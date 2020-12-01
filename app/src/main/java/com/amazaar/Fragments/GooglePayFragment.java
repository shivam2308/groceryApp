package com.amazaar.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.GooglePayConfig;
import com.stripe.android.Stripe;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePayFragment extends BaseFragment {

    private PaymentsClient paymentsClient;
    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 53;
    private View googlePayButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        paymentsClient = Wallet.getPaymentsClient(getActivity(),
                new Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                        .build());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initComponents(View rootView) {
            isReadyToPay();
    }

    private void setGooglePayAvailable(boolean available) {
        if (available) {
            googlePayButton.setVisibility(View.VISIBLE);
        } else {

        }
    }

    private void isReadyToPay() {
        final IsReadyToPayRequest request = createIsReadyToPayRequest();
        paymentsClient.isReadyToPay(request)
                .addOnCompleteListener(
                        new OnCompleteListener<Boolean>() {
                            public void onComplete(Task<Boolean> task) {
                                try {
                                if (task.isSuccessful()) {
                                    setGooglePayAvailable(task.getResult());
                                } else {
                                    // hide Google Pay as payment option
                                }
                                }/*catch (ApiException exception) {
                                    exception.printStackTrace();
                                }*/ catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
    }

    @NonNull
    private IsReadyToPayRequest createIsReadyToPayRequest() {
        final JSONArray allowedAuthMethods = new JSONArray();
        allowedAuthMethods.put("PAN_ONLY");
        allowedAuthMethods.put("CRYPTOGRAM_3DS");

        final JSONArray allowedCardNetworks = new JSONArray();
        allowedCardNetworks.put("AMEX");
        allowedCardNetworks.put("DISCOVER");
        allowedCardNetworks.put("MASTERCARD");
        allowedCardNetworks.put("VISA");

        final JSONObject isReadyToPayRequestJson = new JSONObject();
        try {
            isReadyToPayRequestJson.put("allowedAuthMethods", allowedAuthMethods);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            isReadyToPayRequestJson.put("allowedCardNetworks", allowedCardNetworks);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return IsReadyToPayRequest.fromJson(isReadyToPayRequestJson.toString());
    }

    @NonNull
    private PaymentDataRequest createPaymentDataRequest() throws JSONException {
        final JSONObject tokenizationSpec =
                new GooglePayConfig(getContext()).getTokenizationSpecification();
        final JSONObject cardPaymentMethod = new JSONObject()
                .put("type", "CARD")
                .put(
                        "parameters",
                        new JSONObject()
                                .put("allowedAuthMethods", new JSONArray()
                                        .put("PAN_ONLY")
                                        .put("CRYPTOGRAM_3DS"))
                                .put("allowedCardNetworks",
                                        new JSONArray()
                                                .put("AMEX")
                                                .put("DISCOVER")
                                                .put("MASTERCARD")
                                                .put("VISA"))

                                // require billing address
                                .put("billingAddressRequired", true)
                                .put(
                                        "billingAddressParameters",
                                        new JSONObject()
                                                // require full billing address
                                                .put("format", "MIN")

                                                // require phone number
                                                .put("phoneNumberRequired", true)
                                )
                )
                .put("tokenizationSpecification", tokenizationSpec);

        // create PaymentDataRequest
        final String paymentDataRequest = new JSONObject()
                .put("apiVersion", 2)
                .put("apiVersionMinor", 0)
                .put("allowedPaymentMethods",
                        new JSONArray().put(cardPaymentMethod))
                .put("transactionInfo", new JSONObject()
                        .put("totalPrice", "10.00")
                        .put("totalPriceStatus", "FINAL")
                        .put("currencyCode", "USD")
                )
                .put("merchantInfo", new JSONObject()
                        .put("merchantName", "Example Merchant"))

                // require email address
                .put("emailRequired", true)
                .toString();

        return PaymentDataRequest.fromJson(String.valueOf(paymentDataRequest));
    }

    private void payWithGoogle() throws JSONException {
        AutoResolveHelper.resolveTask(
                paymentsClient.loadPaymentData(createPaymentDataRequest()),
                getActivity(),
                LOAD_PAYMENT_DATA_REQUEST_CODE
        );
    }

    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOAD_PAYMENT_DATA_REQUEST_CODE: {
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        if (data != null) {
                            onGooglePayResult(data);
                        }
                        break;
                    }
                    case Activity.RESULT_CANCELED: {
                        // Canceled
                        break;
                    }
                    case AutoResolveHelper.RESULT_ERROR: {
                        // Log the status for debugging
                        // Generally there is no need to show an error to
                        // the user as the Google Payment API will do that
                        final Status status =
                                AutoResolveHelper.getStatusFromIntent(data);
                        break;
                    }
                    default: {
                        // Do nothing.
                    }
                }
                break;
            }
            default: {
                // Handle any other startActivityForResult calls you may have made.
            }
        }
    }

    private void onGooglePayResult(@NonNull Intent data) {
        final PaymentData paymentData = PaymentData.getFromIntent(data);
        if (paymentData == null) {
            return;
        }

        PaymentMethodCreateParams paymentMethodCreateParams = null;
        try {
            paymentMethodCreateParams = PaymentMethodCreateParams.createFromGooglePay(
                    new JSONObject(paymentData.toJson()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Stripe stripe;
        new Stripe(getContext(),"").createPaymentMethod(
                paymentMethodCreateParams,
                new ApiResultCallback<PaymentMethod>() {
                    @Override
                    public void onSuccess(@NonNull PaymentMethod result) {
                    }

                    @Override
                    public void onError(@NonNull Exception e) {
                    }
                }
        );
    }
}

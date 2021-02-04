package com.amazaar.Widget.OrderSummaryWidget;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.OrderSummaryListAdapter;
import com.amazaar.CommonCode.Strings;
import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.Dialog.CloseOrderDailogFragment;
import com.amazaar.Dialog.DailogDeliveryManAssignment;
import com.amazaar.Dialog.OutForDeliveryDailogFragment;
import com.amazaar.EnumFormatter.DeliveryStatusEnumFormatter;
import com.amazaar.EnumFormatter.PaymentModeEnumFormatter;
import com.amazaar.EnumFormatter.PaymentStatusEnumFormatter;
import com.amazaar.Fragments.GenreateQRCodeFragment;
import com.amazaar.Fragments.QRCodeReaderFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getCurrentActivity;
import static com.amazaar.Module.AmazaarApplication.getFragmentManager;

public class OrderSummaryWidget extends LinearLayout implements IView<OrderSummaryView>, View.OnClickListener {

    @Inject
    public OrderSummaryView m_view;
    @Inject
    public DeliveryStatusEnumFormatter m_deliveryStatusEnumFormatter;
    @Inject
    public PaymentStatusEnumFormatter m_paymentStatusEnumFormatter;
    @Inject
    public PaymentModeEnumFormatter m_paymentModeStatusEnumFormatter;
    private LinearLayout m_name;
    private LinearLayout m_phone;
    private LinearLayout m_email;
    private LinearLayout m_deliveryStatus;
    private CustomTextView m_nametxt;
    private CustomTextView m_phonetxt;
    private CustomTextView m_emailtxt;
    private CustomTextView m_orderId;
    private CustomTextView m_orderDate;
    private CustomTextView m_deliveryDate;
    private CustomTextView m_orderTotal;
    private CustomTextView m_status;
    private CustomTextView m_payment;
    private CustomTextView m_payment_mode;
    private RecyclerView rvOrderList;
    private LinearLayoutManager mLayoutManager;
    private OrderSummaryListAdapter orderListAdapter;
    private ImageButton m_qr_codebtn;
    private ImageButton m_delivery_manBtn;
    private ImageButton m_call_us;
    private ImageButton m_call_him;
    private ImageButton m_closeOeder_btn;
    private ImageButton m_outForDelivery_btn;
    private ImageButton m_scan_qr_btn;


    public OrderSummaryWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.order_summary_layout, this);
        rvOrderList = (RecyclerView) findViewById(R.id.orderlist);
        m_name = (LinearLayout) findViewById(R.id.perosnNamelayout);
        m_phone = (LinearLayout) findViewById(R.id.perosnphonenumberlayout);
        m_email = (LinearLayout) findViewById(R.id.emaillayout);
        m_nametxt = (CustomTextView) findViewById(R.id.personname);
        m_phonetxt = (CustomTextView) findViewById(R.id.personphone);
        m_emailtxt = (CustomTextView) findViewById(R.id.personEmail);
        m_deliveryStatus = (LinearLayout) findViewById(R.id.deliverydate);
        m_orderId = (CustomTextView) findViewById(R.id.orderId);
        m_orderDate = (CustomTextView) findViewById(R.id.orderdate);
        m_deliveryDate = (CustomTextView) findViewById(R.id.orderdeliverdate);
        m_status = (CustomTextView) findViewById(R.id.orderstatus);
        m_payment = (CustomTextView) findViewById(R.id.orderPayment);
        m_payment_mode = (CustomTextView) findViewById(R.id.orderPaymentmode);
        m_orderTotal = (CustomTextView) findViewById(R.id.ordertotal);
        m_qr_codebtn = (ImageButton) findViewById(R.id.qr_code_btn);
        m_delivery_manBtn = (ImageButton) findViewById(R.id.delivery_man_assign_btn);
        m_call_us = (ImageButton) findViewById(R.id.call_us_btn);
        m_call_him = (ImageButton) findViewById(R.id.call_him_btn);
        m_closeOeder_btn = (ImageButton) findViewById(R.id.close_order_btn);
        m_outForDelivery_btn = (ImageButton) findViewById(R.id.out_for_delivery_btn);
        m_scan_qr_btn = (ImageButton) findViewById(R.id.scan_qr_btn);
        mLayoutManager = new LinearLayoutManager(getContext());
        rvOrderList.setLayoutManager(mLayoutManager);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.order_summary_layout, this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public OrderSummaryView getView() {
        return m_view;
    }

    private void initWidget() {
        orderListAdapter = new OrderSummaryListAdapter(getView().getOrderSummaryListModel(), getContext());
        rvOrderList.setAdapter(orderListAdapter);
        getView().getOrderParentId().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                getView().startOrderGetList();
                m_orderId.setText(getView().getOrderParentId().getVar().getOrderId());
                m_orderDate.setText(getView().getOrderParentId().getVar().getOrderDate());
                if (getView().getOrderParentId().getVar().getStatus() != BuyPbOuterClass.DeliveryStatusEnum.DELIVERED) {
                    m_deliveryStatus.setVisibility(GONE);
                } else {
                    m_deliveryDate.setText(getView().getOrderParentId().getVar().getDeliveryDate());
                }
                m_status.setText(m_deliveryStatusEnumFormatter.format(getView().getOrderParentId().getVar().getStatus()));
                m_payment.setText(m_paymentStatusEnumFormatter.format(getView().getOrderParentId().getVar().getonOrderChange().getData().getPaymentRef().getStatus()));
                m_payment_mode.setText(m_paymentModeStatusEnumFormatter.format(getView().getOrderParentId().getVar().getonOrderChange().getData().getPaymentRef().getMode()));
                m_orderTotal.setText(String.valueOf(getView().getOrderParentId().getVar().getonOrderChange().getData().getPaymentRef().getAmount()));
                if (getView().getCustomerSession().getSession().getPrivilege() == CustomerPbOuterClass.PrivilegeTypeEnum.ADMIN) {
                    m_nametxt.setText(Strings.titleCase(getView().getOrderSummaryListModel().get(0).getOnitemChange().getData().getCustomerRef().getName()));
                    m_phonetxt.setText(getView().getOrderSummaryListModel().get(0).getOnitemChange().getData().getCustomerRef().getContact().getMobile().getMobileNo());
                    m_emailtxt.setText(Strings.getEmail(getView().getOrderSummaryListModel().get(0).getOnitemChange().getData().getCustomerRef().getContact().getEmail()));
                } else {
                    m_name.setVisibility(GONE);
                    m_phone.setVisibility(GONE);
                    m_email.setVisibility(GONE);
                }
            }
        });

        m_qr_codebtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GenreateQRCodeFragment genreateQRCodeFragment = new GenreateQRCodeFragment();
                genreateQRCodeFragment.setListModel(getView().getOrderSummaryListModel());
                Utils.addNextFragment(getContext(), genreateQRCodeFragment, getView().getMainFragment(), false);
            }
        });

        m_delivery_manBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DailogDeliveryManAssignment dailogDeliveryManAssignment = new DailogDeliveryManAssignment();
                dailogDeliveryManAssignment.setParentId(getView().getOrderParentId().getVar().getOrderId());
                dailogDeliveryManAssignment.show(getFragmentManager(), getContext().getString(R.string.choose_delivery));
            }
        });

        m_call_us.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getAPhoneCall("+919984929589");
            }
        });
        m_call_him.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getAPhoneCall("+91" + getView().getOrderSummaryListModel().get(0).getOnitemChange().getData().getCustomerRef().getContact().getMobile().getMobileNo());
            }
        });
        m_closeOeder_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseOrderDailogFragment closeOrderDailogFragment = new CloseOrderDailogFragment();
                Bundle buldle = new Bundle();
                buldle.putCharSequence("parentOrderId",getView().getOrderParentId().getVar().getOrderId());
                closeOrderDailogFragment.setArguments(buldle);
                closeOrderDailogFragment.show(getFragmentManager(), "CLose Order");
            }
        });
        m_outForDelivery_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OutForDeliveryDailogFragment outForDeliveryDailogFragment = new OutForDeliveryDailogFragment();
                Bundle buldle = new Bundle();
                buldle.putCharSequence("parentOrderId",getView().getOrderParentId().getVar().getOrderId());
                outForDeliveryDailogFragment.setArguments(buldle);
                outForDeliveryDailogFragment.show(getFragmentManager(), "Out For Delivery");
            }
        });
        m_scan_qr_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeReaderFragment QrCodeFragmentNew = new QRCodeReaderFragment();
                Utils.addNextFragment(getContext(), QrCodeFragmentNew, getView().getMainFragment(), false);
            }
        });
    }

    private void getAPhoneCall(String phoneNo) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        getCurrentActivity().startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo)));
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

}

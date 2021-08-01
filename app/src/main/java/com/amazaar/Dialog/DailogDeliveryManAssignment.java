package com.amazaar.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amazaar.ClientServices.AssignDeliveryMenRequestClientService;
import com.amazaar.ClientServices.CustomerClientService;
import com.amazaar.CommonCode.AToast;
import com.amazaar.CommonCode.Strings;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.R;
import com.google.inject.Injector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class DailogDeliveryManAssignment extends DialogFragment implements View.OnClickListener {

    @Inject
    private CustomerClientService m_customerService;
    @Inject
    private AssignDeliveryMenRequestClientService m_assignDeliveryMenRequestClientService;
    private RadioGroup radioGroup;
    private Button m_cancel;
    private Button m_ok;
    private Map<Integer, String> m_deliveryManId = new HashMap<>();
    private String m_parentOrderid;
    private String m_id;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimationTultip;
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.setContentView(R.layout.dialog_delivery_man);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        injectMembers();
        initializeComponent(dialog);
        return dialog;
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }


    protected void initializeComponent(Dialog v) {

        radioGroup = (RadioGroup) v.findViewById(R.id.radiogroup);
        m_cancel = (Button) v.findViewById(R.id.cancel_btn);
        m_ok = (Button) v.findViewById(R.id.ok_btn);
        m_cancel.setOnClickListener(this);
        m_ok.setOnClickListener(this);
        getDeliveryMan();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                m_id = m_deliveryManId.get(checkedId);
            }
        });
    }

    public void addRadioButtons(List<CustomerPbOuterClass.CustomerPb> customerPbList) {
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        for (CustomerPbOuterClass.CustomerPb customerPb : customerPbList) {
            int keyid = View.generateViewId();
            m_deliveryManId.put(keyid, customerPb.getDbInfo().getId());
            RadioButton rdbtn = new RadioButton(getContext());
            rdbtn.setId(keyid);
            rdbtn.setText(Strings.titleCase(customerPb.getName()));
            rdbtn.setOnClickListener(this);
            radioGroup.addView(rdbtn);
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onClick(View v) {

        if (v == m_cancel) {
            dismiss();
        } else if (v == m_ok) {

            try {
                m_assignDeliveryMenRequestClientService.get(getRequestid());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dismiss();
        }


    }

    private String getRequestid() {
        return m_parentOrderid + "#" + m_id;
    }

    private void getDeliveryMan() {
        CustomerPbOuterClass.CustomerSearchResponsePb customerResp = CustomerPbOuterClass.CustomerSearchResponsePb.getDefaultInstance();
        CustomerPbOuterClass.CustomerSearchRequestPb.Builder builder = CustomerPbOuterClass.CustomerSearchRequestPb.newBuilder();
        builder.setPrivilege(CustomerPbOuterClass.PrivilegeTypeEnum.DELIVERY_MAN);
        try {
            customerResp = m_customerService.search(builder.build());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (customerResp.getResultsList().size() > 0) {
            addRadioButtons(customerResp.getResultsList());
        } else {
            AToast.noDeliveryManFound();
        }
    }

    public void setParentId(String orderId) {
        m_parentOrderid=orderId;
    }
}

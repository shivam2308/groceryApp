package com.amazaar.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.amazaar.CommonCode.AToast;
import com.amazaar.CommonCode.Strings;
import com.amazaar.R;
import com.amazaar.SessionManager.CustomerSession;

import javax.inject.Inject;

public class DialogFragmentFeedback extends DialogFragment implements View.OnClickListener {

    @Inject
    public CustomerSession customerSession;
    private TextView tvCancel;
    private TextView tvSend;
    private EditText etFeedback;
    private RadioButton feedbackbtn;
    private RadioButton complaintbtn;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimationTultip;
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.setContentView(R.layout.dialog_feedback);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        initializeComponent(dialog);
        return dialog;
    }


    protected void initializeComponent(Dialog v) {
        tvCancel = (TextView) v.findViewById(R.id.dailog_send_feedback_tvCancel);
        tvSend = (TextView) v.findViewById(R.id.dailog_send_feedback_tvSubmit);
        etFeedback = (EditText) v.findViewById(R.id.dailog_send_feedback_etDes);
        feedbackbtn = (RadioButton) v.findViewById(R.id.feedbackbtn);
        complaintbtn = (RadioButton) v.findViewById(R.id.complaintbtn);
        tvCancel.setOnClickListener(this);
        tvSend.setOnClickListener(this);
        customerSession = new CustomerSession();
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

        if (v == tvCancel) {
            dismiss();
        } else if (v == tvSend) {
             if (etFeedback.getText().toString().isEmpty()) {
                AToast.notSendEmptyMessage();
            } else {
                String emailbody = etFeedback.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                if (feedbackbtn.isChecked()) {
                    String[] recipients = {"amazaar.feedback@gmail.com"};
                    intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Mail from - " + Strings.titleCase(customerSession.getSession().getName()));
                    intent.putExtra(Intent.EXTRA_TEXT, emailbody);
                    intent.setType("text/html");
                    intent.setPackage("com.google.android.gm");
                    intent.setType("message/rfc822");
                    startActivity(intent);
                    dismiss();
                } else if (complaintbtn.isChecked()) {
                    String[] recipients = {"amazaar.complaint@gmail.com"};
                    intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Complaint Mail from - " + Strings.titleCase(customerSession.getSession().getName()));
                    intent.putExtra(Intent.EXTRA_TEXT, emailbody);
                    intent.setType("text/html");
                    intent.setPackage("com.google.android.gm");
                    intent.setType("message/rfc822");
                    startActivity(intent);
                    dismiss();
                }else{
                    AToast.pleaseSelect();
                }
            }
        }


    }

}

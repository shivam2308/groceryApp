package com.amazaar.Widget.HelpWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.amazaar.Interfaces.IView;
import com.amazaar.R;
import com.amazaar.Dialog.DialogFragmentAboutUs;
import com.amazaar.Dialog.DialogFragmentFeedback;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getFragmentManager;

public class HelpWidget extends LinearLayout implements IView<HelpView>, View.OnClickListener {
    @Inject
    public HelpView m_view;

    private RelativeLayout rlEmailUs;
    private RelativeLayout rlAboutUs;
    private RelativeLayout rlSendFeedback;
    //private Fragment DialogFragmentFeedback;
    //private Object DialogFragmentFeedback;

    public HelpWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.help_layout, this);
        rlEmailUs = (RelativeLayout) findViewById(R.id.fragment_help_rlEmailUs);
        rlAboutUs = (RelativeLayout) findViewById(R.id.fragment_help_rlAboutUs);
        rlSendFeedback = (RelativeLayout) findViewById(R.id.fragment_help_rlFeedback);

        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
       // inflate(getContext(), R.layout.help_layout, this);
        rlSendFeedback.setOnClickListener(this);
        rlEmailUs.setOnClickListener(this);
        rlAboutUs.setOnClickListener(this);

    }

    private void initWidget() {

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public HelpView getView() {
        return m_view;
    }
    @Override
    public void onClick(View v) {

        if (v == rlAboutUs) {
            DialogFragmentAboutUs dialogFragmentAboutUs = new DialogFragmentAboutUs();
            dialogFragmentAboutUs.show(getFragmentManager(), getContext().getString(R.string.lbl_aboutus));
        } else if (v == rlEmailUs) {

        } else if (v == rlSendFeedback) {

            DialogFragmentFeedback dialogFragmentFeedback = new DialogFragmentFeedback();
            dialogFragmentFeedback.show(getFragmentManager(), getContext().getString(R.string.lbl_feedback));
        }
    }
}

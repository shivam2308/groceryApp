package com.amazaar.Widget.ProfileEditWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amazaar.Interfaces.IView;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Utility.AndroidUtility;
import com.amazaar.Utility.Utils;
import com.amazaar.Widget.ProfileSubmitWidget.ProfileSubmitView;
import com.google.inject.Injector;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getFragmentManager;

public class ProfileEditWidget extends LinearLayout implements IView<ProfileEditView>, View.OnClickListener {

    private ImageView m_Close;
    @Inject
    public ProfileEditView m_view;
    private TextView m_save;
    private EditText m_firstName;
    private EditText m_lastname;
    private EditText m_email;
    private EditText m_houseNo;
    private EditText m_street;
    private EditText m_landmark;
    private String m_city;
    private String m_state;
    private EditText m_pincode;
    private NiceSpinner m_citySpinner;
    private String m_gender;
    private NiceSpinner m_stateSpinner;

    public ProfileEditWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.profile_edit_layout, this);
        m_Close = (ImageView) findViewById(R.id.dailog_send_feedback_ivClose);
        m_citySpinner = (NiceSpinner) findViewById(R.id.city_spinner);
        m_save = (TextView) findViewById(R.id.fragment_save_tvSave);
        m_stateSpinner = (NiceSpinner) findViewById(R.id.state_spinner);
        m_firstName = (EditText) findViewById(R.id.fragment_signup_etFirstName);
        m_lastname = (EditText) findViewById(R.id.fragment_signup_etLastName);
        m_email = (EditText) findViewById(R.id.fragment_signup_etEmail);
        m_houseNo = (EditText) findViewById(R.id.houseNo);
        m_street = (EditText) findViewById(R.id.street);
        m_landmark = (EditText) findViewById(R.id.landmark);
        m_pincode = (EditText) findViewById(R.id.pincode);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.profile_edit_layout, this);
        m_Close.setOnClickListener(this);
    }

    private void initWidget() {
        List<String> cityDataset = new LinkedList<>(getView().getCityList());
        m_citySpinner.attachDataSource(cityDataset);
        m_citySpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                m_city = parent.getItemAtPosition(position).toString();
            }
        });
        List<String> stateDataset = new LinkedList<>(getView().getStateList());
        m_stateSpinner.attachDataSource(stateDataset);
        m_stateSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                m_state = parent.getItemAtPosition(position).toString();
            }
        });
        m_save.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getView().createCustomer(AndroidUtility.getTextFromEditText(m_firstName),
                        AndroidUtility.getTextFromEditText(m_lastname),
                        AndroidUtility.getTextFromEditText(m_email),
                        AndroidUtility.getTextFromEditText(m_houseNo),
                        AndroidUtility.getTextFromEditText(m_street),
                        AndroidUtility.getTextFromEditText(m_landmark),
                        m_city, m_state, Integer.parseInt(AndroidUtility.getTextFromEditText(m_pincode)), getContext());
                return false;
            }
        });
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public ProfileEditView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        Utils.hideKeyboard(getContext());
        if (v == m_Close) {
            AmazaarApplication.getFragmentManager().popBackStack();
        }
    }
}

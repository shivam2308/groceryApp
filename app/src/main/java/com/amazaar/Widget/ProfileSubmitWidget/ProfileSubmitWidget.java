package com.amazaar.Widget.ProfileSubmitWidget;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amazaar.CommonCode.AToast;
import com.amazaar.Interfaces.IView;
import com.amazaar.R;
import com.amazaar.Utility.AndroidUtility;
import com.google.inject.Injector;
import com.prod.basic.common.code.Strings;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class ProfileSubmitWidget extends LinearLayout implements IView<ProfileSubmitView> {

    @Inject
    public ProfileSubmitView m_view;
    private TextView m_signUp;
    private EditText m_firstName;
    private EditText m_lastname;
    private EditText m_email;
    private EditText m_houseNo;
    private EditText m_street;
    private EditText m_landmark;
    private String m_city;
    private String m_state;
    private EditText m_pincode;
    private RadioGroup m_genderGroup;
    private NiceSpinner m_citySpinner;
    private String m_gender;
    private NiceSpinner m_stateSpinner;

    public ProfileSubmitWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.profile_submit_layout, this);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.profile_submit_layout, this);
        m_citySpinner = (NiceSpinner) findViewById(R.id.city_spinner);
        m_signUp = (TextView) findViewById(R.id.fragment_signup_tvSignup);
        m_stateSpinner = (NiceSpinner) findViewById(R.id.state_spinner);
        m_firstName = (EditText) findViewById(R.id.fragment_signup_etFirstName);
        m_lastname = (EditText) findViewById(R.id.fragment_signup_etLastName);
        m_email = (EditText) findViewById(R.id.fragment_signup_etEmail);
        m_houseNo = (EditText) findViewById(R.id.houseNo);
        m_street = (EditText) findViewById(R.id.street);
        m_landmark = (EditText) findViewById(R.id.landmark);
        m_pincode = (EditText) findViewById(R.id.pincode);
        m_genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
    }

    public void initWidget() {
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
        m_genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                m_gender = rb.getText().toString();
            }
        });
        m_signUp.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (Strings.notEmpty(AndroidUtility.getTextFromEditText(m_firstName)) &&
                        Strings.notEmpty(AndroidUtility.getTextFromEditText(m_lastname)) &&
                        Strings.notEmpty(AndroidUtility.getTextFromEditText(m_email)) &&
                        Strings.notEmpty(AndroidUtility.getTextFromEditText(m_houseNo)) &&
                        Strings.notEmpty(AndroidUtility.getTextFromEditText(m_street)) &&
                        Strings.notEmpty(AndroidUtility.getTextFromEditText(m_landmark)) &&
                        Strings.notEmpty(m_city) &&
                        Strings.notEmpty(m_state) &&
                        Strings.notEmpty(AndroidUtility.getTextFromEditText(m_pincode)) &&
                        Strings.notEmpty(m_gender)){
                    AToast.plsWait();
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("Saving...");
                    progressDialog.show();
                    getView().createCustomer(AndroidUtility.getTextFromEditText(m_firstName),
                            AndroidUtility.getTextFromEditText(m_lastname),
                            AndroidUtility.getTextFromEditText(m_email),
                            AndroidUtility.getTextFromEditText(m_houseNo),
                            AndroidUtility.getTextFromEditText(m_street),
                            AndroidUtility.getTextFromEditText(m_landmark),
                            m_city, m_state, Integer.parseInt(AndroidUtility.getTextFromEditText(m_pincode)), m_gender, getContext());
                }
                else{
                    AToast.formFieldMissingToast();
                }
                return false;
            }
        });
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public ProfileSubmitView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }
}

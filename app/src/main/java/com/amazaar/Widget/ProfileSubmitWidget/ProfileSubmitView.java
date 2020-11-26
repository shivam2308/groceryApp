package com.amazaar.Widget.ProfileSubmitWidget;

import android.content.Context;

import com.amazaar.CommonCode.CommonHelper;
import com.amazaar.ControlFlow.CustomerRegistration;
import com.amazaar.EnumFormatter.CityEnumFormatter;
import com.amazaar.EnumFormatter.GenderEnumFormatter;
import com.amazaar.EnumFormatter.StateEnumFormatter;
import com.amazaar.Protobuff.AddressPbOuterClass;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.Protobuff.RegistrationPbOuterClass;
import com.prod.basic.common.collect.Lists;

import java.util.List;

import javax.inject.Inject;


public class ProfileSubmitView {

    @Inject
    public CityEnumFormatter m_cityEnumFormatter;
    @Inject
    public StateEnumFormatter m_stateEnumFormatter;
    @Inject
    public GenderEnumFormatter m_genderEnumFormatter;
    @Inject
    public CommonHelper m_commanHelper;
    @Inject
    public CustomerRegistration m_customerRegistration;
    private String m_phoneNumber;

    @Inject
    public ProfileSubmitView(CustomerRegistration customerRegistration, CommonHelper commanHelper, CityEnumFormatter cityEnumFormatter, StateEnumFormatter stateEnumFormatter, GenderEnumFormatter genderEnumFormatter) {
        m_cityEnumFormatter = cityEnumFormatter;
        m_stateEnumFormatter = stateEnumFormatter;
        m_genderEnumFormatter = genderEnumFormatter;
        m_commanHelper = commanHelper;
        m_customerRegistration = customerRegistration;
    }

    public void setPhoneNumber(String phoneNumber) {
        m_phoneNumber = phoneNumber;
    }

    public List<String> getCityList() {
        List<String> cityList = Lists.newArrayList();
        for (AddressPbOuterClass.CityEnum city :
                AddressPbOuterClass.CityEnum.values()) {
            if (city != AddressPbOuterClass.CityEnum.UNRECOGNIZED) {
                cityList.add(m_cityEnumFormatter.format(city));
            }
        }
        return cityList;
    }

    public List<String> getStateList() {
        List<String> stateList = Lists.newArrayList();
        for (AddressPbOuterClass.StateEnum state :
                AddressPbOuterClass.StateEnum.values()) {
            if (state != AddressPbOuterClass.StateEnum.UNRECOGNIZED) {
                stateList.add(m_stateEnumFormatter.format(state));
            }
        }
        return stateList;
    }

    public void createCustomer(String firstName, String lastName, String email, String houseNo, String street, String landMark, String city, String state, int pincode, String gender, Context context) {
        CustomerPbOuterClass.CustomerPb.Builder customerPb = CustomerPbOuterClass.CustomerPb.newBuilder();
        customerPb.getNameBuilder().setFirstName(firstName);
        customerPb.getNameBuilder().setLastName(lastName);
        customerPb.getContactBuilder().setEmail(m_commanHelper.getEmailPb(email));
        customerPb.getContactBuilder().getMobileBuilder().setMobileNo(m_phoneNumber);
        customerPb.getAddressBuilder().setHomeNo(houseNo);
        customerPb.getAddressBuilder().setStreet(street);
        customerPb.getAddressBuilder().setLandMark(landMark);
        customerPb.getAddressBuilder().setCity(m_cityEnumFormatter.getEnum(city));
        customerPb.getAddressBuilder().setState(m_stateEnumFormatter.getEnum(state));
        customerPb.getAddressBuilder().setPincode(pincode);
        customerPb.setGender(m_genderEnumFormatter.getEnum(gender));
        RegistrationPbOuterClass.RegistrationPb.Builder registrationPb = RegistrationPbOuterClass.RegistrationPb.newBuilder();
        registrationPb.setCustomer(customerPb);
        m_customerRegistration.startRegistration(context, registrationPb.build());
    }


}

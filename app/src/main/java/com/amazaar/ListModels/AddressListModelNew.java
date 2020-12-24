package com.amazaar.ListModels;

import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.AddressPbOuterClass;

import javax.inject.Inject;

/**
 * Created by Nishish on 8/6/2018.
 */

public class AddressListModelNew {
    @Inject
    public VariableValueChange<AddressPbOuterClass.AddressPb> m_address;
    private String address;

    @Inject
    public AddressListModelNew() {
        m_address = new VariableValueChange<>();
        m_address.setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                setAddress(com.amazaar.CommonCode.Strings.getAddress(m_address.getVar()));
            }
        });
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public VariableValueChange<AddressPbOuterClass.AddressPb> getAddressModel() {
        return m_address;
    }
}

package com.amazaar.Widget.EditProductDetailWidget;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.amazaar.Adapters.AddressListAdapter;
import com.amazaar.Adapters.OrderSummaryMiniListAdapter;
import com.amazaar.CommonCode.DataModel;
import com.amazaar.EnumFormatter.ItemQuantityTypeEnumFormatter;
import com.amazaar.Interfaces.IView;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.amazaar.R;
import com.amazaar.Utility.AndroidUtility;
import com.google.inject.Injector;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class EditProductDetailWidget extends LinearLayout implements IView<EditProductDetailView>, View.OnClickListener{
    @Inject
    public EditProductDetailView m_view;
    private EditText m_product_name;
    private EditText m_product_quantity;
    private EditText m_product_price;
    private NiceSpinner m_quantityTypeSpinner;
    private Switch m_availability_status;
    private String m_quantity_type;
    private TextView m_saveButton;
    @Inject
    public ItemQuantityTypeEnumFormatter m_itemQuantityTypeEnumFormatter;

    public EditProductDetailWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.edit_product_detail_layout, this);
        m_product_name = (EditText) findViewById(R.id.fragment_edit_etProductName);
        m_product_quantity = (EditText) findViewById(R.id.fragment_edit_productquantity);
        m_product_price = (EditText) findViewById(R.id.fragment_edit_productprice);
        m_availability_status = (Switch) findViewById(R.id.toggle_av);
        m_quantityTypeSpinner = (NiceSpinner) findViewById(R.id.quantity_type_spinner);
        m_saveButton = (TextView) findViewById(R.id.fragment_save_tvEditDataSave);

        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }
    private void inflateLayout() {
        inflate(getContext(), R.layout.edit_product_detail_layout, this);
    }
    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }
    private void initWidget() {
        getView().getItemPbMOdel().setListener(new DataModel.ChangeListener() {
            @Override
            public void onChange() {
                m_product_name.setText(getView().getItemPbMOdel().getData().getItemName().getFirstName());
                m_product_price.setText("" + getView().getItemPbMOdel().getData().getPrice());
                m_product_quantity.setText(getView().getItemPbMOdel().getData().getQuantity());
                if(getView().getItemPbMOdel().getData().getAvailabilityStatus() == ItemPbOuterClass.AvailabilityStatusEnum.AVAILABLE){
                    m_availability_status.setChecked(true);
                }
                else{
                    m_availability_status.setChecked(false);
                }

                List<String> quantityTypeDataset = new LinkedList<>(getView().getQuantityTypeList());

                m_quantityTypeSpinner.attachDataSource(quantityTypeDataset);
                m_quantityTypeSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                    @Override
                    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                        m_quantity_type = parent.getItemAtPosition(position).toString();

                    }
                });
                m_quantityTypeSpinner.setSelectedIndex(getView().getQuantityTypePosition(getView().getItemPbMOdel().getData().getItemQuantityType()));
                m_quantity_type = m_quantityTypeSpinner.getItemAtPosition(m_quantityTypeSpinner.getSelectedIndex()).toString();
            }

        });
        m_saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().updateCurrentItem(AndroidUtility.getTextFromEditText(m_product_name), AndroidUtility.getTextFromEditText(m_product_price), AndroidUtility.getTextFromEditText(m_product_quantity), getView().getAvailabilityStatus(m_availability_status), m_quantity_type);
            }
        });
    }

    @Override
    public EditProductDetailView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {

    }
}

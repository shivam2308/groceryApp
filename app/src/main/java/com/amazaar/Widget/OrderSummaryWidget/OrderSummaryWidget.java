package com.amazaar.Widget.OrderSummaryWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.OrderSummaryListAdapter;
import com.amazaar.CommonCode.Strings;
import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.EnumFormatter.DeliveryStatusEnumFormatter;
import com.amazaar.EnumFormatter.PaymentModeEnumFormatter;
import com.amazaar.EnumFormatter.PaymentStatusEnumFormatter;
import com.amazaar.Fragments.GenreateQRCodeFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

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
    private Button m_qr_codebtn;

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
        m_qr_codebtn = (Button) findViewById(R.id.qr_code_btn);
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
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

}

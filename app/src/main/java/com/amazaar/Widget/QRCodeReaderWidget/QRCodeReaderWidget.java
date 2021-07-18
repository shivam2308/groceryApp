package com.amazaar.Widget.QRCodeReaderWidget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.amazaar.ControlFlow.ConfirmOrderDelivery;
import com.amazaar.Enums.CallSyncStateEnum;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class QRCodeReaderWidget extends LinearLayout implements IView<QRCodeAppReaderView>, View.OnClickListener, com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener {

    @Inject
    public QRCodeAppReaderView m_view;
    @Inject
    public ConfirmOrderDelivery m_confirmOrderDelivery;
    private QRCodeReaderView qrCodeReaderView;



    public QRCodeReaderWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.qr_code_reader_layout, this);
        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);

        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.qr_code_reader_layout, this);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
       // qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();

    }

    public void initWidget() {
        getView().setQrCodeReaderView(qrCodeReaderView);
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public QRCodeAppReaderView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Log.e("QRCodeReade",text);
        if(m_confirmOrderDelivery.getCallSync().getVar()!= CallSyncStateEnum.SYNC_START){
            Vibrator v = (Vibrator) AmazaarApplication.getCurrentActivity().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(1000);
            m_confirmOrderDelivery.confirmOrder(getView().getConfirmDeliveryPb(text));
        }
    }
}

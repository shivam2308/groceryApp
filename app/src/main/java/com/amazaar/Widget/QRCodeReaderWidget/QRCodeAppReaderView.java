package com.amazaar.Widget.QRCodeReaderWidget;

import com.amazaar.Fragments.QRCodeReaderFragment;
import com.amazaar.Protobuff.ConfirmOrderDeliveryPbOuterClass;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import java.util.Arrays;

import javax.inject.Inject;

public class QRCodeAppReaderView {
    private QRCodeReaderView m_qrCodeReaderView;
    private QRCodeReaderFragment m_qrCodeReaderFragment;

    @Inject
    public QRCodeAppReaderView() {

    }

    public QRCodeReaderView getQrCodeReaderView() {
        return m_qrCodeReaderView;
    }

    public void setQrCodeReaderView(QRCodeReaderView qrCodeReaderView) {
        m_qrCodeReaderView = qrCodeReaderView;
    }

    public void setMainFragment(QRCodeReaderFragment qrCodeReaderFragment) {
        m_qrCodeReaderFragment = qrCodeReaderFragment;
    }

    public ConfirmOrderDeliveryPbOuterClass.ConfirmOrderDeliveryPb getConfirmDeliveryPb(String text) {
        ConfirmOrderDeliveryPbOuterClass.ConfirmOrderDeliveryPb.Builder builder = ConfirmOrderDeliveryPbOuterClass.ConfirmOrderDeliveryPb.newBuilder();
        builder.addAllData(Arrays.asList(text.split("/")));
        return builder.build();
    }
}

package com.amazaar.Widget.GenreateQRCodeWidget;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.amazaar.CommonCode.EncryptorAndDecryptor;
import com.amazaar.Fragments.GenreateQRCodeFragment;
import com.amazaar.ListModels.OrderSummaryListModel;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.SessionManager.CustomerSession;

import java.util.List;

import javax.inject.Inject;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenreateQRCodeView {

    @Inject
    public CustomerSession m_customerSession;
    private GenreateQRCodeFragment m_genreateQRCodeFragment;
    @Inject
    private VariableValueChange<List<OrderSummaryListModel>> m_orderSummaryListModel;

    @Inject
    GenreateQRCodeView(VariableValueChange<List<OrderSummaryListModel>> orderSummaryListModel, CustomerSession customerSession) {
        m_orderSummaryListModel = orderSummaryListModel;
        m_customerSession = customerSession;
    }

    public Bitmap getQrCode() {
        QRGEncoder qrgEncoder = new QRGEncoder(getEncryptedCode(), null, QRGContents.Type.TEXT, 400);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        return qrgEncoder.getBitmap();
    }

    private String getEncryptedCode() {
        return EncryptorAndDecryptor.encrypt(getQrCodeString());
    }

    private String getQrCodeString() {
        String qrcode = "";
        qrcode = qrcode + m_customerSession.getSession().getDbInfo().getId() + "&";
        qrcode = qrcode + m_orderSummaryListModel.getVar().get(0).getOnitemChange().getData().getParentOrderId() + "&";
        String buyIds = "";
        String orderIds = "";
        for (OrderSummaryListModel item : m_orderSummaryListModel.getVar()) {
            buyIds = buyIds + item.getOnitemChange().getData().getDbInfo().getId() + "|";
            orderIds = orderIds + item.getOnitemChange().getData().getOrderId() + "|";
        }
        String time = String.valueOf(System.currentTimeMillis());
        qrcode = qrcode + buyIds + "&" + orderIds + "&" + time;
        return qrcode;
    }

    public GenreateQRCodeFragment getMainFragment() {
        return m_genreateQRCodeFragment;
    }

    public void setMainFragment(GenreateQRCodeFragment genreateQRCodeFragment) {
        m_genreateQRCodeFragment = genreateQRCodeFragment;
    }

    public VariableValueChange<List<OrderSummaryListModel>> getListModel() {
        return m_orderSummaryListModel;
    }
}

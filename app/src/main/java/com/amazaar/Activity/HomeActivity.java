package com.amazaar.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.Fragments.HomeCategoryFragment;
import com.amazaar.Fragments.PaymentFragment;
import com.amazaar.Fragments.QRCodeReaderFragment;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Widget.TopBarWidget.TopBarWidget;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    private TopBarWidget m_topBar;
    private Fragment mFragment = null;
    private HomeCategoryFragment mainFragment;
    private QRCodeReaderFragment qrReaderFragment;
    @Inject
    private PaymentFragment paymentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actity);
        m_topBar = (TopBarWidget) findViewById(R.id.topBar);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //qrReaderFragment.getQRCodeReaderWidget().getView().getQrCodeReaderView().stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AmazaarApplication.setCurrentActivity(this);
       // qrReaderFragment.getQRCodeReaderWidget().getView().getQrCodeReaderView().startCamera();
    }

    private void initView() {
        setToolbar(TopBarUiEnum.HOME);
        mainFragment = new HomeCategoryFragment();
        qrReaderFragment = new QRCodeReaderFragment();
        paymentFragment= new PaymentFragment();
        m_topBar.getView().setMainFragment(mainFragment);
        openFragment(mainFragment);
       // openFragment(qrReaderFragment);
    }

    public void setToolbar(TopBarUiEnum enumm) {
        m_topBar.getView().getTopBarChange().setVar(enumm);
    }

    private void openFragment(final Fragment mFragment) {
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flcontainer, mFragment, mFragment.getClass().getSimpleName());
        transaction.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        paymentFragment.onActivityResult(requestCode, resultCode, data);
    }
}
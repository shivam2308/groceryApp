package com.amazaar.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.amazaar.Dialog.CloseAppDialogFragment;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.Fragments.HomeCategoryFragment;
import com.amazaar.Fragments.MyAccountFragment;
import com.amazaar.Fragments.OrderListFragment;
import com.amazaar.Fragments.PaymentFragment;
import com.amazaar.Fragments.QRCodeReaderFragment;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.SessionManager.CustomerSession;
import com.amazaar.Widget.TopBarWidget.TopBarWidget;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getContext;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String[]> {

    @Inject
    public CustomerSession m_customerSession;
    private TopBarWidget m_topBar;
    private Fragment mFragment = null;
    private HomeCategoryFragment mainFragment;
    private QRCodeReaderFragment qrReaderFragment;
    private OrderListFragment m_orderLIstFragment;
    @Inject
    private PaymentFragment paymentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AmazaarApplication.setCurrentActivity(this);
        AmazaarApplication.createLoadingDailog();
        setContentView(R.layout.activity_home_actity);
        m_topBar = (TopBarWidget) findViewById(R.id.topBar);
        injectMembers();
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
        paymentFragment = new PaymentFragment();
        m_orderLIstFragment = new OrderListFragment();
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
        mainFragment.getHomeCategoryWidget().getView().getProductListFragment().getProductListWidget().getView().getProductDetailsFragment().getProductDetailsWidget().getView().getUploadFragment().onActivityResult(requestCode, resultCode, data);
        if (AmazaarApplication.getCurrentFragment().getClass() == MyAccountFragment.class) {
            m_topBar.getView().getMenuFragment().getMenuWidget().getView().getMyAccountFragment().getMyAccountWidget().getView().getUploadFragment().onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {

        int f = AmazaarApplication.getFragmentManager().getBackStackEntryCount();

        if (f < 1) {
            CloseAppDialogFragment closeAppDialogFragment = new CloseAppDialogFragment();
            closeAppDialogFragment.show(getFragmentManager(), "EXIT FROM PROJECT");
        } else {
            super.onBackPressed();
        }
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @NonNull
    @Override
    public Loader<String[]> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String[]> loader, String[] data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String[]> loader) {

    }
}
package com.amazaar.Widget.GenreateQRCodeWidget;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.OrderListAdapter;
import com.amazaar.Fragments.OrderSummaryFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListModels.OrderListModel;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.amazaar.Widget.OrderListWidget.OrderListView;
import com.google.inject.Injector;

import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class GenreateQRCodeWidget extends LinearLayout implements IView<GenreateQRCodeView>, View.OnClickListener{

    @Inject
    public GenreateQRCodeView m_view;

    //Declaration
    private ImageView m_qr_code;

    public GenreateQRCodeWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.qr_code_layout, this);
        m_qr_code = findViewById(R.id.qrcodeimage);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }



//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        item = menu.findItem(R.id.menu_left);
//        item.setVisible(false);
//
//    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.qr_code_layout, this);

    }

    public void initWidget() {
       getView().getListModel().setListener(new VariableValueChange.ChangeListener() {
           @Override
           public void onChange() {
               setBrightness(255);
               m_qr_code.setImageBitmap(getView().getQrCode());
           }
       });

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }


    @Override
    public GenreateQRCodeView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setBrightness(int brightness){

        //constrain the value of brightness
        if(brightness < 0)
            brightness = 0;
        else if(brightness > 255)
            brightness = 255;


        ContentResolver cResolver = AmazaarApplication.getCurrentActivity().getContentResolver();
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);

    }

}

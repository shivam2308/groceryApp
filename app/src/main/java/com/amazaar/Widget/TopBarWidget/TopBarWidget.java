package com.amazaar.Widget.TopBarWidget;

import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazaar.Fragments.CartListFragment;
import com.amazaar.Fragments.MenuFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class TopBarWidget extends LinearLayout implements IView<TopBarView>, View.OnClickListener {

    @Inject
    public TopBarView m_view;
    private EditText etSearch;
    private ImageView ivMenu;
    private ImageView ivCart;
    private TextView tvTitle;
    private RelativeLayout rlToolbar;


    public TopBarWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.top_bar_layout, this);
        etSearch = (EditText) findViewById(R.id.activity_home_etSearch);
        ivMenu = (ImageView) findViewById(R.id.activity_home_ivMenu);
        ivCart = (ImageView) findViewById(R.id.activity_home_ivCart);
        tvTitle = (TextView) findViewById(R.id.activity_home_tvTitle);
        rlToolbar = (RelativeLayout) findViewById(R.id.activity_home_rlToolbar);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.top_bar_layout, this);
        ivMenu.setOnClickListener(this);
        ivCart.setOnClickListener(this);
    }

    public void setUpToolbar(final String title, final boolean isShowback, boolean isToolbarVisible, boolean isSearch, boolean isCart) {

        rlToolbar.setVisibility(isToolbarVisible ? View.VISIBLE : View.GONE);
        ivMenu.setBackgroundResource(isShowback ? R.drawable.ic_menu : R.drawable.ic_back_arror);
        etSearch.setVisibility(isSearch ? View.VISIBLE : View.GONE);
        tvTitle.setVisibility(isSearch ? View.GONE : View.VISIBLE);
        ivCart.setVisibility(isCart ? View.VISIBLE : View.GONE);
        tvTitle.setText(title);


    }

    private void initWidget() {
        getView().getTopBarChange().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                switch (getView().getTopBarChange().getVar()) {
                    case HOME:
                        setUpToolbar(getContext().getString(R.string.nav_menu_home), true, true, true, true);
                        break;
                    case MENU:
                        setUpToolbar(getContext().getString(R.string.nav_menu_home), false, false, false, false);
                        break;
                    case CART:
                    case PROFILE:
                        setUpToolbar(getContext().getString(R.string.my_cart_4), false, false, false, false);
                        break;
                    case WISH_LIST:
                        setUpToolbar(getContext().getString(R.string.my_wishlist), false, true, false,true);
                        break;
                    case PRODUCT_DETAILS:
                        setUpToolbar(getContext().getString(R.string.fruits_vegetables), false,true,false,true);
                        break;
                    case CHECK_OUT:
                        setUpToolbar("CheckOut", false, true, false,false);
                        break;
                    case PAYMENT:
                        setUpToolbar("Payment", false, true, false,false);
                        break;
                    case SETTING:
                        setUpToolbar("Settings", false, true, false,false);
                        break;
                    case HELP:
                        setUpToolbar("Help", false, true, false,false);
                        break;
                    case ORDER_LIST:
                        setUpToolbar("Order List", false, true, false,false);
                        break;
                }
            }
        });

        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartListFragment mFragment = new CartListFragment();
                Utils.addNextFragment(getContext(), mFragment, getView().getMainFragment(), true);
            }
        });
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public TopBarView getView() {
        return m_view;
    }

    @Override
    public void onClick(View v) {
        Utils.hideKeyboard(getContext());

        if (v == ivMenu) {
            FragmentManager fragmentManager = AmazaarApplication.getFragmentManager();

            if (fragmentManager.getBackStackEntryCount() > 0) {
                AmazaarApplication.getFragmentManager().popBackStack();

            } else {
                MenuFragment menuFragment = new MenuFragment();
                Utils.addNextFragmentFadeAnim(menuFragment, getView().getMainFragment());
            }


        }
    }
}

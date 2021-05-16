package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.R;
import com.amazaar.SessionManager.CustomerSession;
import com.amazaar.Widget.HomeCategoryWidget.HomeCategoryWidget;
import com.amazaar.Widget.OrderListWidget.OrderListWidget;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class HomeCategoryFragment extends BaseFragment {

    private HomeCategoryWidget m_homeCategoryWidget;
    private OrderListWidget m_orderListWudget;
    private MenuItem item;
    @Inject
    public CustomerSession m_customerSession;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initToolbar();
        injectMembers();
        initComponents(rootView);

        return rootView;
    }


    @Override
    public void initComponents(View rootView) {
        m_homeCategoryWidget = rootView.findViewById(R.id.home_category);
        m_orderListWudget = rootView.findViewById(R.id.orderlist_widget_);
        if(m_customerSession.getSession().getPrivilege()== CustomerPbOuterClass.PrivilegeTypeEnum.DELIVERY_MAN){
            m_homeCategoryWidget.setVisibility(View.GONE);
            m_orderListWudget.setVisibility(View.VISIBLE);
            m_orderListWudget.getView().setMainFragment(new OrderListFragment());
        }else{
            m_orderListWudget.setVisibility(View.GONE);
            m_homeCategoryWidget.setVisibility(View.VISIBLE);
            m_homeCategoryWidget.getView().setMainFragment(this);
        }

    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.HOME);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        item = menu.findItem(R.id.menu_left);
        item.setVisible(false);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initToolbar();
        }
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    public HomeCategoryWidget getHomeCategoryWidget(){
        return m_homeCategoryWidget;
    }
}

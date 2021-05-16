package com.amazaar.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.ControlFlow.GetImageFromUrl;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.R;

import java.util.List;

public class ProductPagerAdapter extends PagerAdapter {

    Context context;
    private List<ImagePbOuterClass.ImageRefPb> imgList;
    LayoutInflater layoutInflater;
    private GetImageFromUrl m_getImageFromUrl;

    public ProductPagerAdapter(Context context, List<ImagePbOuterClass.ImageRefPb> imgList) {
        this.context = context;
        this.imgList = imgList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.row_productdetails_slider, container, false);
        ImageView imageView = itemView.findViewById(R.id.ivCode);
        m_getImageFromUrl.setImageFromUrl(context, imgList.get(position),imageView, DefaultImageUrl.ImageShowTypeEnum.ITEM);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

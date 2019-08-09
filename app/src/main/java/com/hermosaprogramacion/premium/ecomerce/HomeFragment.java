package com.hermosaprogramacion.premium.ecomerce;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView categoryRecycler;
    private CategoryAdapter adapter;

    /////////// Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPager = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIODO_TIME = 3000;

    ////////// Banner Slider

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecycler = view.findViewById(R.id.category_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecycler.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
        categoryModels.add(new CategoryModel("LINK", "HOME"));
        categoryModels.add(new CategoryModel("LINK", "ELECTRONICS"));
        categoryModels.add(new CategoryModel("LINK", "PHONE"));
        categoryModels.add(new CategoryModel("LINK", "HOUSE"));
        categoryModels.add(new CategoryModel("LINK", "FASHION"));
        categoryModels.add(new CategoryModel("LINK", "TOYS"));
        categoryModels.add(new CategoryModel("LINK", "SPORTS"));
        categoryModels.add(new CategoryModel("LINK", "BOOKS"));

        adapter = new CategoryAdapter(getActivity(),categoryModels);
        categoryRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /////////// Banner Slider
        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);
        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.app_icon));
        sliderModelList.add(new SliderModel(R.drawable.custom_error_icon));

        sliderModelList.add(new SliderModel(R.drawable.ic_mail_outline_24px));
        sliderModelList.add(new SliderModel(R.drawable.grupo_317));
        sliderModelList.add(new SliderModel(R.drawable.mail_outline_24px));
        sliderModelList.add(new SliderModel(R.drawable.logo));
        sliderModelList.add(new SliderModel(R.drawable.ic_shopping_cart_black_24dp));

        sliderModelList.add(new SliderModel(R.drawable.ic_mail_outline_24px));
        sliderModelList.add(new SliderModel(R.drawable.profile_placeholder));

        SliderAdapter adapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(adapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              currentPager = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }

            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSliderShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSliderShow();
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    startBannerSliderShow();
                }
                return false;
            }
        });

        ////////// Banner Slider

        return view;
    }

    ////////// Banner Slider
    private void pageLooper(){

        if (currentPager == sliderModelList.size() - 2)
        {
            currentPager = 2;
            bannerSliderViewPager.setCurrentItem(currentPager,false);
        }
        if (currentPager == 1)
        {
            currentPager = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPager,false);
        }
    }

    private void startBannerSliderShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
               if (currentPager >= sliderModelList.size()){
                   currentPager= 1;
               }
               bannerSliderViewPager.setCurrentItem(currentPager++,true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIODO_TIME);
    }

    private void stopBannerSliderShow(){
        timer.cancel();
    }
    ////////// Banner Slider

}

package com.hermosaprogramacion.premium.ecomerce;


import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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

    ///////// Strip Ad
      private ImageView        stripAdImage;
      private ConstraintLayout stripAdContainer;
    ///////// Strip Ad

    ////////// Horizontal product layout
    private TextView      h_s_product_title;
    private Button        h_s_product_btn;
    private RecyclerView  h_s_product_recyclerView;
    ////////// Horizontal product layout

    //////// Grid Product layout
    private TextView      grid_product_title;
    private Button        grid_product_btn;
    private GridView      grid_product_gridView;
    //////// Grid Product layout

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

        sliderModelList.add(new SliderModel(R.drawable.ic_shopping_cart_black_24dp,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.app_icon,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.custom_error_icon,"#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.ic_mail_outline_24px,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.grupo_317,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.mail_outline_24px,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.logo,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.ic_shopping_cart_black_24dp,"#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.ic_mail_outline_24px,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.profile_placeholder,"#077AE4"));

        SliderAdapter adapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(adapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setCurrentItem(currentPager);
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

        ///////// Strip Ad
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.all_new_samsung_galaxy_note_9_is_here);
        stripAdContainer.setBackgroundColor(Color.parseColor("#000000"));
        ///////// Strip Ad

        ////////// Horizontal product layout
        h_s_product_title = view.findViewById(R.id.horizontal_scroll_layout_title);
        h_s_product_btn = view.findViewById(R.id.horizontal_scroll_layout_button);
        h_s_product_recyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerView);

        List<HorizontalProductScrollModel> horizontalProductScrollModels = new ArrayList<>();
        horizontalProductScrollModels.add(new HorizontalProductScrollModel(R.drawable.imagen_2, "Redmi A5", "SD 425 PROCESSOR", "6000/"));
        horizontalProductScrollModels.add(new HorizontalProductScrollModel(R.drawable.app_icon, "Redmi A6", "SD 425 PROCESSOR", "6000/"));
        horizontalProductScrollModels.add(new HorizontalProductScrollModel(R.drawable.ic_shopping_cart_black_24dp, "Redmi A7", "SD 425 PROCESSOR", "6000/"));
        horizontalProductScrollModels.add(new HorizontalProductScrollModel(R.drawable.custom_error_icon, "Redmi A8", "SD 425 PROCESSOR", "6000/"));
        horizontalProductScrollModels.add(new HorizontalProductScrollModel(R.drawable.app_icon, "Redmi A9", "SD 425 PROCESSOR", "6000/"));
        horizontalProductScrollModels.add(new HorizontalProductScrollModel(R.drawable.imagen_2, "Redmi A10", "SD 425 PROCESSOR", "6000/"));
        horizontalProductScrollModels.add(new HorizontalProductScrollModel(R.drawable.imagen_2, "Redmi A11", "SD 425 PROCESSOR", "6000/"));
        horizontalProductScrollModels.add(new HorizontalProductScrollModel(R.drawable.imagen_2, "Redmi A12", "SD 425 PROCESSOR", "6000/"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModels);
        LinearLayoutManager horizontalManager = new LinearLayoutManager(getContext());
        horizontalManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        h_s_product_recyclerView.setLayoutManager(horizontalManager);
        h_s_product_recyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();
        ////////// Horizontal product layout

        //////// Grid Product layout
        grid_product_title = view.findViewById(R.id.grid_product_layout_title);
        grid_product_btn = view.findViewById(R.id.grid_product_layout_btn);
        grid_product_gridView = view.findViewById(R.id.grid_product_layout_gridView);

        grid_product_gridView.setAdapter(new GridProductAdapter(horizontalProductScrollModels));

        //////// Grid Product layout


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

package com.hermosaprogramacion.premium.ecomerce;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView categoryRecycler;
    private CategoryAdapter adapter;

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

        return view;
    }

}

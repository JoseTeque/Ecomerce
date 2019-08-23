package com.hermosaprogramacion.premium.ecomerce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private List<HorizontalProductScrollModel> productScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> productScrollModelList) {
        this.productScrollModelList = productScrollModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = productScrollModelList.get(position).getProdutImage();
        String title = productScrollModelList.get(position).getProdutTitle();
        String description = productScrollModelList.get(position).getProdutDescription();
        String price = productScrollModelList.get(position).getProdutPrice();

        holder.setProduct_image(resource);
        holder.setTxt_product_title(title);
        holder.setTxt_product_description(description);
        holder.setTxt_product_price(price);

    }

    @Override
    public int getItemCount() {
        return productScrollModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView product_image;
        private TextView txt_product_title;
        private TextView txt_product_description;
        private TextView txt_product_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.h_s_product_image);
            txt_product_title = itemView.findViewById(R.id.h_s_product_title);
            txt_product_description = itemView.findViewById(R.id.h_s_product_description);
            txt_product_price = itemView.findViewById(R.id.h_s_product_price);
        }

        private void setProduct_image(int resourse){
            product_image.setImageResource(resourse);
        }

        private void setTxt_product_title(String title){
            txt_product_title.setText(title);
        }

        private void setTxt_product_description(String description){
            txt_product_description.setText(description);
        }

        private void setTxt_product_price(String price){
            txt_product_price.setText(price);
        }
    }
}

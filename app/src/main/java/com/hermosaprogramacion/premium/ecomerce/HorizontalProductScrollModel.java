package com.hermosaprogramacion.premium.ecomerce;

public class HorizontalProductScrollModel {
    private int produtImage;
    private String produtTitle;
    private String produtDescription;
    private String produtPrice;

    public HorizontalProductScrollModel(int produtImage, String produtTitle, String produtDescription, String produtPrice) {
        this.produtImage = produtImage;
        this.produtTitle = produtTitle;
        this.produtDescription = produtDescription;
        this.produtPrice = produtPrice;
    }

    public int getProdutImage() {
        return produtImage;
    }

    public void setProdutImage(int produtImage) {
        this.produtImage = produtImage;
    }

    public String getProdutTitle() {
        return produtTitle;
    }

    public void setProdutTitle(String produtTitle) {
        this.produtTitle = produtTitle;
    }

    public String getProdutDescription() {
        return produtDescription;
    }

    public void setProdutDescription(String produtDescription) {
        this.produtDescription = produtDescription;
    }

    public String getProdutPrice() {
        return produtPrice;
    }

    public void setProdutPrice(String produtPrice) {
        this.produtPrice = produtPrice;
    }
}

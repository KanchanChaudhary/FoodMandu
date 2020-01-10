package com.kanchan.foodmandu.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    static List<com.kanchan.foodmandu.ui.home.HomeViewModel> listcategory=new ArrayList<>();
    private String category;
    private int img;

    public HomeViewModel(String category, int img) {
        this.category = category;
        this.img = img;
    }

    public static List<com.kanchan.foodmandu.ui.home.HomeViewModel> getListcategory() {
        return listcategory;
    }

    public static void setListcategory(List<com.kanchan.foodmandu.ui.home.HomeViewModel> listcategory) {
        com.kanchan.foodmandu.ui.home.HomeViewModel.listcategory = listcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
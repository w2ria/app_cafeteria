package com.cafeteria.app_utp_cafeteria.presentation.viewmodel.ordenes;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OrderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Order fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package ru.rinet.questik.ui.chern;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChernViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChernViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("тут будет Черновик");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package net.kathir.myapplication.simpleLiveData;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class SimpleLiveDataModel extends ViewModel {

    private static final String TAG = SimpleLiveDataModel.class.getSimpleName();

    private MutableLiveData<List<String>> nameList;

    LiveData<List<String>> getNameList()
    {
        if(nameList == null)
        {
            nameList = new MutableLiveData<>();
            loadNames();
        }
        return nameList;
    }

    private void loadNames() {

        Handler mHandler = new Handler();
        mHandler.postDelayed(()-> {

            List<String> nameListValue = new ArrayList<>();
            nameListValue.add("Messi");
            nameListValue.add("Ronoldo");
            nameListValue.add("Pogba");
            nameListValue.add("Gerrard");
            nameListValue.add("Rooney");
            nameListValue.add("Lampard");
            nameListValue.add("Zidane");
            nameListValue.add("Beckham");
            nameListValue.add("Torres");
            nameListValue.add("Van Persie");
            nameListValue.add("Klose");
            long seed = System.nanoTime();
            Collections.shuffle(nameListValue, new Random(seed));

            nameList.setValue(nameListValue);

        },5000);
        }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }
    }



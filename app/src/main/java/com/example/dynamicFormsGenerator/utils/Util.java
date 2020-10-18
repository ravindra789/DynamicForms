package com.example.dynamicFormsGenerator.utils;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.dynamicFormsGenerator.featureModules.main.models.PastFormData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Util {

    private static Util instance;
    private Context mContext;
    private LoaderDialogFragment loaderDialogFragment;
    private CommonPreferences mCommonPreferences;

    public static Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null) {
                    instance = new Util();
                }
            }
        }
        return instance;
    }

    public void setContext(Context context, CommonPreferences commonPreferences) {
        mContext = context;
        mCommonPreferences = commonPreferences;
    }

    public void showLoadingDialog(FragmentActivity fragmentActivity) {
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        Fragment prev = fragmentActivity.getSupportFragmentManager().findFragmentByTag("loaderDialogFragment");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        loaderDialogFragment = new LoaderDialogFragment();
        loaderDialogFragment.show(fragmentActivity.getSupportFragmentManager(), "loaderDialogFragment");
    }

    public void dismissLoadingDialog() {
        if (loaderDialogFragment != null) {
            loaderDialogFragment.dismissAllowingStateLoss();
        }
    }

    public void setPastFormData(Context context, @Nullable List<PastFormData> data) {
        if (data == null) {
            mCommonPreferences.setPastFormList(null);
        } else {
            Gson gson = new Gson();
            String itemsList = gson.toJson(data);
            mCommonPreferences.setPastFormList(itemsList);
        }
    }

    public List<PastFormData> getPastFormData() {
        Gson gson = new Gson();
        String cartListJson = mCommonPreferences.getPastFormList();
        List<PastFormData> pastFormList = gson.fromJson(cartListJson, new TypeToken<List<PastFormData>>() {
        }.getType());

        if (pastFormList == null) {
            pastFormList = new ArrayList<>();
        }
        return pastFormList;
    }

    public String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
}

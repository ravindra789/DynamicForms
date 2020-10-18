package com.example.dynamicFormsGenerator.featureModules.main.repo;


import android.content.Context;

import com.example.dynamicFormsGenerator.utils.CommonPreferences;
import com.example.dynamicFormsGenerator.utils.Util;

/**
 * Created by ravindra on 18,January,2019
 */
public class MainActivityRepository {

    private static MainActivityRepository instance;


    private Util mUtil;
    private Context mContext;
    private CommonPreferences mPrefs;


    public static MainActivityRepository getInstance() {
        if (instance == null) {
            synchronized (MainActivityRepository.class) {
                if (instance == null) {
                    instance = new MainActivityRepository();
                }
            }
        }

        return instance;
    }

    public void setVariables(Util util, Context context, CommonPreferences prefs) {
        mUtil = util;
        mContext = context;
        mPrefs = prefs;
    }
}

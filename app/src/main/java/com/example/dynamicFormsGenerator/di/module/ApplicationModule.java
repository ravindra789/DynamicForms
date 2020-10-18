package com.example.dynamicFormsGenerator.di.module;

import android.app.Application;
import android.content.Context;

import com.example.dynamicFormsGenerator.utils.AnimationUtil;
import com.example.dynamicFormsGenerator.utils.CommonPreferences;
import com.example.dynamicFormsGenerator.utils.Util;
import com.example.dynamicFormsGenerator.utils.permissionManager.PermissionUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RavindraP on 18 October 2020
 */
@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Application context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public CommonPreferences providesSharedPreferences(Context context) {
        CommonPreferences commonPreferences = CommonPreferences.getInstance();
        commonPreferences.load(context);
        return commonPreferences;
    }


    @Provides
    @Singleton
    public Util provideUtility(Context context, CommonPreferences commonPreferences) {
        Util util = Util.getInstance();
        util.setContext(context, commonPreferences);
        return util;
    }

    @Provides
    @Singleton
    public AnimationUtil provideAnimation(Context context) {
        AnimationUtil animationUtil = AnimationUtil.getInstance();
        animationUtil.setContext(context);
        return animationUtil;
    }

    @Provides
    @Singleton
    public PermissionUtils providePermissionUtils() {
        PermissionUtils permissionUtils = PermissionUtils.getInstance();
        return permissionUtils;
    }
}

package com.example.dynamicFormsGenerator.di.component;

import android.content.Context;

import com.example.dynamicFormsGenerator.di.module.ApplicationModule;
import com.example.dynamicFormsGenerator.utils.AnimationUtil;
import com.example.dynamicFormsGenerator.utils.CommonPreferences;
import com.example.dynamicFormsGenerator.utils.Util;
import com.example.dynamicFormsGenerator.utils.permissionManager.PermissionUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by RavindraP on 18 October 2020
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context providesContext();

    CommonPreferences providesSharedPreferences();

    Util provideUtility();

    AnimationUtil provideAnimation();

    PermissionUtils providePermissionUtils();
}

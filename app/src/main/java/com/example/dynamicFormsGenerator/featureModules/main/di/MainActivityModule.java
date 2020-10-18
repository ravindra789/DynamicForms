package com.example.dynamicFormsGenerator.featureModules.main.di;

import android.content.Context;

import com.example.dynamicFormsGenerator.di.scopes.UserScope;
import com.example.dynamicFormsGenerator.featureModules.main.repo.MainActivityRepository;
import com.example.dynamicFormsGenerator.utils.CommonPreferences;
import com.example.dynamicFormsGenerator.utils.Util;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ravindra on 18,January,2019
 */
@Module
public class MainActivityModule {


    @Provides
    @UserScope
    MainActivityRepository provideHomeActivityRepository(Util util, Context context, CommonPreferences prefs) {
        MainActivityRepository homeActivityRepository = MainActivityRepository.getInstance();
        homeActivityRepository.setVariables(util, context, prefs);
        return homeActivityRepository;
    }
}

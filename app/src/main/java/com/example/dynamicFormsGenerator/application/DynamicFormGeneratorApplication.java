package com.example.dynamicFormsGenerator.application;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

import com.example.dynamicFormsGenerator.di.component.ApplicationComponent;
import com.example.dynamicFormsGenerator.di.component.DaggerApplicationComponent;
import com.example.dynamicFormsGenerator.di.module.ApplicationModule;

/**
 * Created by RavindraP on 18 October 2020
 */
public class DynamicFormGeneratorApplication extends MultiDexApplication {

    private static DynamicFormGeneratorApplication applicationContext;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ApplicationComponent mApplicationComponent;

    public static DynamicFormGeneratorApplication app() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}

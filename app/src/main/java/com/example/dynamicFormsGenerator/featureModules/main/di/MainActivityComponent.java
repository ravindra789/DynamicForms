package com.example.dynamicFormsGenerator.featureModules.main.di;


import com.example.dynamicFormsGenerator.di.component.ApplicationComponent;
import com.example.dynamicFormsGenerator.di.scopes.UserScope;
import com.example.dynamicFormsGenerator.featureModules.main.adapters.FillFormFragmentRecyclerAdapter;
import com.example.dynamicFormsGenerator.featureModules.main.adapters.FormReportRecyclerAdapter;
import com.example.dynamicFormsGenerator.featureModules.main.views.FillFormFragment;
import com.example.dynamicFormsGenerator.featureModules.main.views.FormOptionsFragment;
import com.example.dynamicFormsGenerator.featureModules.main.views.FormReportFragment;
import com.example.dynamicFormsGenerator.featureModules.main.views.MainActivity;
import com.example.dynamicFormsGenerator.featureModules.main.views.MainActivityViewModel;
import com.example.dynamicFormsGenerator.featureModules.main.views.SuccessFragment;

import dagger.Component;

/**
 * Created by ravindra on 18,January,2019
 */
@UserScope
@Component(dependencies = ApplicationComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity homeActivity);

    void inject(FillFormFragment fillFormFragment);

    void inject(FormReportFragment formReportFragment);

    void inject(SuccessFragment successFragment);

    void inject(FormOptionsFragment formOptionsFragment);

    void inject(MainActivityViewModel mainActivityViewModel);

    void inject(FillFormFragmentRecyclerAdapter fillFormFragmentRecyclerAdapter);

    void inject(FormReportRecyclerAdapter formReportRecyclerAdapter);
}

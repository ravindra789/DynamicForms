package com.example.dynamicFormsGenerator.featureModules.splash.di;


import com.example.dynamicFormsGenerator.di.component.ApplicationComponent;
import com.example.dynamicFormsGenerator.di.scopes.UserScope;
import com.example.dynamicFormsGenerator.featureModules.splash.SplashActivity;

import dagger.Component;


@UserScope
@Component(dependencies = ApplicationComponent.class, modules = SplashActivityModule.class)
public interface SplashActivityComponent {
    void inject(SplashActivity splashActivity);
}

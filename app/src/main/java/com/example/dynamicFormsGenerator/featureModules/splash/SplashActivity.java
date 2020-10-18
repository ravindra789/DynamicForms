package com.example.dynamicFormsGenerator.featureModules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.application.DynamicFormGeneratorApplication;
import com.example.dynamicFormsGenerator.databinding.ActivitySplashBinding;
import com.example.dynamicFormsGenerator.featureModules.login.LoginActivity;
import com.example.dynamicFormsGenerator.featureModules.main.views.MainActivity;
import com.example.dynamicFormsGenerator.featureModules.splash.di.DaggerSplashActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.splash.di.SplashActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.splash.di.SplashActivityModule;
import com.example.dynamicFormsGenerator.utils.CommonPreferences;

import javax.inject.Inject;

/**
 * Created by RavindraP on 18 October 2020
 */
public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Inject
    CommonPreferences prefs;

    private ActivitySplashBinding binding;
    private SplashActivityComponent splashActivityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        splashActivityComponent = DaggerSplashActivityComponent.builder()
                .applicationComponent(((DynamicFormGeneratorApplication) getApplication()).getApplicationComponent())
                .splashActivityModule(new SplashActivityModule())
                .build();

        splashActivityComponent.inject(this);

        scheduleSplashScreen();
    }

    private void scheduleSplashScreen() {
        new Handler().postDelayed(() -> checkLoginStatus(), SPLASH_TIME_OUT);
    }

    private void checkLoginStatus() {
        if (prefs.isFirstTimeLogin()) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            startActivity(intent);
            finish();
        }
    }
}

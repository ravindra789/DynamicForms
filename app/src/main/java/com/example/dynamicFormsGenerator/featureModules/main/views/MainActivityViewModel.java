package com.example.dynamicFormsGenerator.featureModules.main.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.dynamicFormsGenerator.featureModules.main.repo.MainActivityRepository;

import javax.inject.Inject;

/**
 * Created by RavindraP on 18 October 2020
 */
public class MainActivityViewModel extends AndroidViewModel {

    @Inject
    MainActivityRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }
}

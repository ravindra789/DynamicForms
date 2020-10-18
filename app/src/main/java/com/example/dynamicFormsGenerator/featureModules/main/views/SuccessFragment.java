package com.example.dynamicFormsGenerator.featureModules.main.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.application.DynamicFormGeneratorApplication;
import com.example.dynamicFormsGenerator.databinding.FragmentSuccessBinding;
import com.example.dynamicFormsGenerator.featureModules.main.di.DaggerMainActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.main.di.MainActivityComponent;

/**
 * Created by RavindraP on 18 October 2020
 */
public class SuccessFragment extends Fragment {

    private MainActivityViewModel viewModel;
    private MainActivityComponent mainActivityComponent;
    private FragmentSuccessBinding fragmentFillFormBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        fragmentFillFormBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_success, viewGroup, false);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(((DynamicFormGeneratorApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
        mainActivityComponent.inject(this);
        mainActivityComponent.inject(viewModel);

        setClickListeners();

        return fragmentFillFormBinding.getRoot();
    }

    private void setClickListeners() {
        fragmentFillFormBinding.btnBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }
}

package com.example.dynamicFormsGenerator.featureModules.main.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.application.DynamicFormGeneratorApplication;
import com.example.dynamicFormsGenerator.databinding.FragmentFormOptionsBinding;
import com.example.dynamicFormsGenerator.featureModules.main.di.DaggerMainActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.main.di.MainActivityComponent;

/**
 * Created by RavindraP on 18 October 2020
 */
public class FormOptionsFragment extends Fragment {

    private FragmentFormOptionsBinding fragmentFillFormBinding;
    private MainActivityViewModel viewModel;
    private MainActivityComponent mainActivityComponent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        fragmentFillFormBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_form_options, viewGroup, false);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(((DynamicFormGeneratorApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
        mainActivityComponent.inject(this);
        mainActivityComponent.inject(viewModel);

        setOnClicks();

        return fragmentFillFormBinding.getRoot();
    }

    private void setOnClicks() {
        fragmentFillFormBinding.btnCheckForm.setOnClickListener(v -> {
            FormReportFragment fragment = new FormReportFragment();
            addFragment(fragment);
        });

        fragmentFillFormBinding.btnFillForm.setOnClickListener(v -> {
            FillFormFragment fragment = new FillFormFragment();
            addFragment(fragment);
        });
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

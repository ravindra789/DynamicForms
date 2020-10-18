package com.example.dynamicFormsGenerator.featureModules.main.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.application.DynamicFormGeneratorApplication;
import com.example.dynamicFormsGenerator.databinding.FragmentFormReportBinding;
import com.example.dynamicFormsGenerator.featureModules.main.adapters.FormReportRecyclerAdapter;
import com.example.dynamicFormsGenerator.featureModules.main.di.DaggerMainActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.main.di.MainActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.main.models.PastFormData;
import com.example.dynamicFormsGenerator.utils.Util;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by RavindraP on 18 October 2020
 */
public class FormReportFragment extends Fragment {

    @Inject
    Util utils;

    private FragmentFormReportBinding fragmentFormReportBinding;
    private MainActivityViewModel viewModel;
    private MainActivityComponent mainActivityComponent;
    private FormReportRecyclerAdapter formReportRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        fragmentFormReportBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_form_report, viewGroup, false);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(((DynamicFormGeneratorApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
        mainActivityComponent.inject(this);
        mainActivityComponent.inject(viewModel);

        setUpData();

        return fragmentFormReportBinding.getRoot();
    }

    private void setUpData() {
        List<PastFormData> pastData = utils.getPastFormData();

        if (pastData.size() > 0) {
            fragmentFormReportBinding.recyclerView.setVisibility(View.VISIBLE);
            fragmentFormReportBinding.txtNoDataMsg.setVisibility(View.GONE);

            setAdapter(pastData);
        } else {
            fragmentFormReportBinding.recyclerView.setVisibility(View.GONE);
            fragmentFormReportBinding.txtNoDataMsg.setVisibility(View.VISIBLE);
        }
    }

    private void setAdapter(List<PastFormData> formsDataList) {
        fragmentFormReportBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        formReportRecyclerAdapter = new FormReportRecyclerAdapter(getActivity(), formsDataList);
        mainActivityComponent.inject(formReportRecyclerAdapter);
        fragmentFormReportBinding.recyclerView.setAdapter(formReportRecyclerAdapter);
    }
}
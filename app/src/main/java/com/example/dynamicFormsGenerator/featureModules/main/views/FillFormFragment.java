package com.example.dynamicFormsGenerator.featureModules.main.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.application.DynamicFormGeneratorApplication;
import com.example.dynamicFormsGenerator.databinding.FragmentFillFormBinding;
import com.example.dynamicFormsGenerator.featureModules.main.adapters.FillFormFragmentRecyclerAdapter;
import com.example.dynamicFormsGenerator.featureModules.main.di.DaggerMainActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.main.di.MainActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.main.interfaces.FormValidatorInterface;
import com.example.dynamicFormsGenerator.featureModules.main.models.DynamicFormData;
import com.example.dynamicFormsGenerator.featureModules.main.models.PastFormData;
import com.example.dynamicFormsGenerator.utils.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by RavindraP on 18 October 2020
 */
public class FillFormFragment extends Fragment implements FormValidatorInterface {

    @Inject
    Util utils;

    private FragmentFillFormBinding fragmentFillFormBinding;
    private MainActivityViewModel viewModel;
    private MainActivityComponent mainActivityComponent;
    private FillFormFragmentRecyclerAdapter fillFormFragmentRecyclerAdapter;
    private ArrayList<DynamicFormData> mFormsDataList;
    private ArrayList<DynamicFormData> mFormsAnswersDataList;
    private static String FILE_NAME = "formHistory.json";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        fragmentFillFormBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_fill_form, viewGroup, false);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(((DynamicFormGeneratorApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
        mainActivityComponent.inject(this);
        mainActivityComponent.inject(viewModel);

        setClickListeners();

        mFormsAnswersDataList = new ArrayList<>();

        String jsonFileString = utils.getJsonFromAssets(getActivity(), "form.json");
        Gson gson = new Gson();
        Type listFormType = new TypeToken<List<DynamicFormData>>() {
        }.getType();
        mFormsDataList = gson.fromJson(jsonFileString, listFormType);
        setAdapter(mFormsDataList);

        return fragmentFillFormBinding.getRoot();
    }

    private void setClickListeners() {
        fragmentFillFormBinding.btnSubmit.setOnClickListener(v -> {
            if (checkValidation()) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("Do you wish to save data?");
        dialog.setTitle("Save Data");
        dialog.setPositiveButton("YES",
                (dialog1, which) -> {
                    addDataToList();
                    replaceFragment();
                });
        dialog.setNegativeButton("cancel", (dialog12, which) -> dialog12.cancel());
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void addDataToList() {
        List<PastFormData> pastData = utils.getPastFormData();
        PastFormData pastFormData = new PastFormData();
        pastFormData.setName(mFormsAnswersDataList.get(0).getValueAdded());
        pastData.add(pastFormData);
        utils.setPastFormData(getActivity(), pastData);
    }

    private void replaceFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        SuccessFragment fragment = new SuccessFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setAdapter(ArrayList<DynamicFormData> formsDataList) {
        fragmentFillFormBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fillFormFragmentRecyclerAdapter = new FillFormFragmentRecyclerAdapter(getActivity(), formsDataList, this);
        mainActivityComponent.inject(fillFormFragmentRecyclerAdapter);
        fragmentFillFormBinding.recyclerView.setAdapter(fillFormFragmentRecyclerAdapter);
    }

    private boolean checkValidation() {
        for (int i = 0; i < mFormsAnswersDataList.size(); i++) {
            if (!mFormsAnswersDataList.get(i).isValidated() && mFormsAnswersDataList.get(i).isRequired()) {
                Toast.makeText(getActivity(), mFormsAnswersDataList.get(i).getErrorMsg(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    @Override
    public void onTextViewValidated(DynamicFormData dynamicFormData) {
    }

    @Override
    public void onEditTextViewValidated(DynamicFormData dynamicFormData) {
        if (!mFormsAnswersDataList.contains(dynamicFormData))
            mFormsAnswersDataList.add(dynamicFormData);
    }
}

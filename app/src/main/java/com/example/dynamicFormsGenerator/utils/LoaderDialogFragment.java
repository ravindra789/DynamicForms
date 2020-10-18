package com.example.dynamicFormsGenerator.utils;


import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.databinding.FragmentProgressLoaderBinding;


public class LoaderDialogFragment extends DialogFragment {

    private FragmentProgressLoaderBinding progressLoaderBinding;

    public static LoaderDialogFragment newInstance() {
        LoaderDialogFragment fragment = new LoaderDialogFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.AppTheme);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        progressLoaderBinding = FragmentProgressLoaderBinding.inflate(LayoutInflater.from(getActivity()));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        setCancelable(false);

        return progressLoaderBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}

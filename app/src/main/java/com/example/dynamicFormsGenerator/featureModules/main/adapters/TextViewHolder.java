package com.example.dynamicFormsGenerator.featureModules.main.adapters;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.featureModules.main.interfaces.FormValidatorInterface;
import com.example.dynamicFormsGenerator.featureModules.main.models.DynamicFormData;

/**
 * Created by RavindraP on 18 October 2020
 */
public class TextViewHolder extends RecyclerView.ViewHolder {

    private TextView txtName;

    TextViewHolder(@NonNull View itemView, FormValidatorInterface mFormValidatorInterface) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txt_field_name);
    }

    void setTextViewDetails(DynamicFormData dynamicFormData) {
        txtName.setText(dynamicFormData.getFieldName());
    }
}
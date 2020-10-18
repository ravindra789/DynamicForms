package com.example.dynamicFormsGenerator.featureModules.main.adapters;


import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.featureModules.main.interfaces.FormValidatorInterface;
import com.example.dynamicFormsGenerator.featureModules.main.models.DynamicFormData;

/**
 * Created by RavindraP on 18 October 2020
 */
public class EditTextViewHolder extends RecyclerView.ViewHolder {

    private AppCompatEditText editTextValue;
    private FormValidatorInterface mFormValidatorInterface;
    private DynamicFormData mDynamicFormData;

    EditTextViewHolder(@NonNull View itemView, FormValidatorInterface formValidatorInterface) {
        super(itemView);
        editTextValue = itemView.findViewById(R.id.edit_text_value);
        mFormValidatorInterface = formValidatorInterface;

        editTextValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if (s.length() >= mDynamicFormData.getMinLength()) {
                        if (mDynamicFormData.isValueValidate() && (Integer.parseInt(s.toString()) < mDynamicFormData.getMin() ||
                                Integer.parseInt(s.toString()) > mDynamicFormData.getMax())) {
                            mDynamicFormData.setValueAdded(s.toString());
                            mDynamicFormData.setErrorMsg("Please add data in the range.");
                            mDynamicFormData.setValidated(false);
                        } else {
                            mDynamicFormData.setValueAdded(s.toString());
                            mDynamicFormData.setErrorMsg("");
                            mDynamicFormData.setValidated(true);
                        }
                    } else {
                        mDynamicFormData.setValueAdded(s.toString());
                        mDynamicFormData.setErrorMsg("Please add minimum " + mDynamicFormData.getMin() + "chars");
                        mDynamicFormData.setValidated(false);
                    }
                } else {
                    mDynamicFormData.setValueAdded("");
                    mDynamicFormData.setErrorMsg("Please add data.");
                    mDynamicFormData.setValidated(false);
                }
                mFormValidatorInterface.onEditTextViewValidated(mDynamicFormData);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    void setEditTextViewDetails(DynamicFormData dynamicFormData) {
        mDynamicFormData = dynamicFormData;
        if (dynamicFormData.getInputType().equalsIgnoreCase("number")) {
            editTextValue.setInputType(InputType.TYPE_CLASS_NUMBER);
            editTextValue.setFilters(new InputFilter[]{new InputFilter.LengthFilter(dynamicFormData.getMaxLength())});
        } else if (dynamicFormData.getInputType().equalsIgnoreCase("multiline")) {
            editTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        } else {
            editTextValue.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }
}
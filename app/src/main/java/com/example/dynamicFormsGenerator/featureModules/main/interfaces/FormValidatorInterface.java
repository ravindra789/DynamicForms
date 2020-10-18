package com.example.dynamicFormsGenerator.featureModules.main.interfaces;

import com.example.dynamicFormsGenerator.featureModules.main.models.DynamicFormData;

/**
 * Created by RavindraP on 18 October 2020
 */
public interface FormValidatorInterface {
    public void onTextViewValidated(DynamicFormData dynamicFormData);

    public void onEditTextViewValidated(DynamicFormData dynamicFormData);
}

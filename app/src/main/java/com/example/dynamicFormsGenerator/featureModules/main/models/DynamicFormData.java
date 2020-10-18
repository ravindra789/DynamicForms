package com.example.dynamicFormsGenerator.featureModules.main.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DynamicFormData {

    @SerializedName("question_number")
    @Expose
    private int questionNumber;

    @SerializedName("field_name")
    @Expose
    private String fieldName;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("required")
    @Expose
    private boolean required;

    @SerializedName("input_type")
    @Expose
    private String inputType;

    @SerializedName("min")
    @Expose
    private int min;

    @SerializedName("minLength")
    @Expose
    private int minLength;

    @SerializedName("max")
    @Expose
    private int max;

    @SerializedName("maxLength")
    @Expose
    private int maxLength;

    @SerializedName("value_added")
    @Expose
    private String valueAdded;

    @SerializedName("isValueValidate")
    @Expose
    private boolean isValueValidate;

    @SerializedName("error_msg")
    @Expose
    private String errorMsg;

    @SerializedName("isValidated")
    @Expose
    private boolean isValidated;

    public boolean isValueValidate() {
        return isValueValidate;
    }

    public void setValueValidate(boolean valueValidate) {
        isValueValidate = valueValidate;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getValueAdded() {
        return valueAdded;
    }

    public void setValueAdded(String valueAdded) {
        this.valueAdded = valueAdded;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
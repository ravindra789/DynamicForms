package com.example.dynamicFormsGenerator.featureModules.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.featureModules.main.interfaces.FormValidatorInterface;
import com.example.dynamicFormsGenerator.featureModules.main.models.DynamicFormData;

import java.util.ArrayList;


/**
 * Created by RavindraP on 18 October 2020
 */
public class FillFormFragmentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TEXT_VIEW = 1;
    private static int EDIT_TEXT_VIEW = 2;
    private Context mContext;
    private ArrayList<DynamicFormData> mFormsDataList;
    private FormValidatorInterface mFormValidatorInterface;

    public FillFormFragmentRecyclerAdapter(Context context, ArrayList<DynamicFormData> formsDataList,
                                           FormValidatorInterface formValidatorInterface) {
        mContext = context;
        mFormsDataList = formsDataList;
        mFormValidatorInterface = formValidatorInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == TEXT_VIEW) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_text_view, viewGroup, false);
            return new TextViewHolder(view, mFormValidatorInterface);
        } else if (viewType == EDIT_TEXT_VIEW) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_edit_text_view, viewGroup, false);
            return new EditTextViewHolder(view, mFormValidatorInterface);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_text_view, viewGroup, false);
            return new TextViewHolder(view, mFormValidatorInterface);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TEXT_VIEW) {
            ((TextViewHolder) viewHolder).setTextViewDetails(mFormsDataList.get(position));
        } else if (getItemViewType(position) == EDIT_TEXT_VIEW) {
            ((EditTextViewHolder) viewHolder).setEditTextViewDetails(mFormsDataList.get(position));
        } else {
            ((TextViewHolder) viewHolder).setTextViewDetails(mFormsDataList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mFormsDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mFormsDataList.get(position).getType().equalsIgnoreCase("text")) {
            return TEXT_VIEW;
        } else if (mFormsDataList.get(position).getType().equalsIgnoreCase("edit_text")) {
            return EDIT_TEXT_VIEW;
        } else {
            return TEXT_VIEW;
        }
    }
}

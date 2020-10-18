package com.example.dynamicFormsGenerator.featureModules.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamicFormsGenerator.databinding.AdapterFormReportBinding;
import com.example.dynamicFormsGenerator.featureModules.main.models.PastFormData;

import java.util.List;

/**
 * Created by RavindraP on 18 October 2020
 */
public class FormReportRecyclerAdapter extends RecyclerView.Adapter<FormReportRecyclerAdapter.MyViewHolder> {

    private List<PastFormData> mPastData;
    private Context mContext;

    public FormReportRecyclerAdapter(Context context, List<PastFormData> pastData) {
        mContext = context;
        mPastData = pastData;
    }

    @NonNull
    @Override
    public FormReportRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterFormReportBinding binding = AdapterFormReportBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FormReportRecyclerAdapter.MyViewHolder holder, int position) {
        holder.binding.txtName.setText(mPastData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mPastData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AdapterFormReportBinding binding;

        public MyViewHolder(AdapterFormReportBinding recyclerAdapterHomeActivityBinding) {
            super(recyclerAdapterHomeActivityBinding.getRoot());
            binding = recyclerAdapterHomeActivityBinding;
        }
    }
}

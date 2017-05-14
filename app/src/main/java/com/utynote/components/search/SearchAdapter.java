package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.utynote.databinding.SearchItemViewBinding;
import com.utynote.utils.Sequences;

import java.util.ArrayList;
import java.util.List;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    @NonNull private List<SearchItemModel> data = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SearchItemViewBinding binding = SearchItemViewBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(@Nullable Iterable<SearchItemModel> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(Sequences.map(data));
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull private final SearchItemViewBinding mBinding;

        public ViewHolder(@NonNull SearchItemViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull SearchItemModel model) {
            mBinding.setModel(model);
        }
    }
}

package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.utynote.databinding.SearchViewItemBinding;
import com.utynote.utils.Sequences;

import java.util.ArrayList;
import java.util.List;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    @NonNull private List<SearchItemData> data = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SearchViewItemBinding binding = SearchViewItemBinding.inflate(layoutInflater, parent, false);
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

    public void setData(@NonNull SearchViewModel.Data model) {
        this.data.clear();
        this.data.addAll(Sequences.asCollection(model.data));
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull private final SearchViewItemBinding mBinding;

        public ViewHolder(@NonNull SearchViewItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull SearchItemData model) {
            mBinding.setModel(model);
        }
    }
}

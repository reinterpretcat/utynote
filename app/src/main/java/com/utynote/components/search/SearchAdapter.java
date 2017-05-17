package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.utynote.R;
import com.utynote.databinding.SearchViewBusyBinding;
import com.utynote.databinding.SearchViewErrorBinding;
import com.utynote.databinding.SearchViewItemBinding;
import com.utynote.utils.Sequences;

import rx.functions.Action1;
import rx.functions.Func1;

import static com.utynote.utils.Preconditions.checkNotNull;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    @NonNull private final VisitorFunctions<Integer> viewType = new VisitorFunctions<>(
            (model) -> R.layout.search_view_busy,
            (model) -> R.layout.search_view_error,
            (model) -> R.layout.search_view_item);

    @NonNull private final VisitorFunctions<Integer> viewCount = new VisitorFunctions<>(
            (model) -> 1,
            (model) -> 1,
            (model) -> Sequences.size(model.data));

    @Nullable private SearchViewModel.Abstract model;

    @Override
    public int getItemViewType(int position) {
        return viewType.call(getModel());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == R.layout.search_view_busy) {
            return new ViewHolder(SearchViewBusyBinding.inflate(layoutInflater, parent, false));
        }

        if (viewType == R.layout.search_view_error) {
            return new ViewHolder(SearchViewErrorBinding.inflate(layoutInflater, parent, false));
        }

        return new ViewHolder(SearchViewItemBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        getModel().accept(new VisitorActions(
                model -> holder.bind(),
                model -> holder.bind(model.description),
                model -> holder.bind(model.data.get(holder.getAdapterPosition())))
            .visitor);
    }

    @Override
    public int getItemCount() {
        return viewCount.call(getModel());
    }

    public void setData(@NonNull SearchViewModel.Abstract model) {
        this.model = model;
        notifyDataSetChanged();
    }

    @NonNull
    private SearchViewModel.Abstract getModel() {
        return checkNotNull(model);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable private SearchViewErrorBinding mErrorBinding;
        @Nullable private SearchViewItemBinding mItemBinding;

        public ViewHolder(@NonNull SearchViewBusyBinding binding) {
            super(binding.getRoot());
        }

        public ViewHolder(@NonNull SearchViewErrorBinding binding) {
            super(binding.getRoot());
            mErrorBinding = binding;
        }

        public ViewHolder(@NonNull SearchViewItemBinding binding) {
            super(binding.getRoot());
            mItemBinding = binding;
        }

        public void bind() { }

        public void bind(@NonNull String model) {
            checkNotNull(mErrorBinding).setModel(model);
        }

        public void bind(@NonNull SearchItemData model) {
            checkNotNull(mItemBinding).setModel(model);
        }
    }

    private static final class VisitorActions {
        @NonNull private final SearchViewModel.Visitor visitor;

        VisitorActions(@NonNull Action1<SearchViewModel.Busy> onBusy,
                       @NonNull Action1<SearchViewModel.Error> onError,
                       @NonNull Action1<SearchViewModel.Data> onData) {
            visitor = new SearchViewModel.Visitor() {
                @Override
                public void visit(@NonNull SearchViewModel.Busy model) {
                    onBusy.call(model);
                }

                @Override
                public void visit(@NonNull SearchViewModel.Error model) {
                    onError.call(model);
                }

                @Override
                public void visit(@NonNull SearchViewModel.Data model) {
                    onData.call(model);
                }
            };
        }

        void call(@NonNull SearchViewModel.Abstract model) {
            model.accept(visitor);
        }
    }

    private static final class VisitorFunctions<T> {
        private T result;
        private final VisitorActions actions;

        VisitorFunctions(@NonNull Func1<SearchViewModel.Busy, T> onBusy,
                         @NonNull Func1<SearchViewModel.Error, T> onError,
                         @NonNull Func1<SearchViewModel.Data, T> onData) {

            actions = new VisitorActions(
                    (model) -> result = onBusy.call(model),
                    (model) -> result = onError.call(model),
                    (model) -> result = onData.call(model));
        }

        public T call(@NonNull SearchViewModel.Abstract model) {
            actions.call(model);
            return result;
        }
    }
}

package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;

import com.utynote.components.search.data.SearchRepository;
import com.utynote.utils.Sequences;

import java.util.ArrayList;

import static com.utynote.utils.Preconditions.checkNotNull;

public class SearchPresenter implements SearchContract.Presenter {

    @NonNull private final SearchRepository mRepository;
    @Nullable private SearchContract.View mView;

    public SearchPresenter(@NonNull SearchRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void attach(@NonNull SearchContract.View view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void search(@NonNull String term) {
        mRepository
                .search(term)
                .map(r -> SearchItemModel.getBuilder()
                            .withPrimaryTitle(SpannableString.valueOf(r.name))
                            .withPrimarySubtitle(SpannableString.valueOf(r.country))
                            .withSecondarySubtitle(SpannableString.valueOf(r.coordinate.toString()))
                            .build())
                .reduce(new ArrayList<SearchItemModel>(), Sequences::merge)
                .subscribe(getView()::showResults, ex -> getView().showError(formatError(ex)));
    }

    @NonNull
    private SearchContract.View getView() {
        return checkNotNull(mView);
    }

    @NonNull
    private String formatError(Throwable tr) {
        return tr.getMessage();
    }
}

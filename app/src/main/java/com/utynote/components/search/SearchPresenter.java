package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.utynote.components.search.data.SearchProcessor;
import com.utynote.components.search.data.SearchResult;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.utynote.utils.Preconditions.checkNotNull;

public class SearchPresenter implements SearchContract.Presenter {

    @Nullable private SearchContract.View view;
    @Nullable private Disposable subscription;
    @NonNull private final SearchProcessor processor;

    public SearchPresenter(@NonNull SearchProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void attach(@NonNull SearchContract.View view) {
        this.view = view;
        this.subscription = Observable
                .fromPublisher(processor)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError);
    }

    @Override
    public void detach() {
        checkNotNull(subscription).dispose();
        view = null;
    }

    @Override
    public void search(@NonNull String term) {
        processor.onNext(term);
    }

    @NonNull
    private SearchContract.View getView() {
        return checkNotNull(view);
    }

    private void onNext(@NonNull SearchResult searchResult) {
        getView().render(new SearchViewModel.Data(Observable.fromIterable(searchResult.places)
                .map(r -> SearchItemData.getBuilder()
                        .withPrimaryTitle(r.name)
                        .withSecondaryTitle("")
                        .withPrimarySubtitle(r.country)
                        .withSecondarySubtitle(r.coordinate.toString())
                        .build())
                .toList()
                .blockingGet()));
    }

    private void onError(@NonNull Throwable t) {
        getView().render(new SearchViewModel.Error(t.getMessage()));
    }
}

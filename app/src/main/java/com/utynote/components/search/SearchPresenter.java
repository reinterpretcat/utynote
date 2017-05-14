package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.utynote.components.search.data.SearchProcessor;
import com.utynote.components.search.data.SearchResult;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;

import static com.utynote.utils.Preconditions.checkNotNull;

public class SearchPresenter implements SearchContract.Presenter {

    @Nullable private SearchContract.View view;
    @Nullable private Subscription subscription;
    @NonNull private final SearchProcessor processor;

    public SearchPresenter(@NonNull SearchProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void attach(@NonNull SearchContract.View view) {
        this.view = view;
        processor.subscribe(createSubscriber());
    }

    @Override
    public void detach() {
        checkNotNull(subscription).cancel();
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

    @NonNull
    private Subscriber<SearchResult> createSubscriber() {
        return new Subscriber<SearchResult>() {
            @Override
            public void onSubscribe(Subscription s) {
                subscription = s;
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(SearchResult searchResult) {
                getView().showResults(Observable.fromIterable(searchResult.places)
                        .map(r -> SearchItemModel.getBuilder()
                                .withPrimaryTitle(r.name)
                                .withSecondaryTitle("")
                                .withPrimarySubtitle(r.country)
                                .withSecondarySubtitle(r.coordinate.toString())
                                .build())
                        .toList()
                        .blockingGet());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(t.getMessage());
            }

            @Override
            public void onComplete() {
            }
        };
    }
}

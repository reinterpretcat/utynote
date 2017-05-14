package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;

import com.utynote.components.search.data.SearchProcessor;
import com.utynote.components.search.data.SearchResult;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;

import static com.utynote.utils.Preconditions.checkNotNull;

public class SearchPresenter implements SearchContract.Presenter {

    @Nullable private SearchContract.View mView;
    @Nullable private Subscription mSubscription;
    @NonNull private final SearchProcessor mProcessor;

    public SearchPresenter(@NonNull SearchProcessor processor) {
        mProcessor = processor;
    }

    @Override
    public void attach(@NonNull SearchContract.View view) {
        mView = view;
        mProcessor.subscribe(createSubscriber());
    }

    @Override
    public void detach() {
        checkNotNull(mSubscription).cancel();
        mView = null;
    }

    @Override
    public void search(@NonNull String term) {
        mProcessor.onNext(term);
    }

    @NonNull
    private SearchContract.View getView() {
        return checkNotNull(mView);
    }

    @NonNull
    private Subscriber<SearchResult> createSubscriber() {
        return new Subscriber<SearchResult>() {
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(SearchResult searchResult) {
                getView().showResults(Observable.fromIterable(searchResult.places)
                        .map(r -> SearchItemModel.getBuilder()
                                .withPrimaryTitle(new SpannableString(r.name))
                                .withPrimarySubtitle(new SpannableString(r.country))
                                .withSecondarySubtitle(new SpannableString(r.coordinate.toString()))
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

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

    @NonNull private final SearchProcessor mProcessor;
    @Nullable private SearchContract.View mView;

    @NonNull private final Subscriber<SearchResult> mSubscriber = new Subscriber<SearchResult>() {
        @Override
        public void onSubscribe(Subscription s) {
        }

        @Override
        public void onNext(SearchResult searchResult) {
            Iterable<SearchItemModel> results = Observable.fromIterable(searchResult.places)
                    .map(r -> SearchItemModel.getBuilder()
                    .withPrimaryTitle(new SpannableString(r.name))
                    .withPrimarySubtitle(new SpannableString(r.country))
                    .withSecondarySubtitle(new SpannableString(r.coordinate.toString()))
                    .build())
                    .toList()
                    .blockingGet();
            getView().showResults(results);
        }

        @Override
        public void onError(Throwable t) {
            getView().showError(t.getMessage());
        }

        @Override
        public void onComplete() {
        }
    };

    public SearchPresenter(@NonNull SearchProcessor processor) {
        mProcessor = processor;
        mProcessor.subscribe(mSubscriber);
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
        mProcessor.onNext(term);
    }

    @NonNull
    private SearchContract.View getView() {
        return checkNotNull(mView);
    }
}

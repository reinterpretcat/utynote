package com.utynote.components.search.model.geojson;


import com.utynote.components.search.model.SearchResult;
import com.utynote.test.annotations.HttpResponse;
import com.utynote.test.core.AnnotationHandler;

import org.junit.Rule;
import org.junit.Test;

import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class JsonSearchRepositoryTest {

    public @Rule final AnnotationHandler mRule = new AnnotationHandler();

    JsonSearchRepository mRepository;

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    public void search_hasExpectedResult() {
        TestSubscriber<SearchResult> subscriber = new TestSubscriber<>();

        mRepository
                .search("Berlin")
                .subscribe(subscriber);

        assertThat(subscriber.getOnNextEvents(), hasItem(SearchResult
                .getBuilder()
                .build()));
    }
}

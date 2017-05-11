package com.utynote.components.search.model.geojson;


import com.utynote.components.search.model.SearchRepository;
import com.utynote.components.search.model.SearchResult;
import com.utynote.test.annotations.HttpResponse;
import com.utynote.test.core.AnnotationHandler;
import com.utynote.test.dependencies.SearchComponentFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class SearchRepositoryTest {

    @Rule public final AnnotationHandler mRule = new AnnotationHandler();
    @Inject public SearchRepository mRepository;

    @Before
    public void setup() {
        SearchComponentFactory
                .create("https://search.mapzen.com/v1/", "geojson")
                .inject(this);
    }

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

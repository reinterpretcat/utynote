package com.utynote.components.search.data.geojson;


import com.utynote.components.search.data.SearchProcessor;
import com.utynote.components.search.data.SearchResult;
import com.utynote.entities.GeoCoordinate;
import com.utynote.entities.Place;
import com.utynote.test.annotations.HttpResponse;
import com.utynote.test.core.AnnotationHandler;
import com.utynote.test.dependencies.SearchComponentFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import io.reactivex.subscribers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class SearchProcessorTest {

    @Rule public final AnnotationHandler mRule = new AnnotationHandler();
    @Inject public SearchProcessor mProcessor;

    @Before
    public void setup() {
        SearchComponentFactory.create().inject(this);
    }

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    public void search_hasExpectedResult() {
        TestSubscriber<SearchResult> subscriber = new TestSubscriber<>();
        mProcessor.subscribe(subscriber);

        mProcessor.onNext("Berlin");

        subscriber.assertNoErrors();
        assertThat(subscriber.valueCount(), equalTo(1));
        assertThat(subscriber.values().get(0).places, hasItem(Place.getBuilder()
                .withId("101748799")
                .withName("Berlin")
                .withCountry("Germany")
                .withGeoCoordinate(new GeoCoordinate(52.52045, 13.40732))
                .build()));
    }
}

package com.utynote.components.search;

import com.utynote.test.annotations.HttpResponse;
import com.utynote.test.core.AnnotationHandler;
import com.utynote.test.dependencies.SearchComponentFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasSize;

public class SearchPresenterTest {
    @Rule public final AnnotationHandler mRule = new AnnotationHandler();
    @Inject public SearchPresenter mPresenter;

    private FakeSearchView mView;

    @Before
    public void setup() {
        SearchComponentFactory.create().inject(this);
        mView = new FakeSearchView();
    }

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    public void whenAttached_search_hasExpectedResult() {
        mPresenter.attach(mView);

        mPresenter.search("Berlin");

        assertThat(mView.errors, emptyIterable());
        assertThat(mView.results, hasSize(1));
    }

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    public void whenDetached_search_hasNoResults() {
        mPresenter.attach(mView);
        mPresenter.detach();

        mPresenter.search("Berlin");

        assertThat(mView.results, emptyIterable());
        assertThat(mView.errors, emptyIterable());
    }
}

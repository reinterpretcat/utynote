package com.utynote.components.search;

import android.support.annotation.NonNull;

import com.utynote.test.annotations.HttpResponse;
import com.utynote.test.core.AnnotationHandler;
import com.utynote.test.dependencies.SearchComponentFactory;
import com.utynote.test.utils.Utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasItem;
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
        assertThat(mView.results.get(0), hasItem(new SearchItemMatcher(SearchItemModel.getBuilder()
                .withPrimaryTitle("Berlin")
                .withSecondaryTitle("")
                .withPrimarySubtitle("Germany")
                .withSecondarySubtitle("52.52045,13.40732")
                .build())));
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

    private final class SearchItemMatcher extends TypeSafeMatcher<SearchItemModel> {
        @NonNull private final SearchItemModel mModel;

        SearchItemMatcher(@NonNull SearchItemModel model) {
            mModel = model;
        }

        @Override
        protected boolean matchesSafely(SearchItemModel item) {
            return Utils.equals(mModel.primaryTitle, item.primaryTitle) &&
                   Utils.equals(mModel.secondaryTitle, item.secondaryTitle) &&
                   Utils.equals(mModel.primarySubtitle, item.primarySubtitle) &&
                   Utils.equals(mModel.secondarySubtitle, item.secondarySubtitle);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Search item with title: " + mModel.primaryTitle);
        }
    }
}

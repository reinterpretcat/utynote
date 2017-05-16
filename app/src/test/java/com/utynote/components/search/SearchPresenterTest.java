package com.utynote.components.search;

import android.support.annotation.NonNull;

import com.utynote.test.annotations.HttpResponse;
import com.utynote.test.core.AnnotationHandler;
import com.utynote.test.dependencies.SearchComponentFactory;
import com.utynote.test.utils.Utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ImmediateThinScheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class SearchPresenterTest {
    @Rule public final AnnotationHandler rule = new AnnotationHandler();
    @Inject public SearchPresenter presenter;

    private FakeSearchView view;

    @Before
    public void setup() {
        SearchComponentFactory.create().inject(this);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler ->
                ImmediateThinScheduler.INSTANCE);
        view = new FakeSearchView();
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.reset();
    }

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    public void whenAttached_search_hasExpectedResult() {
        presenter.attach(view);

        presenter.search("Berlin");

        assertThat(view.errors, emptyIterable());
        assertThat(view.results, hasSize(1));
        assertThat(view.results.get(0).data, hasItem(match(SearchItemData.getBuilder()
                .withPrimaryTitle("Berlin")
                .withSecondaryTitle("")
                .withPrimarySubtitle("Germany")
                .withSecondarySubtitle("52.52045,13.40732")
                .build())));
    }

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    public void whenDetached_search_hasNoResults() {
        presenter.attach(view);
        presenter.detach();

        presenter.search("Berlin");

        assertThat(view.results, emptyIterable());
        assertThat(view.errors, emptyIterable());
    }

    @NonNull
    private TypeSafeMatcher<SearchItemData> match(@NonNull final SearchItemData model) {
        return new TypeSafeMatcher<SearchItemData>() {
            @Override
            protected boolean matchesSafely(SearchItemData item) {
                return Utils.equals(model.primaryTitle, item.primaryTitle) &&
                        Utils.equals(model.secondaryTitle, item.secondaryTitle) &&
                        Utils.equals(model.primarySubtitle, item.primarySubtitle) &&
                        Utils.equals(model.secondarySubtitle, item.secondarySubtitle);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Search item with title: " + model.primaryTitle);
            }
        };
    }
}

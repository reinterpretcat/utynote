package com.utynote.components.search

import com.utynote.test.annotations.HttpResponse
import com.utynote.test.core.AnnotationHandler
import com.utynote.test.dependencies.SearchComponentFactory
import com.utynote.test.utils.Utils

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import javax.inject.Inject

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ImmediateThinScheduler

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.emptyIterable
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.hasSize

class SearchPresenterTest {
    @Rule @JvmField val rule = AnnotationHandler()
    @Inject lateinit var presenter: SearchPresenter

    private var view: FakeSearchView? = null

    @Before
    fun setup() {
        SearchComponentFactory.create().inject(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { ImmediateThinScheduler.INSTANCE }
        view = FakeSearchView()
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    fun whenAttached_search_hasExpectedResult() {
        presenter.attach(view!!)

        presenter.search("Berlin")

        assertThat<List<SearchViewModel.Error>>(view!!.errors, emptyIterable<Any>())
        assertThat<List<SearchViewModel.Data>>(view!!.results, hasSize<Any>(1))
        assertThat(view!!.results[0].data, hasItem(match(SearchItemData(
                primaryTitle = "Berlin",
                secondaryTitle = "",
                primarySubtitle = "Germany",
                secondarySubtitle = "52.52045,13.40732"))))
    }

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    fun whenDetached_search_hasNoResults() {
        presenter.attach(view!!)
        presenter.detach()

        presenter.search("Berlin")

        assertThat<List<SearchViewModel.Data>>(view!!.results, emptyIterable<Any>())
        assertThat<List<SearchViewModel.Error>>(view!!.errors, emptyIterable<Any>())
    }

    private fun match(model: SearchItemData): TypeSafeMatcher<SearchItemData> {
        return object : TypeSafeMatcher<SearchItemData>() {
            override fun matchesSafely(item: SearchItemData): Boolean {
                return Utils.equals(model.primaryTitle, item.primaryTitle) &&
                        Utils.equals(model.secondaryTitle, item.secondaryTitle) &&
                        Utils.equals(model.primarySubtitle, item.primarySubtitle) &&
                        Utils.equals(model.secondarySubtitle, item.secondarySubtitle)
            }

            override fun describeTo(description: Description) {
                description.appendText("Search item with title: " + model.primaryTitle)
            }
        }
    }
}

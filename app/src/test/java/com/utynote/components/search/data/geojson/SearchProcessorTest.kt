package com.utynote.components.search.data.geojson


import com.utynote.components.search.data.SearchProcessor
import com.utynote.components.search.data.SearchResult
import com.utynote.entities.GeoCoordinate
import com.utynote.entities.Place
import com.utynote.test.annotations.HttpResponse
import com.utynote.test.core.AnnotationHandler
import com.utynote.test.dependencies.SearchComponentFactory
import io.reactivex.subscribers.TestSubscriber
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class SearchProcessorTest {

    @Rule @JvmField val mRule = AnnotationHandler()
    @Inject lateinit var mProcessor: SearchProcessor

    @Before
    fun setup() {
        SearchComponentFactory.create().inject(this)
    }

    @HttpResponse(path = "search/geojson/berlin")
    @Test
    fun search_hasExpectedResult() {
        val subscriber = TestSubscriber<SearchResult>()
        mProcessor.subscribe(subscriber)

        mProcessor.onNext("Berlin")

        subscriber.assertNoErrors()
        assertThat(subscriber.valueCount(), equalTo(1))
        assertThat(subscriber.values()[0].places, hasItem(Place(
                id = "101748799",
                name = "Berlin",
                country = "Germany",
                coordinate = GeoCoordinate(52.52045, 13.40732))))
    }
}

package com.syntax.mvp.utama

import com.nhaarman.mockito_kotlin.verify
import com.syntax.mvp.BuildConfig
import com.syntax.mvp.model.ResponseMovies
import com.syntax.mvp.model.ResultsItem
import com.syntax.mvp.network.ApiService
import com.syntax.mvp.utils.SchedulerProvider
import com.syntax.mvp.utils.TestScheduler
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MoviesPresenterTest {

    @Mock
    private lateinit var view: MoviesView

    @Mock
    private lateinit var data: ResponseMovies

    @Mock
    private lateinit var apiService: ApiService

    lateinit var dataMovies: Observable<ResponseMovies>

    private val dataItem = mutableListOf<ResultsItem>()

    private lateinit var presenter: MoviesPresenter

    private lateinit var scheduler: SchedulerProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestScheduler()
        data = ResponseMovies(dataItem)
        dataMovies = Observable.just(data)
        presenter = MoviesPresenter(scheduler)
        Mockito.`when`(apiService.getUpcomingMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE)).thenReturn(dataMovies)
    }

    @Test
    fun fetchDataUpComing() {
        presenter.fetchDataUpComing(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
        verify(view).showDataMovies(dataItem)
    }
}
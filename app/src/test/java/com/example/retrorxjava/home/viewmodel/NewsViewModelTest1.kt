package com.example.retrorxjava.home.viewmodel


import com.example.retrorxjava.RxImmediateSchedulerRule
import com.example.retrorxjava.home.network.HackerNewsApi
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.OngoingStubbing
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.retrorxjava.home.helper.UseCaseResult
import com.example.retrorxjava.home.di.hackerModule
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.koin.dsl.koinApplication
import com.example.retrorxjava.home.model.NewsResponse as BookResponse1


@Suppress("CAST_NEVER_SUCCEEDS", "ANNOTATION_TARGETS_NON_EXISTENT_ACCESSOR")
class NewsViewModelTest1 : KoinTest {


    private val homeViewModelTest: HomeViewModel by inject()
    @Mock
    lateinit var hackerNewsApi: HackerNewsApi

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()


    companion object {
        @ClassRule
        @JvmField
        @get:Rule
        val schedulers = RxImmediateSchedulerRule()
    }


    @Before
    @Test
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        stopKoin()
        startKoin {
            modules(
                module {
                    single {
                        HomeViewModel(
                            hackerNewsApi = hackerNewsApi
                        )
                    }
                }
            )
        }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }


    }

    @After
    @Test
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun checkModule() {
        koinApplication { hackerModule }
        Assert.assertNotNull(hackerModule)

    }


    @Test
    fun `check api calls OnSuccess`() {
        val trendingData = BookResponse1()
        whenever(hackerNewsApi.getNews()).thenReturn(Observable.just(trendingData))

        homeViewModelTest.loadNewsData()



    }

    @Test
    fun `check api calls OnError`() {
        whenever(hackerNewsApi.getNews()).thenReturn(Observable.error(Throwable("error")))

        homeViewModelTest.loadNewsData()


    }


}


private inline fun <reified T> mock(): Int? = Mockito.mock(T::class.java)
fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)
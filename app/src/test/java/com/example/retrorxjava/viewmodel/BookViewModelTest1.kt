package com.example.retrorxjava.viewmodel


import com.example.retrorxjava.RxImmediateSchedulerRule
import com.example.retrorxjava.model.Book
import com.example.retrorxjava.network.Api
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.OngoingStubbing
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.retrorxjava.di.retrofitModule
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.koin.dsl.koinApplication
import org.mockito.Mockito.verify
import com.example.retrorxjava.model.BookResponse as BookResponse1


@Suppress("CAST_NEVER_SUCCEEDS", "ANNOTATION_TARGETS_NON_EXISTENT_ACCESSOR")
class BookViewModelTest1 : KoinTest {


    private val bookViewModelTest: BookViewModel by inject()
    @Mock
    lateinit var api: Api
    @Mock
    lateinit var apiresult: Apiresult
    @Mock
    private lateinit var compositeDisposable: CompositeDisposable
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
                        BookViewModel(
                            api = api
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
        koinApplication { retrofitModule }
        Assert.assertNotNull(retrofitModule)
    }



    @Test
    fun `check api calls OnSuccess`() {
        //Arrange
        val trendingData = BookResponse1()
        whenever(api.getMovies()).thenReturn(Observable.just(trendingData))

        // ACt
        bookViewModelTest.response(apiresult)
        // Assert
        verify(apiresult).onSucess(trendingData)


    }
    @Test
    fun `check api calls OnError`() {
        //Arrange
        whenever(api.getMovies()).thenReturn(Observable.error(Throwable("error")))

        // ACt
        bookViewModelTest.response(apiresult)
        // Assert
        verify(apiresult).onError("error")


    }

}


private inline fun <reified T> mock(): Int? = Mockito.mock(T::class.java)
fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)
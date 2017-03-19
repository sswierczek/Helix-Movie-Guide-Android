package com.androidmess.helix.discovery.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.androidmess.helix.BuildConfig
import com.androidmess.helix.R
import com.androidmess.helix.common.model.data.MovieResult
import com.androidmess.helix.discovery.model.repository.RetrofitDiscoverRepository
import com.androidmess.helix.discovery.presentation.DiscoveryPresenter
import com.androidmess.helix.discovery.usecase.GetDiscoveryMoviesUseCase
import kotlinx.android.synthetic.main.activity_discover.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DiscoverActivity : AppCompatActivity(), DiscoverView {

    var presenter: DiscoveryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        // FIXME Add DI !!!
        val baseUrl = BuildConfig.BASE_URL
        val apiKey = BuildConfig.API_KEY
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY;
        val okClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val repository = RetrofitDiscoverRepository(retrofit, apiKey)
        presenter = DiscoveryPresenter(GetDiscoveryMoviesUseCase(repository))

        // FIXME Add Base Activity to not call presenter methods
        presenter?.connect(view = this)
    }

    override fun onStart() {
        super.onStart()
        // FIXME Add Base Activity to not call presenter methods
        presenter?.visible()
    }

    override fun onStop() {
        super.onStop()
        // FIXME Add Base Activity to not call presenter methods
        presenter?.invisible()
        if (isFinishing) {
            presenter?.disconnect()
        }
    }

    override fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showMovies(movies: MovieResult) {
        // FIXME Add RecyclerView
        fakeContainer.text = movies.results.toString()
    }

    override fun showError(error: Throwable?) {
        // FIXME Add error messages handling
        fakeContainer.text = error.toString()
    }
}
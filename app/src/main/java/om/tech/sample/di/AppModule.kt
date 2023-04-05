package om.tech.sample.di

import om.tech.sample.networkService.RetroServices
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import om.tech.sample.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val logger : HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)//For logging
    //Create okHttp Client
    private val okHttp: OkHttpClient.Builder= OkHttpClient.Builder().addInterceptor(logger)
    @Provides
    @Singleton
    fun provideMyApi(): RetroServices {
    return Retrofit.Builder()
        .baseUrl(Constants.API_BASE_PATH)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
        .build()
        .create(RetroServices::class.java)
}
    fun getContext(@ApplicationContext context:Context):Context{
        return context
    }

}
package walmart.excercise.astronomy.vikas.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import walmart.excercise.astronomy.vikas.BuildConfig

interface PlanetaryService {

    @GET("planetary/apod")
    suspend fun download(
        @Query("api_key") apiKey: String? = BuildConfig.PLANETORY_API_KEY
    ): PlanetaryResponse

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"

        private fun create(): PlanetaryService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlanetaryService::class.java)
        }

        val planetaryService: PlanetaryService = create()
    }
}
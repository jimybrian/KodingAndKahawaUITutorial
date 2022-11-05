package ke.kk.kodingandkahawauitutorial.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface BoredApi{
    @GET("/api/activity")
    fun getActivity() : Call<ActivityResponse>
}


class RetrofitClient {
    val baseUrl = "https://www.boredapi.com"

    var instance: RetrofitClient? = null
    var boredApi: BoredApi? = null

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        boredApi = retrofit.create(BoredApi::class.java)
    }

    @Synchronized
    fun getRetrofitInstance(): RetrofitClient? {
        if(instance == null){
            instance = RetrofitClient()
        }
        return instance
    }

    fun boredApiRequest() : BoredApi?{
        return boredApi
    }
}
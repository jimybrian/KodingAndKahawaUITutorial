package ke.kk.kodingandkahawauitutorial.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


interface DarajaAPIRequests{
    @GET("/oauth/v1/generate")
    fun getAuthToken(
        @Header("authorization") token: String,
        @Query("grant_type") grantType: String
    ) : Call<AuthToken>

    @POST("/mpesa/stkpush/v1/processrequest")
    fun initiateSTKPush(
        @Header("Authorization") bearerToken: String,
        @Body payload: StkPayload
    ) : Call<Any>
}

object DarajaInfo {
    val consumerKey = ""
    val consumerSecret = ""

    val shortCode = "174379"
    val lipaPasSKey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"

    fun getCurrentTimeStamp(): String? {
        return try {
            val timeStamp = SimpleDateFormat("yyyyMMddhhmmss", Locale.ENGLISH)
                .format(Calendar.getInstance().time)
            timeStamp
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getSTKPassword(timeStamp: String) : String{
        val password:String = shortCode + lipaPasSKey + timeStamp
        return b64String(password)
    }

    fun getAuthKey(): String{
        val authKey = "$consumerKey:$consumerSecret"
        return "Basic ${b64String(authKey)}"
    }

    fun b64String(s:String):String{
        val bytes = s.toByteArray(charset("ISO-8859-1"))
        val auth = Base64.getEncoder().encodeToString(bytes);

        return  auth
    }
}

class DarajaRetrofitClient {

    val baseUrl = "https://sandbox.safaricom.co.ke"
    var instance: DarajaRetrofitClient? = null

    var darajaApi: DarajaAPIRequests? = null

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttp = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        darajaApi = retrofit.create(DarajaAPIRequests::class.java)
    }

    @Synchronized
    fun getRetrofitInstance(): DarajaRetrofitClient? {
        if (instance == null) {
            instance = DarajaRetrofitClient()
        }
        return instance
    }

    fun getDarajaApis(): DarajaAPIRequests? {
        return darajaApi
    }
}
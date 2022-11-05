package ke.kk.kodingandkahawauitutorial.api

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ke.kk.kodingandkahawauitutorial.R
import ke.kk.kodingandkahawauitutorial.databinding.ActivityGetBoredActivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetBoredActivity : AppCompatActivity() {

    lateinit var binding: ActivityGetBoredActivityBinding
    var retrofitClient: RetrofitClient? = null

    var darajaRetrofitClient: DarajaRetrofitClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_get_bored_activity)

        retrofitClient = RetrofitClient().getRetrofitInstance()
        darajaRetrofitClient = DarajaRetrofitClient().getRetrofitInstance()

        getBoredActivity()

        binding.btnGetActivity.setOnClickListener {
            getBoredActivity()
        }

        binding.btnInitiateSTKPush.setOnClickListener {
            getAuthToken()
        }
    }

    private fun getAuthToken(){
        darajaRetrofitClient?.darajaApi?.getAuthToken(DarajaInfo.getAuthKey(),"client_credentials")?.enqueue(
            object : Callback<AuthToken>{
                override fun onResponse(call: Call<AuthToken>, response: Response<AuthToken>) {
                    val bearerToken = "Bearer ${response.body()?.access_token}"
                    initiateSTKPush(bearerToken)
                }

                override fun onFailure(call: Call<AuthToken>, t: Throwable) {
                    Toast.makeText(this@GetBoredActivity, "Sorry, something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun initiateSTKPush(bearerToken: String){
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val amount = binding.etAmount.text.toString()
        val timeStamp = DarajaInfo.getCurrentTimeStamp().orEmpty()

        val stkPayload = StkPayload(
            AccountReference = "STK_PUSH_DEMO",
            Amount = amount,
            BusinessShortCode = DarajaInfo.shortCode,
            CallBackURL = "https://example.com/anydomain",
            PartyA = phoneNumber,
            PartyB = DarajaInfo.shortCode,
            Password = DarajaInfo.getSTKPassword(timeStamp),
            PhoneNumber = phoneNumber,
            Timestamp = timeStamp,
            TransactionDesc = "STK PUSH DEMO",
            TransactionType = "CustomerPayBillOnline"
        )
        darajaRetrofitClient?.darajaApi?.initiateSTKPush(bearerToken, stkPayload)?.enqueue(
            object : Callback<Any>{
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    Toast.makeText(this@GetBoredActivity, "Success, STK Initiated Successfully", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Toast.makeText(this@GetBoredActivity, "Sorry, something went wrong", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    private fun getBoredActivity(){
        retrofitClient?.boredApi?.getActivity()?.enqueue(object: Callback<ActivityResponse>{
            override fun onResponse(
                call: Call<ActivityResponse>,
                response: Response<ActivityResponse>
            ) {
                val activity = response.body()
                if(activity != null) {
                    binding.txObtainedActivity.text = "Activity: ${activity?.activity}"
                    binding.txType.text = "Type: ${activity?.type}"
                    binding.txLink.text = "Link: ${activity?.link}"
                    binding.txPrice.text = "Price: ${activity?.price}"
                    binding.txNumberParticipants.text = "Participants: ${activity?.participants}"
                }
            }

            override fun onFailure(call: Call<ActivityResponse>, t: Throwable) {
                Toast.makeText(this@GetBoredActivity, "Sorry, something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
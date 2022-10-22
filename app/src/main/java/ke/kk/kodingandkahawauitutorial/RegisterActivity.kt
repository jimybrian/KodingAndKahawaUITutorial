package ke.kk.kodingandkahawauitutorial

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ke.kk.kodingandkahawauitutorial.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var activityRegisterBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityRegisterBinding = DataBindingUtil.setContentView(this@RegisterActivity, R.layout.activity_register)

        activityRegisterBinding.btnRegister.setOnClickListener {
            val email = activityRegisterBinding.etEmail.text.toString()
            val fullNames = activityRegisterBinding.etFullNames.text.toString()
            val password = activityRegisterBinding.etPassword.text.toString()

            if(email.isNotEmpty() && fullNames.isNotEmpty() && password.isNotEmpty()){
                Toast.makeText(this@RegisterActivity, "$email, $fullNames, $password", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this@RegisterActivity, "Please enter the required fields", Toast.LENGTH_SHORT).show()
            }


        }
    }

}
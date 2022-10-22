package ke.kk.kodingandkahawauitutorial

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ke.kk.kodingandkahawauitutorial.databinding.ActivityRadioCheckBoxesBinding

class RadioButtonCheckBoxActivity : AppCompatActivity() {

    lateinit var activityRadioCheckBoxesBinding: ActivityRadioCheckBoxesBinding

    var isMondaySelected: Boolean = false
    var isTuedaySelected: Boolean = false
    var isWednesdaySelected: Boolean = false

    var gender: String = "Male"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityRadioCheckBoxesBinding = DataBindingUtil.setContentView(this@RadioButtonCheckBoxActivity, R.layout.activity_radio_check_boxes)


        activityRadioCheckBoxesBinding.rdGender.setOnCheckedChangeListener { radioGroup, id ->
            gender = when(id){
                activityRadioCheckBoxesBinding.rbMale.id -> "Male"
                activityRadioCheckBoxesBinding.rbFemale.id -> "Female"
                activityRadioCheckBoxesBinding.rbPreferNotToSay.id -> "Prefer Not To Say"
                else -> "Male"
            }
        }

        activityRadioCheckBoxesBinding.chkMonday.setOnCheckedChangeListener { compoundButton, isChecked ->
            isMondaySelected = isChecked
        }

        activityRadioCheckBoxesBinding.chkTuesday.setOnCheckedChangeListener { compoundButton, isChecked ->
            isTuedaySelected = isChecked
        }

        activityRadioCheckBoxesBinding.chkWednesday.setOnCheckedChangeListener { compoundButton, isChecked ->
            isWednesdaySelected = isChecked
        }


        activityRadioCheckBoxesBinding.btnClickMe.setOnClickListener {
            Toast.makeText(this@RadioButtonCheckBoxActivity, "Gender $gender, Monday $isMondaySelected, Tuesday, $isTuedaySelected, Wednesday $isWednesdaySelected", Toast.LENGTH_LONG).show()
        }

    }

}
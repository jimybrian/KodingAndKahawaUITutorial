package ke.kk.kodingandkahawauitutorial.mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import ke.kk.kodingandkahawauitutorial.R
import ke.kk.kodingandkahawauitutorial.databinding.ActivityCarBinding
import ke.kk.kodingandkahawauitutorial.mvvm.viewmodel.CarViewModel

class CarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCarBinding

    private val carViewModel: CarViewModel = CarViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@CarActivity, R.layout.activity_car)

        binding.lifecycleOwner = this@CarActivity
        binding.viewModel = carViewModel

        setupObservers()

        binding.btnCreateCar.setOnClickListener {
            carViewModel.createUpdateCar()
        }

        binding.btnUpdateSpeed.setOnClickListener {
            carViewModel.updateSpeed()
        }

        binding.btnUpdateFuelLevel.setOnClickListener {
            carViewModel.updateFuelLevel()
        }
    }

    private fun setupObservers(){
        carViewModel.carName.observe(this@CarActivity, Observer {
            carViewModel.createUpdateCar()
        })

        carViewModel.carModel.observe(this@CarActivity, Observer {
            carViewModel.createUpdateCar()
        })

        carViewModel.carRegistrationNumber.observe(this@CarActivity, Observer {
            carViewModel.createUpdateCar()
        })
    }

}
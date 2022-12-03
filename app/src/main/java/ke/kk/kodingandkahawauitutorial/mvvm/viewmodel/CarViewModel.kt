package ke.kk.kodingandkahawauitutorial.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ke.kk.kodingandkahawauitutorial.mvvm.model.CarModel
import java.util.*

class CarViewModel : ViewModel() {
    val carName: MutableLiveData<String> = MutableLiveData()
    val carModel: MutableLiveData<String> = MutableLiveData()
    val carRegistrationNumber: MutableLiveData<String> = MutableLiveData()

    val car: MutableLiveData<CarModel> = MutableLiveData()

    fun createUpdateCar(){
        var createdCar = car.value
        if(createdCar == null){
            createdCar = CarModel(
                carName.value.orEmpty(),
                carModel.value.orEmpty(),
                carRegistrationNumber.value.orEmpty(),
                "0",
                "100%"
            )

            car.value = (createdCar)
        }else{
            createdCar.name = carName.value.orEmpty()
            createdCar.make = carModel.value.orEmpty()
            createdCar.registration = carRegistrationNumber.value.orEmpty()

            car.value = (createdCar)
        }
    }

    fun updateSpeed(){
        var createdCar = car.value
        if(createdCar != null){
            val r = Random()
            val low = 5
            val high = 110
            val result = r.nextInt(high - low) + low

            createdCar.speed = "${result.toString()} Km/h"
            car.value = (createdCar)
        }
    }

    fun updateFuelLevel(){
        var createdCar = car.value
        if(createdCar != null){
            val r = Random()
            val low = 20
            val high = 90
            val result = r.nextInt(high - low) + low

            createdCar.fuelLevel = "${result.toString()} %"
            car.value = (createdCar)
        }
    }
}
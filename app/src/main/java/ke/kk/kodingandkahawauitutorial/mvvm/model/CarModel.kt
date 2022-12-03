package ke.kk.kodingandkahawauitutorial.mvvm.model

data class CarModel(
    var name: String,
    var make: String,
    var registration: String,
    var speed: String,
    var fuelLevel: String
){
    fun updateSpeed(newSpeed: String){
        this.speed = newSpeed
    }

    fun updateFuelLevel(newLevel: String){
        this.fuelLevel = newLevel
    }
}

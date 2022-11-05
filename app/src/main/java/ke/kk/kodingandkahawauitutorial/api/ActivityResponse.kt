package ke.kk.kodingandkahawauitutorial.api

import com.google.gson.annotations.SerializedName

//{
//    "activity": "Have a paper airplane contest with some friends",
//    "type": "social",
//    "participants": 4,
//    "price": 0.02,
//    "link": "",
//    "key": "8557562",
//    "accessibility": 0.05
//}
data class ActivityResponse (
    @SerializedName("activity") val activity: String,
    @SerializedName("type") val type: String,
    @SerializedName("participants") val participants: Long,
    @SerializedName("price") val price: Double,
    @SerializedName("link") val link: String,
    @SerializedName("key") val key: String,
    @SerializedName("accessibility") val accessibility: Double
)


data class AuthToken(
    @SerializedName("access_token") val access_token: String,
    @SerializedName("expires_in") val expires_in: String
)

data class StkPayload(
    @SerializedName("AccountReference") val AccountReference: String,
    @SerializedName("Amount") val Amount: String,
    @SerializedName("BusinessShortCode") val BusinessShortCode: String,
    @SerializedName("CallBackURL") val CallBackURL: String,
    @SerializedName("PartyA") val PartyA: String,
    @SerializedName("PartyB") val PartyB: String,
    @SerializedName("Password") val Password: String,
    @SerializedName("PhoneNumber") val PhoneNumber: String,
    @SerializedName("Timestamp") val Timestamp: String,
    @SerializedName("TransactionDesc") val TransactionDesc: String,
    @SerializedName("TransactionType") val TransactionType: String
)
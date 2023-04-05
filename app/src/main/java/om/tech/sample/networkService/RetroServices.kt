package om.tech.sample.networkService

import om.tech.sample.beans.DataBean
import retrofit2.Response
import retrofit2.http.*


interface RetroServices {

    @GET("users")

    suspend fun getUsers(): Response<DataBean>

}
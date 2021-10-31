package com.vtpsales.vtpsales.data.repository

import com.vtpsales.vtpsales.data.entities.User
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @FormUrlEncoded
    @POST("user_login.php")
    suspend fun executeLogin(@Field("email") email: String, @Field("password") password: String): Response<User>


    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<User>
}

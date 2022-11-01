package com.cursos.moviles.adimer.laravelcrud

import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("user")
    suspend fun getUsers() : Response<List<User>>

    @Headers("content-type: application/json")
    @POST("user")
    suspend fun createUser( @Body user: User) : Response<User>

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User) : Response<User>
}
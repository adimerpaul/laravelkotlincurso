package com.cursos.moviles.adimer.laravelcrud

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
    @GET("user")
    suspend fun getUsers() : Response<List<User>>

    @Headers("content-type: application/json")
    @POST("user")
    suspend fun createUser(user: User) : Response<User>
}
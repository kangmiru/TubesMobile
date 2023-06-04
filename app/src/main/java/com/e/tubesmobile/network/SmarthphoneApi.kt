package com.e.tubesmobile.network

import com.e.tubesmobile.model.Smarthphone
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface SmarthphoneApi {
    @GET("api/smartphone")
    suspend fun all(): ApiResponse<SmarthphoneGetResponse>
    @GET("api/smartphone/{id}")
    suspend fun find(@Path("id") id: String):
            ApiResponse<SmarthphoneSingleGetResponse>
    @POST("api/smartphone")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Smarthphone):
            ApiResponse<SmarthphoneSingleGetResponse>
    @PUT("api/smartphone/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Smarthphone
    ):
            ApiResponse<SmarthphoneSingleGetResponse>
    @DELETE("api/smartphone/{id}")
    suspend fun delete(@Path("id") id: String):
            ApiResponse<SmarthphoneSingleGetResponse>
}
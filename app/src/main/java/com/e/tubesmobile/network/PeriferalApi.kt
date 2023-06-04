package com.e.tubesmobile.network

import com.e.tubesmobile.model.Periferal
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface PeriferalApi {
    @GET("api/periferal")
    suspend fun all(): ApiResponse<PeriferalGetResponse>
    @GET("api/periferal/{id}")
    suspend fun find(@Path("id") id: String):
            ApiResponse<PeriferalSingleGetResponse>
    @POST("api/periferal")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Periferal):
            ApiResponse<PeriferalSingleGetResponse>
    @PUT("api/periferal/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Periferal
    ):
            ApiResponse<PeriferalSingleGetResponse>
    @DELETE("api/periferal/{id}")
    suspend fun delete(@Path("id") id: String):
            ApiResponse<PeriferalSingleGetResponse>
}
package com.yxl.schedule.data

import com.yxl.schedule.utils.Constants
import com.yxl.schedule.data.model.Groups
import com.yxl.schedule.data.model.ScheduleData
import com.yxl.schedule.data.model.ProfessorData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ScheduleApi {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("group")
    suspend fun getStudentSchedule(
        @Query("group") group: String,
        @Query("subgroup") subgroup: String,
        @Query("weekdays[]") weekdays: String,
        @Query("weeks[]") weeks: String
    ): Response<ScheduleData>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("teacher")
    suspend fun getProfessorSchedule(
        @Query("teacher") teacher: String,
        @Query("weekdays[]") weekdays: String
    ): Response<ProfessorData>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("group/list")
    suspend fun getGroups(): Response<Groups>

    companion object{

         operator fun invoke(): ScheduleApi {
           return Retrofit.Builder()
                .baseUrl(Constants.scheduleBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ScheduleApi::class.java)
        }
    }

}
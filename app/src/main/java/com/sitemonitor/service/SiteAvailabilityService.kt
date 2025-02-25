package com.sitemonitor.service

import retrofit2.Call
import retrofit2.http.GET

interface SiteAvailabilityService {
    @GET("/")
    fun checkAvailability(): Call<Void>
}

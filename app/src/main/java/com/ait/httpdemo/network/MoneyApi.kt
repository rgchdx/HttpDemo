package com.ait.httpdemo.network

import com.ait.httpdemo.data.money.MoneyResult
import retrofit2.http.GET

// https://api.exchangerate-api.com/v4/latest/USD

//Host: https://api.exchangerate-api.com/
//Path: v4/latest/USD
//Query params: we do not have now...

interface MoneyApi {

    @GET("v4/latest/USD")
    suspend fun getRates(): MoneyResult


}
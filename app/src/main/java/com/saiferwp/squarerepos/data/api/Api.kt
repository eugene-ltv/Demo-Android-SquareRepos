package com.saiferwp.squarerepos.data.api

import com.saiferwp.squarerepos.data.api.response.SquareReposResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("orgs/square/repos")
    fun getSquareReposAsync(
        @Query("page") currentPage: Int,
        @Query("sort") sort: String = "pushed"
    ): Deferred<Response<SquareReposResponse>>

}
package com.saiferwp.squarerepos.data.api.request

import com.saiferwp.squarerepos.data.api.Api
import com.saiferwp.squarerepos.data.api.response.SquareReposResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class SquareReposRequest(private val currentPage: Int) : Request<SquareReposResponse>() {
    override fun executeAsync(api: Api): Deferred<Response<SquareReposResponse>> {
        return api.getSquareReposAsync(currentPage)
    }
}

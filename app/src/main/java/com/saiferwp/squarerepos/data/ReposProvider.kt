package com.saiferwp.squarerepos.data

import com.saiferwp.squarerepos.data.api.ApiClient
import com.saiferwp.squarerepos.data.api.request.SquareReposRequest
import com.saiferwp.squarerepos.data.api.response.SquareReposResponse
import java.net.UnknownHostException

class ReposProvider(
    private val apiClient: ApiClient
) {

    suspend fun getSquareRepos(currentPage: Int): SquareReposResponse? {
        try {
            val request = SquareReposRequest(currentPage)
            val response = apiClient.executeAsync(request).await()

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()

                return body
            } else {
                // Error, empty result
            }
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                // No internet
            }
        }
        return null
    }
}
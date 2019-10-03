package com.saiferwp.squarerepos.ui.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiferwp.squarerepos.App
import com.saiferwp.squarerepos.Config
import com.saiferwp.squarerepos.data.model.RepoData
import kotlinx.coroutines.launch

class ReposViewModel : ViewModel() {

    private val reposListLiveData = MutableLiveData<List<RepoData>>()


    private var currentPage = 1
    internal var isLastPage = false
    internal var isLoading = false

    private fun loadRepos() {
        isLoading = true
        viewModelScope.launch {
            val provider = App.component.getReposProvider()
            val reposList = provider.getSquareRepos(currentPage)
            if (reposList != null) {
                if (reposList.size < PAGE_SIZE) {
                    isLastPage = true
                }

                isLoading = false
                reposListLiveData.value = reposList
            }
        }
    }

    fun reposList(): MutableLiveData<List<RepoData>> {
        loadRepos()
        return reposListLiveData
    }

    fun loadMoreItems() {
        currentPage++
        loadRepos()
    }

    companion object {
        const val PAGE_SIZE: Int = Config.API_RESPONSE_PAGE_SIZE
    }
}

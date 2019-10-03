package com.saiferwp.squarerepos.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saiferwp.squarerepos.R
import com.saiferwp.squarerepos.misc.PaginationListener


class ReposFragment : Fragment() {

    companion object {
        fun newInstance() = ReposFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private val adapter: ReposListAdapter =
        ReposListAdapter()

    private lateinit var viewModel: ReposViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerView_repos_list)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object :
            PaginationListener(
                recyclerView.layoutManager as LinearLayoutManager,
                ReposViewModel.PAGE_SIZE
            ) {
            override fun loadMoreItems() {
                viewModel.loadMoreItems()
            }

            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReposViewModel::class.java)

        viewModel.reposList()
            .observe(this, Observer { list ->
                adapter.addData(list)

                adapter.showLoading(!viewModel.isLastPage)
            })
    }
}

package com.example.newsapp.ui.fragments.breaking_news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.NewsApplication.Companion.applicationComponent
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.ui.adapters.NewsAdapter
import com.example.newsapp.ui.fragments.viewmodel.NewsViewModel
import com.example.newsapp.ui.fragments.viewmodel.NewsViewModelProviderFactory
import com.example.newsapp.utils.FragmentConstants.Companion.KEY_ARTICLE
import com.example.newsapp.utils.Resource
import javax.inject.Inject

class BreakingNewsFragment : Fragment() {

    @Inject lateinit var newsViewModelProviderFactory: NewsViewModelProviderFactory

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by activityViewModels { newsViewModelProviderFactory }

    override fun onAttach(context: Context) {
        applicationComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupBreakingNewsObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { article -> onItemClick(article) }
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }

    private fun onItemClick(article: Article) {
        findNavController().navigate(
            R.id.action_breakingNewsFragment_to_articleFragment,
            bundleOf(KEY_ARTICLE to article)
        )
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupBreakingNewsObserver() {
        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->

                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }
}

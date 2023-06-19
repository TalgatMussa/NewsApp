package com.example.newsapp.ui.fragments.article

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapp.NewsApplication.Companion.applicationComponent
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.ui.fragments.viewmodel.NewsViewModel
import com.example.newsapp.ui.fragments.viewmodel.NewsViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ArticleFragment : Fragment() {
    @Inject
    lateinit var newsViewModelProviderFactory: NewsViewModelProviderFactory

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private val args: ArticleFragmentArgs by navArgs()
    private val viewModel: NewsViewModel by activityViewModels { newsViewModelProviderFactory }

    override fun onAttach(context: Context) {
        applicationComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        setupWebView(article)
        setupSaveNewsButtonClickListener(article, view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupWebView(article: Article) {
        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }
    }

    private fun setupSaveNewsButtonClickListener(article: Article, view: View) {
        binding.saveNewsButton.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, getString(R.string.article_saved), Snackbar.LENGTH_SHORT).show()
        }
    }
}

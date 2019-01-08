package com.dmity.androidacademy.features.newsDetail

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseFragment
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import com.dmity.androidacademy.utils.visible
import kotlinx.android.synthetic.main.fragment_news_details.*
import kotlinx.android.synthetic.main.view_error_stub.*

class NewsDetailsFragment : BaseFragment() {

    override val layout = R.layout.fragment_news_details

    private val viewModel: NewsDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(NewsDetailsViewModel::class.java)
    }

    override fun initUi() {
        initObservers()
        getIntentData()
    }

    override fun initUx() {
        btnRetry.setOnClickListener { requireActivity().onBackPressed() }
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    fun showError(errorMessage: String, show: Boolean) {
        errorStub.visible(show)
    }

    private fun initObservers() {
        viewModel.showProgress.observe(this, Observer {
            showProgress(it)
        })
        viewModel.showError.observe(this, Observer {
            showError(getString(R.string.error_view_text), it)
        })

        viewModel.newsItem.observe(this, Observer { item ->
            setupScreen(item)
        })
    }

    private fun getIntentData() {
        viewModel.loadNewsItem(arguments?.getInt(ARGS_ITEM_ID))
    }

    private fun setupScreen(newsEntity: NewsEntity) {
        with(newsEntity) {
            Glide.with(this@NewsDetailsFragment).load(imageUrl).into(detailImage)
            detailTitle.text = title
            detailDate.text = publishDate
            detailText.text = fullText
            setupToolbar(newsCategory)
        }
    }

    private fun setupToolbar(title: String) {
        toolbar.title = title.takeIf { it.isNotBlank() } ?: ""
        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white))
    }


    companion object {
        private const val ARGS_ITEM_ID = "item_id"

        fun newInstance(itemId: Int): NewsDetailsFragment {
            val fragment = NewsDetailsFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARGS_ITEM_ID, itemId)
            }
            return fragment
        }
    }
}

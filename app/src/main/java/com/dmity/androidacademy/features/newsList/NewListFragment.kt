package com.dmity.androidacademy.features.newsList

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmity.androidacademy.R
import com.dmity.androidacademy.core.BaseFragment
import com.dmity.androidacademy.core.extensions.addOnClickListener
import com.dmity.androidacademy.core.extensions.setRetryListener
import com.dmity.androidacademy.core.extensions.showError
import com.dmity.androidacademy.core.extensions.showProgress
import com.dmity.androidacademy.core.extensions.showSnack
import com.dmity.androidacademy.core.extensions.visible
import com.dmity.androidacademy.features.about.AboutActivity
import com.dmity.androidacademy.features.newsList.adapter.NewsAdapter
import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isPhone
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isPortrait
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewListFragment : BaseFragment() {

    override val layout = R.layout.fragment_news_list
    private lateinit var adapter: NewsAdapter
    private val viewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }
    private var clickListener: OnNewsClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initUi() {
        initRecycler()
        setupSpinner()
        initObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context) {
            is OnNewsClickListener -> clickListener = context
        }
    }

    override fun initUx() {
        val clickListener = View.OnClickListener {
            viewModel.getNews(retry = true)
        }

        setRetryListener(clickListener)
        fab?.setOnClickListener(clickListener)
        aboutBtn?.setOnClickListener {
            AboutActivity.display(requireContext())
        }
        setupSpinnerListener()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list, menu)
    }

    override fun onDetach() {
        clickListener = null
        super.onDetach()
    }

    private fun showSnackBar(text: String, show: Boolean) {
        if (show && text.isNotBlank()) {
            rvNews.showSnack(text)
            resetSnackBar()
        }
    }

    private fun resetSnackBar() {
        viewModel.showSnackBar.postValue(false)
    }

    private fun setupSpinnerListener() {
        spinner?.addOnClickListener { position ->
            viewModel.getNews(position, false)
        }
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
                context,
                R.array.news_categories,
                R.layout.items_categories_spinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = adapter
    }

    private fun initObservers() {
        viewModel.showProgress.observe(this, Observer { showProgress(it) })
        viewModel.showError.observe(this, Observer { showError(getString(R.string.error_view_text), it) })
        viewModel.showSnackBar.observe(this, Observer { showSnackBar(getString(R.string.error_loading), it) })

        viewModel.news.observe(this, Observer { items ->
            adapter.setData(items)
            rvNews.visible(true)
        })

    }

    private fun initRecycler() {
        rvNews.layoutManager = getLayoutManager()
        adapter = NewsAdapter { onNewsItemClick(it) }
        rvNews.adapter = adapter
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        return when (isPhone(context)) {
            true -> {
                if (isPortrait(context)) LinearLayoutManager(context)
                else GridLayoutManager(context, resources.getInteger(R.integer.landscape_news_columns_count))
            }
            false -> {
                LinearLayoutManager(context)
            }
        }
    }

    private fun onNewsItemClick(item: DisplayableItem) {
        (item as NewsEntity).id?.let {
            clickListener?.onNewsItemClick(it)
        }
    }

    companion object {
        fun newInstance() = NewListFragment()
    }

    interface OnNewsClickListener {
        fun onNewsItemClick(itemId: Int)
    }

}

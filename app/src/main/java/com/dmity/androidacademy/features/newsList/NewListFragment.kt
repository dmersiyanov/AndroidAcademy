package com.dmity.androidacademy.features.newsList

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseFragment
import com.dmity.androidacademy.features.newsList.adapter.NewsAdapter
import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isPhone
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isPortrait
import com.dmity.androidacademy.utils.showSnack
import com.dmity.androidacademy.utils.visible
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.view_error_stub.*
import kotlinx.android.synthetic.main.view_progress_stub.*

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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        when(context) {
            is OnNewsClickListener -> clickListener = context
        }
    }

    override fun initUx() {
        val clickListener = View.OnClickListener {
            viewModel.getNews(retry = true)
        }

        btnRetry.setOnClickListener(clickListener)
        fab.setOnClickListener(clickListener)
        setupSpinnerListener()
    }

    override fun showProgress(show: Boolean) = progress?.visible(show)

    override fun onDetach() {
        clickListener = null
        super.onDetach()
    }

    private fun showError(errorMessage: String, show: Boolean) {
        if(errorMessage.isNotBlank()) {
            tvErrorMessage.text = errorMessage
        }
        errorStub.visible(show)
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
        spinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.getNews(position, false)
            }
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

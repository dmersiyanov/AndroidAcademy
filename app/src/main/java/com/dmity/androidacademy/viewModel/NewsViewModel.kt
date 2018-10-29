package com.dmity.androidacademy.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.utils.DataUtils

class NewsViewModel: ViewModel() {

    private var news: MutableLiveData<List<DisplayableItem>> = MutableLiveData()

    fun getNews(): LiveData<List<DisplayableItem>> {
        if (news.value == null) {
            news.value = DataUtils.generateNews()
        }
        return news
    }

}
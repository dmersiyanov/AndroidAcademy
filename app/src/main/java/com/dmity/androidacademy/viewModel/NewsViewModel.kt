package com.dmity.androidacademy.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.utils.DataUtils

class NewsViewModel: ViewModel() {

    private var news: MutableLiveData<List<DisplayableItem>>? = null

    fun getNews(): LiveData<List<DisplayableItem>> {
        if (news == null) {
            news = MutableLiveData()
            news?.value = DataUtils.generateNews()
        }

        // TODO Так и не понял как понормальному сделать этот момент ниже
        return news!!
    }

}
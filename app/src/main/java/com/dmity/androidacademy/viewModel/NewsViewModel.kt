package com.dmity.androidacademy.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmity.androidacademy.base.SubscriptionsHolder
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.utils.DataUtils
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel: ViewModel(), SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()

    var news: MutableLiveData<List<DisplayableItem>> = MutableLiveData()
    val showProgress = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()


    init {
        getNews()
    }

    fun getNews() {
        Single.fromCallable { DataUtils.generateNews()  }
                .subscribeOn(Schedulers.io())
//                .delay(DELAY, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    //                    showProgress(true)
//                    showError("", false)
                }
                .subscribe({

                    news.value = it
//                    adapter.items = it
//                    showProgress(false)
                }, {
                    Log.e(TAG, it.message)
                    it.printStackTrace()
//                    showError("", true)
//                    showProgress(false)
//                    Snackbar.make(rvNews, getString(R.string.error_loading), Snackbar.LENGTH_LONG).show()
                })
                .bind()
    }



//    fun getNews(): LiveData<List<DisplayableItem>> {
//        if (news.value == null) {
//            news.value = DataUtils.generateNews()
//        }
//        return news
//    }

    override fun onCleared() {
        resetCompositeDisposable()
    }


    companion object {
        private const val DELAY = 2L
        private const val TAG = "NewsViewModel"
    }

}
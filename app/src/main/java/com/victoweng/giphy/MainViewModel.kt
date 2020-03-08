package com.victoweng.giphy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victoweng.giphy.dataModels.search.GifResponse
import com.victoweng.giphy.repo.GiphyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(val gifApi: GiphyApi) : ViewModel() {

    private val gifsLiveData = MutableLiveData<GifResponse>()

    fun fetchGifs(search: String) {
        val hashMap = hashMapOf<String, String>(
            "q" to search,
            "limit" to "20"
        )

        CoroutineScope(IO).launch {
            val list = gifApi.fetchGifs(hashMap)
            Log.d("CLOWN", "gif result: " + list.data?.size);
            gifsLiveData.postValue(list)
        }
    }

    fun observeGifLiveData(): LiveData<GifResponse> {
        return gifsLiveData
    }
}
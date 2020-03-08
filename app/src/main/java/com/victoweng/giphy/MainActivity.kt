package com.victoweng.giphy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.victoweng.giphy.adapter.GifAdapter
import com.victoweng.giphy.repo.GiphyApi
import com.victoweng.giphy.repo.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val glideRequest: RequestManager by lazy {
        Glide.with(this)
    }

    val giphyApi: GiphyApi by lazy {
        RetrofitClient(this).giphyApi()
    }

    val gifAdapter: GifAdapter by lazy {
        GifAdapter(glideRequest)
    }

    val mainViewModel: MainViewModel by lazy {
        MainViewModel(giphyApi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gif_recyclerview.apply {
            adapter = gifAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        mainViewModel.observeGifLiveData().observe(this, Observer {
            gifAdapter.setGifList(it)
        })

        search_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mainViewModel.fetchGifs(text.toString())
            }

        })
    }
}

package com.qifan.kgank.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.qifan.kgank.R
import com.qifan.kgank.api.NetworkState
import com.qifan.kgank.ui.adapter.KGankAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class KGankActivity : AppCompatActivity() {

    //di
    private val mViewModel: KGankViewModel by viewModel()

    private lateinit var adapter: KGankAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = KGankAdapter()
        recyclerview.adapter = adapter
        mViewModel.setCategory("Android")
        mViewModel.gankContentList.observe(this, Observer {
            Logger.d("gank content list")
            adapter.submitList(it)
        })

        mViewModel.loadStatus.observe(this, Observer { state ->
            Log.d("Qifan=======", "WTF")
            when (state) {
                NetworkState.SUCCESS -> {
                    Log.d("Qifan=======", "SUCCESS")
                    spinner.visibility = View.GONE
                }
                NetworkState.LOADING -> {
                    Log.d("Qifan=======", "LOADING")
                    spinner.visibility = View.VISIBLE
                }
                NetworkState.ERROR -> {
                    Log.d("Qifan=======", "ERROR")
                    spinner.visibility = View.GONE
                }
            }
        })
//        button.setOnClickListener {
//            mViewModel.fetchGankContent()
//        }
//        mViewModel.spinner.observe(this, Observer { visible ->
//            spinner.visibility = if (visible) View.VISIBLE else View.GONE
//            textView.visibility = if (!visible) View.VISIBLE else View.GONE
//        })
//
//        mViewModel.gankContentLiveData.observe(this, Observer {
//            textView.text = it.results?.get(0).toString()
//        })
//
//        mViewModel.snackBar.observeEvent(this) { content ->
//            Snackbar.make(textView, content, Snackbar.LENGTH_INDEFINITE).show()
//        }
    }

}

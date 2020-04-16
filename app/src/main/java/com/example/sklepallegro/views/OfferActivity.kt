package com.example.sklepallegro.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sklepallegro.adapters.OfferListAdapter
import com.example.sklepallegro.R
import com.example.sklepallegro.viewModels.OfferViewModel
import kotlinx.android.synthetic.main.activity_main.*

class OfferActivity : AppCompatActivity() {
    private val offerViewModel =
        OfferViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loadingDialog = LoadingDialog(this)

        recyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val offerListAdapter =
            OfferListAdapter(applicationContext)
        recyclerView.adapter = offerListAdapter

        offerViewModel.offerList.observe(this, Observer { it ->
            offerListAdapter.update(it)
        })

        offerViewModel.errorFlag.observe(this, Observer { it ->
            if (it) Toast.makeText(
                applicationContext,
                R.string.errorFetch,
                Toast.LENGTH_SHORT
            ).show()
        })
        offerViewModel.loading.observe(this, Observer {
            if (it) loadingDialog.show()
            else loadingDialog.hide()
        })
    }
}

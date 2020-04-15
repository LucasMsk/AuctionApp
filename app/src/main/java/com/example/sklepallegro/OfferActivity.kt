package com.example.sklepallegro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sklepallegro.models.Offer
import kotlinx.android.synthetic.main.activity_main.*

class OfferActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val offerViewModel = OfferViewModel()
        recyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val offerListAdapter = OfferListAdapter(applicationContext)
        recyclerView.adapter = offerListAdapter

        offerViewModel.offerList.observe(this, Observer<List<Offer>> { it ->
            offerListAdapter.update(it)
        })

        offerViewModel.errorFlag.observe(this, Observer<Boolean> { it ->
            if (it) {
                Toast.makeText(
                    applicationContext,
                    R.string.errorFetch,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}

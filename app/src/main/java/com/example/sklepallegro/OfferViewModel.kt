package com.example.sklepallegro

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sklepallegro.models.Offer
import com.example.sklepallegro.models.OfferList
import com.fasterxml.jackson.module.kotlin.readValue
import java.lang.Exception
import java.net.URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OfferViewModel(private val offerListAdapter: OfferListAdapter) {

    var offerList: List<Offer>? = null

    init {
        updateView()
    }

    fun parseResponse() {
        try {
            val offersResponse =
                ParseService.mapper.readValue<OfferList>(URL("https://private-987cdf-allegromobileinterntest.apiary-mock.com/allegro/offers"))
            offerList =
                offersResponse.offers.filter { it.price.amount > 50 && it.price.amount < 1000 }
                    .sortedByDescending { it.price.amount }

        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun updateView() {
        CoroutineScope(context = Dispatchers.Main).launch {
            val job: Job = CoroutineScope(context = Dispatchers.IO).launch {
                parseResponse()
            }
            job.join()
            if (offerList != null) {
                offerListAdapter.update(offerList!!)
            }
        }
    }
}
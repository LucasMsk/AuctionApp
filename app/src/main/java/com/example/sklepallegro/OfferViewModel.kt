package com.example.sklepallegro

import android.content.Context
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

class OfferViewModel(private val contextt: Context, private val recyclerView: RecyclerView) {

    var offerList: List<Offer>? = null

    init {
        parseResult()
    }

    fun parseResult() {
        CoroutineScope(context = Dispatchers.Main).launch {
            val job: Job = CoroutineScope(context = Dispatchers.IO).launch {
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
            job.join()
            if (offerList != null) {
                recyclerView.adapter = OfferListAdapter(offerList!!, contextt)
            }
        }
    }
}
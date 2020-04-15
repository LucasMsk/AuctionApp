package com.example.sklepallegro

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class OfferViewModel() : ViewModel() {

    var offerList = MutableLiveData<List<Offer>>()
    var errorFlag = MutableLiveData<Boolean>(false)

    init {
        parseResponse()
    }

    private fun parseResponse() {
        CoroutineScope(context = Dispatchers.IO).launch {
            try {
                val offersResponse =
                    ParseService.mapper.readValue<OfferList>(URL(ParseService.URL))
                offerList.postValue(offersResponse.offers.filter { it.price.amount > 50 && it.price.amount < 1000 }
                    .sortedBy { it.price.amount })

            } catch (e: Exception) {
                errorFlag.postValue(true)
            }
        }
    }
}
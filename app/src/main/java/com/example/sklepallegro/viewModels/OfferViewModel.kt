package com.example.sklepallegro.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sklepallegro.api.WebService
import com.example.sklepallegro.models.Offer
import com.example.sklepallegro.models.OfferList
import com.fasterxml.jackson.module.kotlin.readValue
import java.lang.Exception
import java.net.URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OfferViewModel() : ViewModel() {

    var offerList = MutableLiveData<List<Offer>>()
    var errorFlag = MutableLiveData<Boolean>(false)
    var loading = MutableLiveData<Boolean>(false)

    init {
        parseResponse()
    }

    private fun parseResponse() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                loading.postValue(true)
                val offersResponse =
                    WebService.mapper.readValue<OfferList>(URL(
                        WebService.urlOffers
                    ))
                offerList.postValue(offersResponse.offers.filter { it.price.amount > 50 && it.price.amount < 1000 }
                    .sortedBy { it.price.amount })
                loading.postValue(false)

            } catch (e: Exception) {
                loading.postValue(false)
                errorFlag.postValue(true)
            }
        }
    }
}
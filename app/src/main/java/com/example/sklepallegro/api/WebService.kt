package com.example.sklepallegro.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object WebService {
    val mapper = jacksonObjectMapper()
    const val urlOffers =
        "https://private-987cdf-allegromobileinterntest.apiary-mock.com/allegro/offers"
}
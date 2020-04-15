package com.example.sklepallegro

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object ParseService {
    val mapper = jacksonObjectMapper()
    const val URL = "https://private-987cdf-allegromobileinterntest.apiary-mock.com/allegro/offers"
}
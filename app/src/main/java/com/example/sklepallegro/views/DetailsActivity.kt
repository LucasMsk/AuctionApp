package com.example.sklepallegro.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sklepallegro.R
import com.example.sklepallegro.models.Offer

public class DetailsActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val offer = intent.getParcelableExtra<Offer>("offer")
        val pricetxt = findViewById<TextView>(R.id.txt_price_value)
        val description = findViewById<TextView>(R.id.txt_description)
        val imageView = findViewById<ImageView>(R.id.img_details)
        if (offer != null) {
            pricetxt.text = offer.price.amount.toString() + " " + offer.price.currency
            description.text = Html.fromHtml(offer.description)
            supportActionBar?.title = offer.name
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            Glide.with(applicationContext).load(offer.thumbnailUrl).into(imageView)
        }
    }
}
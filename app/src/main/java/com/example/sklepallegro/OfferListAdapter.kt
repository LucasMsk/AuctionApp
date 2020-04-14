package com.example.sklepallegro

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sklepallegro.models.Offer
import com.squareup.picasso.Picasso

class OfferListAdapter(private var offerList: List<Offer>, private val context: Context) :
    RecyclerView.Adapter<OfferListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return offerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val offer = offerList.get(position)
        val price = offer.price.amount.toString() + " " + offer.price.currency
        holder.titleTextView.text = offer.name
        holder.priceTextView.text = price


        Picasso.get().load(offer.thumbnailUrl).into(holder.imageView)

        holder.offer = offer
    }

    class MyViewHolder(itemView: View, var offer: Offer? = null) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var titleTextView: TextView
        lateinit var priceTextView: TextView
        lateinit var imageView: ImageView

        init {
            titleTextView = itemView.findViewById(R.id.txt_title)
            priceTextView = itemView.findViewById(R.id.txt_price_value)
            imageView = itemView.findViewById(R.id.img_item)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                intent.putExtra("offer", offer)
                itemView.context.startActivity(intent)
            }
        }

    }
}

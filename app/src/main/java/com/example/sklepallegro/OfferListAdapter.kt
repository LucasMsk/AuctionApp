package com.example.sklepallegro

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sklepallegro.models.Offer

class OfferListAdapter(private val context: Context) :
    RecyclerView.Adapter<OfferListAdapter.MyViewHolder>() {

    private var offerList = listOf<Offer>()

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
        holder.offer = offer

        Glide.with(context).load(offer.thumbnailUrl).into(holder.imageView)

    }

    fun update(newOfferList: List<Offer>) {
        offerList = newOfferList
        this.notifyDataSetChanged()
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
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                itemView.context.startActivity(intent)
            }
        }

    }
}

package com.codertainment.materialintro.sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.sample.adapter.RecyclerViewAdapter.ExampleViewHolder
import com.codertainment.materialintro.sample.model.Song

class RecyclerViewAdapter(private val data: ArrayList<Song>) : RecyclerView.Adapter<ExampleViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder =
    ExampleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_card, parent, false))

  override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) = holder.bind(data[position])

  override fun getItemCount(): Int = data.size

  inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var coverImage: ImageView = itemView.findViewById(R.id.cover_photo)
    var coverName: TextView = itemView.findViewById(R.id.cover_name)
    var singerName: TextView = itemView.findViewById(R.id.singer_name)

    fun bind(item: Song) {
      coverImage.setImageResource(item.songArt)
      coverName.text = item.songName
      singerName.text = item.singerName
    }
  }
}
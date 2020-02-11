package com.codertainment.materialintro.sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.sample.adapter.RecyclerViewAdapter.ExampleViewHolder
import com.codertainment.materialintro.sample.model.Song
import java.util.*

class RecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<ExampleViewHolder>() {
  private var songList = ArrayList<Song>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_card, parent, false)
    return ExampleViewHolder(view)
  }

  override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
    val song = songList[position]
    holder.coverName.text = song.songName
    holder.singerName.text = song.singerName
    holder.coverImage.setImageResource(song.songArt)
  }

  override fun getItemCount(): Int {
    return songList.size
  }

  fun setSongList(songList: List<Song>) {
    this.songList = ArrayList(songList)
    notifyDataSetChanged()
  }

  inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var coverImage: ImageView = itemView.findViewById(R.id.cover_photo)
    var coverName: TextView = itemView.findViewById(R.id.cover_name)
    var singerName: TextView = itemView.findViewById(R.id.singer_name)
  }
}
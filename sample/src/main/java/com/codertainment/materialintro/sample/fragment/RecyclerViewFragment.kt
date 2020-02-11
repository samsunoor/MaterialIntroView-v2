package com.codertainment.materialintro.sample.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.sample.adapter.RecyclerViewAdapter
import com.codertainment.materialintro.sample.model.Song
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.view.MaterialIntroView
import java.util.*

class RecyclerViewFragment : Fragment(), MaterialIntroListener {
  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: RecyclerViewAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_recyclerview, container, false)
    recyclerView = view.findViewById(R.id.recycler_view)
    initializeRecyclerView()
    loadData()
    Handler().postDelayed({ showMaterialIntro() }, 2000)
    return view
  }

  private fun showMaterialIntro() {
    if (activity == null) return
    MaterialIntroView(requireActivity()).apply {
      isDotAnimationEnabled = true
      focusGravity = FocusGravity.CENTER
      focusType = Focus.MINIMUM
      delayMillis = 200
      isFadeInAnimationEnabled = true
      isFadeOutAnimationEnabled = true
      materialIntroListener = this@RecyclerViewFragment
      isPerformClick = true
      infoText = "This intro focuses on Recyclerview item"
      targetView = recyclerView.getChildAt(2)
      viewId = INTRO_CARD
      show(requireActivity())
    }
  }

  private fun loadData() {
    val song = Song("Diamonds", R.drawable.diamond, "Rihanna")
    val songList: MutableList<Song> = ArrayList()
    for (i in 0..9) {
      songList.add(song)
    }
    adapter.setSongList(songList)
  }

  private fun initializeRecyclerView() {
    if (context == null) return
    val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
    recyclerView.layoutManager = layoutManager
    recyclerView.setHasFixedSize(true)
    adapter = RecyclerViewAdapter(requireContext())
    recyclerView.adapter = adapter
  }

  override fun onIntroDone(onUserClick: Boolean, viewId: String) {
    if (viewId == INTRO_CARD && onUserClick) {
      Toast.makeText(activity, "User Clicked", Toast.LENGTH_SHORT).show()
    }
  }

  companion object {
    private const val INTRO_CARD = "recyclerView_material_intro"
  }
}
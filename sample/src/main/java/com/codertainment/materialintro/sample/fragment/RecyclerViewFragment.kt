package com.codertainment.materialintro.sample.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.sample.adapter.RecyclerViewAdapter
import com.codertainment.materialintro.sample.model.Song
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.utils.materialIntro
import kotlinx.android.synthetic.main.fragment_recyclerview.*

class RecyclerViewFragment : Fragment(), MaterialIntroListener {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_recyclerview, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeRecyclerView()
    Handler().postDelayed({ showMaterialIntro() }, 2000)
  }

  private fun showMaterialIntro() {
    materialIntro(true) {
      focusType = Focus.MINIMUM
      infoText = "This intro focuses on RecyclerView item"
      targetView = recycler_view.getChildAt(2)
      viewId = INTRO_CARD
    }
  }

  private fun getSongs(): ArrayList<Song> {
    val list = ArrayList<Song>()
    val song = Song("Diamonds", R.drawable.diamond, "Rihanna")
    for (i in 0..9) {
      list.add(song)
    }
    return list
  }

  private fun initializeRecyclerView() {
    if (context == null) return
    recycler_view.apply {
      layoutManager = GridLayoutManager(activity, 2)
      adapter = RecyclerViewAdapter(getSongs())
    }
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
package com.codertainment.materialintro.sample.fragment.sequence

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.codertainment.materialintro.MaterialIntroConfiguration
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.sequence.MaterialIntroSequenceListener
import com.codertainment.materialintro.sequence.materialIntroSequence
import com.codertainment.materialintro.shape.ShapeType
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_sequence_parent.*
import org.jetbrains.anko.support.v4.toast

class SequenceParentFragment : Fragment(), MaterialIntroSequenceListener {

  private val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
      if (tab == null) return
      sequence_parent_view_pager.setCurrentItem(tab.position, true)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_sequence_parent, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    Log.d("parent", "onViewCreated")
    sequence_parent_view_pager.adapter = TabsAdapter()
    sequence_parent_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) {

      }

      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

      }

      override fun onPageSelected(position: Int) {
        sequence_parent_tabs.selectTab(sequence_parent_tabs.getTabAt(position))
      }
    })
    sequence_parent_tabs.addOnTabSelectedListener(onTabSelectedListener)

    materialIntroSequence(1000, this) {
      add(
        MaterialIntroConfiguration(
          infoText = "Parent Fragment",
          shapeType = ShapeType.RECTANGLE,
          targetView = sequence_parent_fragment_button,
          viewId = "parent"
        )
      )
    }
  }

  inner class TabsAdapter : FragmentStatePagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment =
      if (position == 0) {
        Child1Fragment()
      } else {
        Child2Fragment()
      }

    override fun getCount(): Int = sequence_parent_tabs.tabCount
  }

  override fun onProgress(onUserClick: Boolean, viewId: String, current: Int, total: Int) {
    toast("click: $onUserClick\nviewId: $viewId\ncurrent: $current\ntotal: $total")
  }

  override fun onCompleted() {
    toast("Tutorial Complete")
  }
}

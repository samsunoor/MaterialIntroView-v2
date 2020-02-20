package com.codertainment.materialintro.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.MaterialIntroConfiguration
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.sequence.MaterialIntroSequenceListener
import com.codertainment.materialintro.sequence.materialIntroSequence
import com.codertainment.materialintro.shape.Focus
import kotlinx.android.synthetic.main.fragment_focus.*
import org.jetbrains.anko.support.v4.toast

class FocusFragment : Fragment(), MaterialIntroSequenceListener {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_focus, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    materialIntroSequence(1000, this) {
      add(materialIntroConfig.apply {
        focusType = Focus.ALL
        infoText = "This intro view focuses on whole of target view"
        targetView = button_focus_1
      })
      add(materialIntroConfig.apply {
        focusType = Focus.MINIMUM
        infoText = "This intro view focuses with minimum size"
        targetView = button_focus_2
      })
      add(materialIntroConfig.apply {
        focusType = Focus.NORMAL
        infoText = "This intro view focuses with normal size (average of MIN and ALL)"
        targetView = button_focus_3
      })
    }
  }

  private val materialIntroConfig
    get() = MaterialIntroConfiguration().apply {
      isPerformClick = true
    }

  override fun onProgress(onUserClick: Boolean, viewId: String, current: Int, total: Int) {
    if (onUserClick) toast(viewId)
  }

  override fun onCompleted() {
    toast("Focus tutorial done")
  }
}
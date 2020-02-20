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
import com.codertainment.materialintro.shape.FocusGravity
import kotlinx.android.synthetic.main.fragment_gravity.*
import org.jetbrains.anko.support.v4.toast

class GravityFragment : Fragment(), MaterialIntroSequenceListener {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_gravity, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    materialIntroSequence(500, this) {
      add(materialIntroConfig.apply {
        targetView = my_card
        infoText = "This intro focuses on RIGHT of target with text alignment"
        focusGravity = FocusGravity.RIGHT
        infoTextAlignment = View.TEXT_ALIGNMENT_VIEW_END
      })
      add(materialIntroConfig.apply {
        targetView = my_card2
        infoText = "This intro focuses on CENTER of target"
        focusGravity = FocusGravity.CENTER
        infoTextAlignment = View.TEXT_ALIGNMENT_CENTER
      })
      add(materialIntroConfig.apply {
        targetView = my_card3
        infoText = "This intro focuses on LEFT of target with text alignment"
        focusGravity = FocusGravity.LEFT
        infoTextAlignment = View.TEXT_ALIGNMENT_VIEW_START
      })
    }
  }

  private val materialIntroConfig
    get() = MaterialIntroConfiguration().apply {
      focusType = Focus.MINIMUM
      isPerformClick = true
    }

  override fun onProgress(onUserClick: Boolean, viewId: String, current: Int, total: Int) {
    if (onUserClick) toast(viewId)
  }

  override fun onCompleted() {
    toast("Gravity tutorial done")
  }
}
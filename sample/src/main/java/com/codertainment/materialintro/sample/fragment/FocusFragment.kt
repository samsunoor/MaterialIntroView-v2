package com.codertainment.materialintro.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.view.materialIntro
import kotlinx.android.synthetic.main.fragment_focus.*
import org.jetbrains.anko.support.v4.toast

class FocusFragment : Fragment(), MaterialIntroListener {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_focus, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    showIntro(button_focus_1, INTRO_FOCUS_1, "This intro view focuses on whole of target view", Focus.ALL)
  }

  private fun showIntro(view: View, id: String, text: String, focusType: Focus) {
    materialIntro(true) {
      this.focusType = focusType
      infoText = text
      targetView = view
      viewId = id
    }
  }

  override fun onIntroDone(onUserClick: Boolean, viewId: String) {
    when {
      viewId == INTRO_FOCUS_1 -> {
        showIntro(button_focus_2, INTRO_FOCUS_2, "This intro view focus on minimum size", Focus.MINIMUM)
      }
      viewId == INTRO_FOCUS_2 -> {
        showIntro(button_focus_3, INTRO_FOCUS_3, "This intro view focus on normal size (average of MIN and ALL)", Focus.NORMAL)
      }
      onUserClick -> {
        toast("Gravity tutorial done")
      }
    }
  }

  companion object {
    private const val INTRO_FOCUS_1 = "intro_focus_1"
    private const val INTRO_FOCUS_2 = "intro_focus_2"
    private const val INTRO_FOCUS_3 = "intro_focus_3"
  }
}
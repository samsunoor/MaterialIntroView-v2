package com.codertainment.materialintro.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.view.MaterialIntroView

/**
 * Created by mertsimsek on 31/01/16.
 */
class FocusFragment : Fragment(), MaterialIntroListener {

  private lateinit var button1: Button
  private lateinit var button2: Button
  private lateinit var button3: Button

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_focus, container, false)
    button1 = view.findViewById(R.id.button_focus_1)
    button2 = view.findViewById(R.id.button_focus_2)
    button3 = view.findViewById(R.id.button_focus_3)
    showIntro(button1, INTRO_FOCUS_1, "This intro view focus on all target.", Focus.ALL)
    return view
  }

  private fun showIntro(view: View, id: String, text: String, focusType: Focus) {
    if (activity == null) return
    MaterialIntroView(requireActivity()).apply {
      isDotAnimationEnabled = true
      focusGravity = FocusGravity.CENTER
      this.focusType = focusType
      delayMillis = 200
      isFadeInAnimationEnabled = true
      isFadeOutAnimationEnabled = true
      materialIntroListener = this@FocusFragment
      isPerformClick = true
      infoText = text
      targetView = view
      viewId = id
      show(requireActivity())
    }
  }

  override fun onIntroDone(onUserClick: Boolean, viewId: String) {
    if (viewId === INTRO_FOCUS_1) showIntro(
      button2,
      INTRO_FOCUS_2,
      "This intro view focus on minimum size",
      Focus.MINIMUM
    ) else if (viewId === INTRO_FOCUS_2) showIntro(
      button3,
      INTRO_FOCUS_3,
      "This intro view focus on normal size (avarage of MIN and ALL)",
      Focus.NORMAL
    )
  }

  companion object {
    private const val INTRO_FOCUS_1 = "intro_focus_1"
    private const val INTRO_FOCUS_2 = "intro_focus_2"
    private const val INTRO_FOCUS_3 = "intro_focus_3"
  }
}
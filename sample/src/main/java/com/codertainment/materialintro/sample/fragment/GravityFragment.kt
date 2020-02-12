package com.codertainment.materialintro.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.view.materialIntro
import kotlinx.android.synthetic.main.fragment_gravity.*
import org.jetbrains.anko.support.v4.toast

class GravityFragment : Fragment(), MaterialIntroListener {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_gravity, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    showIntro(my_card, INTRO_CARD1, "This intro focuses on RIGHT of target with text alignment", FocusGravity.RIGHT, View.TEXT_ALIGNMENT_VIEW_END)
  }

  override fun onIntroDone(onUserClick: Boolean, viewId: String) {
    when {
      viewId == INTRO_CARD1 -> {
        showIntro(my_card2, INTRO_CARD2, "This intro focuses on CENTER of target", FocusGravity.CENTER, View.TEXT_ALIGNMENT_CENTER)
      }
      viewId == INTRO_CARD2 -> {
        showIntro(my_card3, INTRO_CARD3, "This intro focuses on LEFT of target", FocusGravity.LEFT, View.TEXT_ALIGNMENT_VIEW_START)
      }
      onUserClick -> {
        toast("Focus tutorial done")
      }
    }
  }

  private fun showIntro(view: View, id: String, text: String, focusGravity: FocusGravity, textAlignment: Int) {
    materialIntro(true) {
      this.focusGravity = focusGravity
      focusType = Focus.MINIMUM
      isPerformClick = true
      infoText = text
      targetView = view
      infoTextAlignment = textAlignment
      viewId = id
    }
  }

  companion object {
    private const val INTRO_CARD1 = "intro_card_1"
    private const val INTRO_CARD2 = "intro_card_2"
    private const val INTRO_CARD3 = "intro_card_3"
  }
}
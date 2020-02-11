package com.codertainment.materialintro.sample.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.view.MaterialIntroView

/**
 * Created by mertsimsek on 31/01/16.
 */
class GravityFragment : Fragment(), MaterialIntroListener {

  private lateinit var cardView1: CardView
  private lateinit var cardView2: CardView
  private lateinit var cardView3: CardView

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_gravity, container, false)
    cardView1 = view.findViewById(R.id.my_card)
    cardView2 = view.findViewById(R.id.my_card2)
    cardView3 = view.findViewById(R.id.my_card3)
    showIntro(cardView1, INTRO_CARD1, "This intro focuses on RIGHT", FocusGravity.RIGHT, View.TEXT_ALIGNMENT_VIEW_END)
    return view
  }

  override fun onIntroDone(onUserClick: Boolean, viewId: String) {
    if (viewId === INTRO_CARD1) showIntro(
      cardView2,
      INTRO_CARD2,
      "This intro focuses on CENTER.",
      FocusGravity.CENTER,
      View.TEXT_ALIGNMENT_CENTER
    )
    if (viewId === INTRO_CARD2) showIntro(
      cardView3,
      INTRO_CARD3,
      "This intro focuses on LEFT.",
      FocusGravity.LEFT,
      View.TEXT_ALIGNMENT_VIEW_START
    )
  }

  private fun showIntro(view: View, id: String, text: String, focusGravity: FocusGravity, textAlignment: Int) {
    if (activity == null) return
    MaterialIntroView(requireActivity()).apply {
      isDotAnimationEnabled = true
      this.focusGravity = focusGravity
      focusType = Focus.MINIMUM
      delayMillis = 200
      isFadeInAnimationEnabled = true
      isFadeOutAnimationEnabled = true
      isPerformClick = true
      infoText = text
      targetView = view
      infoTextAlignment = textAlignment
      materialIntroListener = this@GravityFragment
      viewId = id
      show(requireActivity())
    }
  }

  companion object {
    private const val INTRO_CARD1 = "intro_card_1"
    private const val INTRO_CARD2 = "intro_card_2"
    private const val INTRO_CARD3 = "intro_card_3"
  }
}
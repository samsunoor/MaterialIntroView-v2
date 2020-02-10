package com.codertainment.materialintro.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.prefs.PreferencesManager
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.shape.ShapeType
import com.codertainment.materialintro.view.MaterialIntroView

/**
 * Created by mertsimsek on 31/01/16.
 */
class MainFragment : Fragment(), View.OnClickListener {

  private lateinit var cardView: CardView
  private lateinit var button: Button

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.content_main, container, false)
    cardView = view.findViewById(R.id.my_card)
    button = view.findViewById(R.id.button_reset_all)
    button.setOnClickListener(this)
    //Show intro
    showIntro(cardView, INTRO_CARD, "This is card! Hello There. You can set this text!")
    return view
  }

  override fun onClick(v: View) {
    val id = v.id
    if (id == R.id.button_reset_all) PreferencesManager(activity!!.applicationContext).resetAll()
  }

  private fun showIntro(view: View, usageId: String, text: String) {
    if (activity == null) return
    MaterialIntroView(requireActivity()).apply {
      isDotAnimationEnabled = true
      focusGravity = FocusGravity.CENTER
      focusType = Focus.MINIMUM
      delayMillis = 200
      isFadeInAnimationEnabled = true
      isFadeOutAnimationEnabled = true
      isPerformClick = true
      setInfoText(text)
      setTarget(view)
      shapeType = ShapeType.RECTANGLE
      viewId = usageId
      show(requireActivity())
    }
  }

  companion object {
    private const val INTRO_CARD = "material_intro"
  }
}
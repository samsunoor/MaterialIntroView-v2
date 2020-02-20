package com.codertainment.materialintro.sample.fragment.sequence

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.MaterialIntroConfiguration
import com.codertainment.materialintro.sample.MainActivity
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.sequence.materialIntroSequence
import com.codertainment.materialintro.shape.ShapeType
import kotlinx.android.synthetic.main.fragment_child2.*

class Child2Fragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_child2, container, false)
  }

  override fun onResume() {
    super.onResume()
    Log.d("child2", "onResume")
    //delay to let viewpager switch to this fragment completely
    Handler().postDelayed(
      {
        materialIntroSequence {
          add(
            MaterialIntroConfiguration(
              shapeType = ShapeType.RECTANGLE,
              targetView = child2_button1,
              infoText = "This is intro for Child2's Button1"
            )
          )
          add(
            MaterialIntroConfiguration(
              shapeType = ShapeType.RECTANGLE,
              viewId = "c2_b2",
              targetView = child2_button2,
              infoText = "This is intro for Child2's Button2"
            )
          )
        }
      }, 500
    )
  }
}

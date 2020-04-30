package com.codertainment.materialintro.sample.fragment.sequence

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.sample.R
import com.codertainment.materialintro.shape.ShapeType
import com.codertainment.materialintro.utils.materialIntroSequence
import kotlinx.android.synthetic.main.fragment_child1.*

class Child1Fragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_child1, container, false)
  }

  override fun onResume() {
    super.onResume()
    Log.d("child1", "onResume")
    materialIntroSequence {
      addConfig {
        shapeType = ShapeType.RECTANGLE
        viewId = "c1_b1"
        targetView = child1_button1
        infoText = "This is intro for Child1's Button1"
      }
      addConfig {
        shapeType = ShapeType.RECTANGLE
        viewId = "c1_b2"
        targetView = child1_button2
        infoText = "This is intro for Child1's Button2"
      }
    }
  }
}

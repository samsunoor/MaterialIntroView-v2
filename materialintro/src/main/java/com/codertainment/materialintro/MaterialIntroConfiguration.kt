package com.codertainment.materialintro

import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.utils.Constants

data class MaterialIntroConfiguration(
  var maskColor: Int = Constants.DEFAULT_MASK_COLOR,
  var delayMillis: Long = Constants.DEFAULT_DELAY_MILLIS,
  var isFadeInAnimationEnabled: Boolean = false,
  var isFadeOutAnimationEnabled: Boolean = false,
  var focusType: Focus = Focus.ALL,
  var focusGravity: FocusGravity = FocusGravity.CENTER,
  var padding: Int = Constants.DEFAULT_TARGET_PADDING,
  var isDismissOnTouch: Boolean = false,
  var colorTextViewInfo: Int = Constants.DEFAULT_COLOR_TEXTVIEW_INFO,
  var isDotViewEnabled: Boolean = false,
  val isImageViewEnabled: Boolean = true
)
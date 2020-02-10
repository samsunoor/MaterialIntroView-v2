package com.codertainment.materialintro.view

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.codertainment.materialintro.MaterialIntroConfiguration
import com.codertainment.materialintro.R
import com.codertainment.materialintro.animation.AnimationFactory
import com.codertainment.materialintro.animation.AnimationListener
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.prefs.PreferencesManager
import com.codertainment.materialintro.shape.*
import com.codertainment.materialintro.shape.Rect
import com.codertainment.materialintro.target.Target
import com.codertainment.materialintro.target.ViewTarget
import com.codertainment.materialintro.utils.Constants
import com.codertainment.materialintro.utils.Utils

class MaterialIntroView : RelativeLayout {

  /**
   * Mask color
   */
  var maskColor = Constants.DEFAULT_MASK_COLOR

  /**
   * MaterialIntroView will start
   * showing after delayMillis seconds
   * passed
   */
  var delayMillis = Constants.DEFAULT_DELAY_MILLIS

  /**
   * We don't draw MaterialIntroView
   * until isReady field set to true
   */
  private var isReady = false

  /**
   * Show MaterialIntroView
   * with fade in animation if
   * this is enabled.
   */
  var isFadeInAnimationEnabled = true

  /**
   * Dismiss MaterialIntroView
   * with fade out animation if
   * this is enabled.
   */
  var isFadeOutAnimationEnabled = true

  /**
   * Animation duration
   */
  var fadeAnimationDurationMillis = Constants.DEFAULT_FADE_DURATION

  /**
   * targetShape focus on target
   * and clear circle to focus
   */
  private lateinit var targetShape: Shape

  /**
   * Focus Type
   */
  var focusType = Focus.ALL

  /**
   * FocusGravity type
   */
  var focusGravity = FocusGravity.CENTER

  /**
   * Target View
   */
  lateinit var targetView: Target

  /**
   * Eraser
   */
  private lateinit var eraser: Paint

  /**
   * Handler will be used to
   * delay MaterialIntroView
   */
  private lateinit var myHandler: Handler

  /**
   * All views will be drawn to
   * this bitmap and canvas then
   * bitmap will be drawn to canvas
   */
  private var bitmap: Bitmap? = null
  private var canvas: Canvas? = null

  /**
   * Circle padding
   */
  var padding = Constants.DEFAULT_TARGET_PADDING

  /**
   * Layout myWidth/myHeight
   */
  private var myWidth = 0
  private var myHeight = 0

  /**
   * Dismiss on touch any position
   */
  var dismissOnTouch = false

  /**
   * Info dialog view
   */
  private lateinit var infoView: RelativeLayout
  /**
   * Info CardView
   */
  private lateinit var infoCardView: CardView
  /**
   * Info Dialog Text
   */
  private lateinit var infoTextView: TextView
  /**
   * Info dialog text color
   */
  private var infoTextColor = Constants.DEFAULT_COLOR_TEXTVIEW_INFO
  /**
   * Help Dialog Icon
   */
  private lateinit var helpIconView: ImageView
  /**
   * Drawable resource to set as help icon
   */
  var helpIconResource = R.drawable.ic_help_outline_black
  /**
   * Help Icon will be shown if this is true
   */
  var isHelpIconEnabled = true
  /**
   * Info dialog will be shown
   * If this value true
   */
  var isInfoEnabled = true

  /**
   * Dot view will appear center of
   * cleared target area
   */
  private lateinit var dotView: ImageView
  /**
   * Dot View will be shown if
   * this is true
   */
  var isDotViewEnabled = true
  /**
   * Dot View animated with zoom in & zoom out animation if this is true
   */
  var isDotAnimationEnabled = false

  /**
   * Save/Retrieve status of MaterialIntroView
   * If Intro is already learnt then don't show
   * it again.
   */
  private val preferencesManager by lazy {
    PreferencesManager(context)
  }
  /**
   * Check using this Id whether user learned
   * or not.
   */
  lateinit var viewId: String
  /**
   * When layout completed, we set this true
   * Otherwise onGlobalLayoutListener stuck on loop.
   */
  private var isLayoutCompleted = false
  /**
   * Notify user when MaterialIntroView is dismissed
   */
  var materialIntroListener: MaterialIntroListener? = null
  /**
   * Perform click operation to target
   * if this is true
   */
  var isPerformClick = false
  /**
   * Disallow this MaterialIntroView from showing up more than once at a time
   */
  private var isIdempotent = false
  /**
   * Shape of target
   */
  var shapeType = ShapeType.CIRCLE
  /**
   * Use custom shape
   */
  var customShape: Shape? = null
  /**
   * Tint Dot Icon
   */
  var dotIconColor = Constants.DEFAULT_DOT_ICON_COLOR
  /**
   * Tint Help Icon
   */
  var helpIconColor = Constants.DEFAULT_HELP_ICON_COLOR
  /**
   * Card View Background Color
   */
  var cardBackgroundColor = Constants.DEFAULT_CARD_BACKGROUND_COLOR

  constructor(context: Context) : super(context) {
    init()
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    init()
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    init()
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    init()
  }

  private fun init() {
    setWillNotDraw(false)
    visibility = INVISIBLE
    /**
     * initialize objects
     */
    myHandler = Handler()
    eraser = Paint().apply {
      color = -0x1
      xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
      flags = Paint.ANTI_ALIAS_FLAG
    }

    infoView = LayoutInflater.from(context).inflate(R.layout.material_intro_card, null) as RelativeLayout
    infoCardView = infoView.findViewById(R.id.info_card_view)
    infoTextView = infoView.findViewById(R.id.info_text)
    helpIconView = infoView.findViewById(R.id.info_icon)

    dotView = LayoutInflater.from(context).inflate(R.layout.dot_view, null) as ImageView

    dotView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        targetShape.reCalculateAll()
        if (targetShape.point.y != 0 && !isLayoutCompleted) {
          if (isInfoEnabled) {
            setInfoLayout()
          }
          if (isDotViewEnabled) {
            setDotViewLayout()
          }
          removeOnGlobalLayoutListener(this@MaterialIntroView, this)
        }
      }
    })
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    myWidth = measuredWidth
    myHeight = measuredHeight
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    if (!isReady) return
    if (bitmap == null) {
      bitmap?.recycle()
      bitmap = Bitmap.createBitmap(myWidth, myHeight, Bitmap.Config.ARGB_8888)
      this.canvas = Canvas(bitmap)
    }
    /**
     * Draw mask
     */
    this.canvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
    this.canvas?.drawColor(maskColor)
    /**
     * Clear focus area
     */
    targetShape.draw(this.canvas!!, eraser, padding)
    canvas.drawBitmap(bitmap, 0f, 0f, null)
  }

  /**
   * Perform click operation when user
   * touches on target circle.
   *
   * @param event
   * @return
   */
  override fun onTouchEvent(event: MotionEvent): Boolean {
    val xT = event.x
    val yT = event.y
    val isTouchOnFocus = targetShape.isTouchOnFocus(xT.toDouble(), yT.toDouble())
    when (event.action) {
      MotionEvent.ACTION_DOWN -> {
        if (isTouchOnFocus && isPerformClick) {
          targetView.view.apply {
            isPressed = true
            invalidate()
          }
        }
        return true
      }
      MotionEvent.ACTION_UP -> {
        if (isTouchOnFocus || dismissOnTouch) {
          dismiss()
        }
        if (isTouchOnFocus && isPerformClick) {
          targetView.view.apply {
            performClick()
            isPressed = true
            invalidate()
            isPressed = false
            invalidate()
          }
        }
        return true
      }
    }
    return super.onTouchEvent(event)
  }

  fun setTarget(view: View) {
    targetView = ViewTarget(view)
  }

  /**
   * Shows material view with fade in
   * animation
   *
   * @param activity
   */
  fun show(activity: Activity) {
    if (!::targetShape.isInitialized) {
      targetShape = when {
        customShape != null -> {
          customShape!!
        }
        shapeType == ShapeType.CIRCLE -> {
          Circle(targetView, focusType, focusGravity, padding)
        }
        else -> {
          Rect(targetView, focusType, focusGravity, padding)
        }
      }
    }
    infoCardView.setCardBackgroundColor(cardBackgroundColor)
    infoTextView.setTextColor(infoTextColor)
    helpIconView.setColorFilter(helpIconColor)
    dotView.setColorFilter(dotIconColor)

    if (preferencesManager.isDisplayed(viewId)) return
    (activity.window.decorView as ViewGroup).addView(this)
    isReady = true
    myHandler.postDelayed(
      {
        if (isFadeInAnimationEnabled)
          AnimationFactory.animateFadeIn(
            this@MaterialIntroView,
            fadeAnimationDurationMillis,
            object : AnimationListener.OnAnimationStartListener {
              override fun onAnimationStart() {
                visibility = VISIBLE
              }
            })
        else
          visibility = VISIBLE
      }, delayMillis
    )
    if (isIdempotent) {
      preferencesManager.setDisplayed(viewId)
    }
  }

  /**
   * Dismiss Material Intro View
   */
  fun dismiss() {
    if (!isIdempotent) {
      preferencesManager.setDisplayed(viewId)
    }
    if (isFadeOutAnimationEnabled) {
      AnimationFactory.animateFadeOut(this, fadeAnimationDurationMillis, object : AnimationListener.OnAnimationEndListener {
        override fun onAnimationEnd() {
          removeSelf()
        }
      })
    } else {
      removeSelf()
    }
  }

  private fun removeSelf() {
    visibility = GONE
    removeMaterialView()
    materialIntroListener?.onUserClicked(viewId)
  }

  private fun removeMaterialView() {
    if (parent != null)
      (parent as ViewGroup).removeView(this)
  }

  /**
   * locate info card view above/below the
   * circle. If circle's Y coordinate is bigger than
   * Y coordinate of root view, then locate cardview
   * above the circle. Otherwise locate below.
   */
  private fun setInfoLayout() {
    myHandler.post {
      isLayoutCompleted = true
      infoParent?.removeView(infoView)
      val infoDialogParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT)
      if (targetShape.point.y < myHeight / 2) {
        infoView.gravity = Gravity.TOP
        infoDialogParams.setMargins(
          0,
          targetShape.point.y + targetShape.height / 2,
          0,
          0
        )
      } else {
        infoView.gravity = Gravity.BOTTOM
        infoDialogParams.setMargins(
          0,
          0,
          0,
          myHeight - (targetShape.point.y + targetShape.height / 2) + 2 * targetShape.height / 2
        )
      }
      infoView.layoutParams = infoDialogParams
      infoView.postInvalidate()
      addView(infoView)
      if (!isHelpIconEnabled) {
        helpIconView.visibility = GONE
      }
      infoView.visibility = VISIBLE
    }
  }

  private fun setDotViewLayout() {
    myHandler.post {
      dotParent?.removeView(dotView)
      val dotViewLayoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
      dotViewLayoutParams.height = Utils.dpToPx(Constants.DEFAULT_DOT_SIZE)
      dotViewLayoutParams.width = Utils.dpToPx(Constants.DEFAULT_DOT_SIZE)
      dotViewLayoutParams.setMargins(
        targetShape.point.x - (dotViewLayoutParams.width / 2),
        targetShape.point.y - (dotViewLayoutParams.height / 2),
        0,
        0
      )
      dotView.layoutParams = dotViewLayoutParams
      dotView.postInvalidate()
      addView(dotView)
      dotView.visibility = VISIBLE
      if (isDotAnimationEnabled) {
        AnimationFactory.performAnimation(dotView)
      }
    }
  }

  fun setInfoTextColor(colorTextViewInfo: Int) {
    this.infoTextColor = colorTextViewInfo
    infoTextView.setTextColor(this.infoTextColor)
  }

  fun setInfoText(textViewInfo: CharSequence) {
    this.infoTextView.text = textViewInfo
  }

  fun setInfoTextSize(textViewInfoSize: Float) {
    this.infoTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textViewInfoSize)
  }

  fun setConfiguration(configuration: MaterialIntroConfiguration) {
    this.maskColor = configuration.maskColor
    this.delayMillis = configuration.delayMillis
    this.isFadeInAnimationEnabled = configuration.isFadeInAnimationEnabled
    this.isFadeOutAnimationEnabled = configuration.isFadeOutAnimationEnabled
    this.infoTextColor = configuration.colorTextViewInfo
    this.isDotViewEnabled = configuration.isDotViewEnabled
    this.dismissOnTouch = configuration.isDismissOnTouch
    this.infoTextColor = configuration.colorTextViewInfo
    this.focusType = configuration.focusType
    this.focusGravity = configuration.focusGravity
  }

  private val infoParent
    get() = infoView.parent as ViewGroup?

  private val dotParent
    get() = dotView.parent as ViewGroup?

  companion object {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun removeOnGlobalLayoutListener(v: View, listener: ViewTreeObserver.OnGlobalLayoutListener) {
      v.viewTreeObserver.removeOnGlobalLayoutListener(listener)
    }
  }
}
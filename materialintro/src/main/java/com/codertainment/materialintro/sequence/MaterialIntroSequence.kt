package com.codertainment.materialintro.sequence

import android.app.Activity
import android.os.Handler
import androidx.fragment.app.Fragment
import com.codertainment.materialintro.MaterialIntroConfiguration
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.prefs.PreferencesManager
import com.codertainment.materialintro.view.MaterialIntroView
import com.codertainment.materialintro.view.materialIntro

/*
 * Created by Shripal Jain
 * on 11/02/2020
 */

class MaterialIntroSequence private constructor(private val activity: Activity) {

  companion object {
    private val sequences = ArrayList<MaterialIntroSequence>()

    fun getInstance(activity: Activity): MaterialIntroSequence {
      val found = sequences.filter { it.activity == activity }
      return if (found.isEmpty()) {
        val mis = MaterialIntroSequence(activity)
        sequences.add(mis)
        mis
      } else {
        found[0]
      }
    }
  }

  private var mivs = ArrayList<MaterialIntroView>()

  private var counter = 0
  private var isMivShowing = false
  private val preferencesManager by lazy {
    PreferencesManager(activity)
  }
  private val handler by lazy {
    Handler()
  }

  /**
   * Whether to show the skip button
   */
  var showSkip = false

  /**
   * If enabled, once the user clicks on skip button, all new MIVs will be skipped too
   * If disabled, even after the user clicks on skip button and new MIVs are added after that, for e.g. for another fragment, the new MIVs will be shown
   */
  var persistSkip = false
  private var isSkipped = false

  private var materialIntroListener = object : MaterialIntroListener {
    override fun onIntroDone(onUserClick: Boolean, viewId: String) {
      materialIntroSequenceListener?.onProgress(onUserClick, viewId, counter, mivs.size)
      isMivShowing = false
      if (counter == mivs.size) {
        materialIntroSequenceListener?.onCompleted()
      } else {
        nextIntro()
      }
    }
  }

  /**
   * Delay (in ms) for first MIV to be shown
   */
  var initialDelay: Long = 500
  var materialIntroSequenceListener: MaterialIntroSequenceListener? = null

  fun add(config: MaterialIntroConfiguration) {
    val found = mivs.find { it.viewId == config.viewId || it.viewId == config.targetView?.tag?.toString() }
    if (found != null && preferencesManager.isDisplayed(config.viewId ?: config.targetView?.tag?.toString())) return
    mivs.add(activity.materialIntro(config = config) {
      showSkip = this@MaterialIntroSequence.showSkip
      delayMillis = if (mivs.isEmpty()) initialDelay else 0
      materialIntroListener = this@MaterialIntroSequence.materialIntroListener
      skipButton.setOnClickListener {
        skip()
      }
    })
  }

  fun addConfig(func: MaterialIntroConfiguration.() -> Unit) {
    add(MaterialIntroConfiguration().apply {
      func()
    })
  }

  private fun skip() {
    isSkipped = true
    mivs[counter - 1].dismiss()
    for (i in 0 until mivs.size) {
      if (mivs[i].showOnlyOnce) {
        preferencesManager.setDisplayed(mivs[i].viewId)
      }
    }
    counter = mivs.size
    materialIntroSequenceListener?.onCompleted()
  }

  fun start() {
    if (isSkipped && persistSkip) {
      skip()
    } else {
      if (!isMivShowing) {
        nextIntro()
      }
    }
  }

  private fun nextIntro() {
    if (isSkipped && persistSkip) {
      skip()
    } else if (counter < mivs.size) {
      isMivShowing = true
      handler.post {
        mivs[counter++].show(activity)
      }
    }
  }
}

interface MaterialIntroSequenceListener {
  fun onProgress(onUserClick: Boolean, viewId: String, current: Int, total: Int)
  fun onCompleted()
}

val Activity.materialIntroSequence
  get() = MaterialIntroSequence.getInstance(this)

fun Activity.materialIntroSequence(
  initialDelay: Long? = null, materialIntroSequenceListener: MaterialIntroSequenceListener? = null, showSkip: Boolean? = null, persistSkip: Boolean? = null,
  func: MaterialIntroSequence.() -> Unit
): MaterialIntroSequence {
  return materialIntroSequence.apply {
    showSkip?.let {
      this.showSkip = it
    }
    persistSkip?.let {
      this.persistSkip = it
    }
    initialDelay?.let {
      this.initialDelay = it
    }
    materialIntroSequenceListener?.let {
      this.materialIntroSequenceListener = it
    }
    func()
    start()
  }
}

val Fragment.materialIntroSequence
  get() = if (activity != null) MaterialIntroSequence.getInstance(activity!!) else null

fun Fragment.materialIntroSequence(
  initialDelay: Long? = null, materialIntroSequenceListener: MaterialIntroSequenceListener? = null, showSkip: Boolean? = null, persistSkip: Boolean? = null,
  func: MaterialIntroSequence.() -> Unit
): MaterialIntroSequence? {
  return materialIntroSequence.apply {
    showSkip?.let {
      this?.showSkip = it
    }
    persistSkip?.let {
      this?.persistSkip = it
    }
    initialDelay?.let {
      this?.initialDelay = it
    }
    materialIntroSequenceListener?.let {
      this?.materialIntroSequenceListener = it
    }
    this?.func()
    this?.start()
  }
}

enum class SkipLocation {
  TOP_RIGHT,
  TOP_LEFT,
  BOTTOM_LEFT,
  BOTTOM_RIGHT
}

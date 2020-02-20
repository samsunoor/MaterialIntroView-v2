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
    val found = mivs.filter { it.viewId == config.viewId }
    if (found.isNotEmpty() || preferencesManager.isDisplayed(config.viewId)) return
    mivs.add(activity.materialIntro(config = config) {
      delayMillis = if (mivs.isEmpty()) initialDelay else 0
      materialIntroListener = this@MaterialIntroSequence.materialIntroListener
    })
  }

  fun start() {
    if (!isMivShowing) {
      nextIntro()
    }
  }

  private fun nextIntro() {
    if (counter < mivs.size) {
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
  initialDelay: Long? = null, materialIntroSequenceListener: MaterialIntroSequenceListener? = null,
  func: MaterialIntroSequence.() -> Unit
): MaterialIntroSequence {
  return materialIntroSequence.apply {
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
  initialDelay: Long? = null, materialIntroSequenceListener: MaterialIntroSequenceListener? = null,
  func: MaterialIntroSequence.() -> Unit
): MaterialIntroSequence? {
  return materialIntroSequence.apply {
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
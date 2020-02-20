package com.codertainment.materialintro.sample

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.codertainment.materialintro.MaterialIntroConfiguration
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.sample.fragment.*
import com.codertainment.materialintro.sequence.MaterialIntroSequenceListener
import com.codertainment.materialintro.sequence.materialIntroSequence
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.view.materialIntro
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_toolbar.*
import org.jetbrains.anko.toast

/**
 * This activity demonstrates how to implement Material introView on ToolBar MenuItems with custom colors and sequence
 *
 */
class ToolbarMenuItemActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MaterialIntroSequenceListener {

  private lateinit var shareAction: View
  private lateinit var helpAction: View

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_toolbar)
    setSupportActionBar(toolbar)

    val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    drawer_layout.setDrawerListener(toggle)
    toggle.syncState()
    nav_view.setNavigationItemSelectedListener(this)
  }

  override fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main, menu)
    Handler().post {
      helpAction = findViewById(R.id.help)
      shareAction = findViewById(R.id.share)
      materialIntroSequence(500, this) {
        add(
          MaterialIntroConfiguration(
            viewId = MENU_SEARCH_ID_TAG,
            infoText = getString(R.string.guide_setup_profile),
            infoCardBackgroundColor = Color.GREEN,
            helpIconColor = Color.BLUE,
            infoTextColor = Color.BLACK,
            dotIconColor = Color.RED,
            targetView = findViewById(R.id.search)
          )
        )
        add(
          MaterialIntroConfiguration(
            viewId = MENU_SHARED_ID_TAG,
            infoText = getString(R.string.guide_setup_profile),
            infoCardBackgroundColor = Color.GREEN,
            helpIconColor = Color.BLUE,
            infoTextColor = Color.BLACK,
            dotIconColor = Color.RED,
            targetView = shareAction
          )
        )
        add(
          MaterialIntroConfiguration(
            viewId = MENU_ABOUT_ID_TAG,
            infoText = getString(R.string.guide_setup_profile),
            infoCardBackgroundColor = Color.GREEN,
            helpIconColor = Color.BLUE,
            infoTextColor = Color.BLACK,
            dotIconColor = Color.RED,
            targetView = helpAction
          )
        )
      }
    }
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    return if (id == R.id.action_settings) {
      true
    } else super.onOptionsItemSelected(item)
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    val fragment = when (item.itemId) {
      R.id.nav_demo -> MainFragment()
      R.id.nav_gravity -> GravityFragment()
      R.id.nav_focus -> FocusFragment()
      R.id.nav_recyclerview -> RecyclerViewFragment()
      R.id.nav_custom_view -> CustomInfoViewFragment()
      else -> null
    }
    if (item.itemId == R.id.nav_toolbar) {
      startActivity(Intent(applicationContext, ToolbarMenuItemActivity::class.java))
    } else if (fragment != null) {
      supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }

  companion object {
    private const val MENU_SHARED_ID_TAG = "menuSharedIdTag"
    private const val MENU_ABOUT_ID_TAG = "menuAboutIdTag"
    private const val MENU_SEARCH_ID_TAG = "menuSearchIdTag"
  }

  override fun onProgress(onUserClick: Boolean, viewId: String, current: Int, total: Int) {
    when(viewId) {
      MENU_SHARED_ID_TAG -> toast("Share done")
      MENU_ABOUT_ID_TAG -> toast("About done")
      MENU_SEARCH_ID_TAG -> toast("Search done")
    }
  }

  override fun onCompleted() {
    toast("All done")
  }
}
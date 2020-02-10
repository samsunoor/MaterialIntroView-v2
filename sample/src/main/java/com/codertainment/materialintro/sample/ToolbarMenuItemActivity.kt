package com.codertainment.materialintro.sample

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.codertainment.materialintro.animation.MaterialIntroListener
import com.codertainment.materialintro.sample.fragment.FocusFragment
import com.codertainment.materialintro.sample.fragment.GravityFragment
import com.codertainment.materialintro.sample.fragment.MainFragment
import com.codertainment.materialintro.sample.fragment.RecyclerViewFragment
import com.codertainment.materialintro.shape.Focus
import com.codertainment.materialintro.shape.FocusGravity
import com.codertainment.materialintro.view.MaterialIntroView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_toolbar.*

/**
 * This activity demonstrates how to implement Material introView on ToolBar MenuItems
 *
 * @author Thomas Kioko
 */
class ToolbarMenuItemActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MaterialIntroListener {

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
      showIntro(findViewById(R.id.search), MENU_SEARCH_ID_TAG, getString(R.string.guide_setup_profile), FocusGravity.CENTER)
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

  /**
   * Method that handles display of intro screens
   *
   * @param view         View to show guide
   * @param id           Unique ID
   * @param text         Display message
   * @param focusGravity Focus Gravity of the display
   */
  private fun showIntro(view: View, id: String, text: String, focusGravity: FocusGravity) {
    MaterialIntroView(this).apply {
      isDotAnimationEnabled = true
      this.focusGravity = focusGravity
      focusType = Focus.MINIMUM
      delayMillis = 100
      fadeAnimationDurationMillis = 200
      isFadeInAnimationEnabled = true
      isFadeOutAnimationEnabled = true
      cardBackgroundColor = Color.RED
      dotIconColor = Color.GREEN
      helpIconColor = Color.BLUE
      isPerformClick = true
      setInfoText(text)
      setTarget(view)
      materialIntroListener = this@ToolbarMenuItemActivity
      viewId = id
      show(this@ToolbarMenuItemActivity)
    }
  }

  override fun onUserClicked(materialIntroViewId: String?) {
    when (materialIntroViewId) {
      MENU_SEARCH_ID_TAG -> showIntro(
        helpAction,
        MENU_ABOUT_ID_TAG,
        getString(R.string.guide_setup_profile),
        FocusGravity.CENTER
      )
      MENU_ABOUT_ID_TAG -> showIntro(
        shareAction,
        MENU_SHARED_ID_TAG,
        getString(R.string.guide_setup_profile),
        FocusGravity.CENTER
      )
      MENU_SHARED_ID_TAG -> Toast.makeText(this@ToolbarMenuItemActivity, "Complete!", Toast.LENGTH_SHORT).show()
    }
  }

  companion object {
    private const val MENU_SHARED_ID_TAG = "menuSharedIdTag"
    private const val MENU_ABOUT_ID_TAG = "menuAboutIdTag"
    private const val MENU_SEARCH_ID_TAG = "menuSearchIdTag"
  }
}
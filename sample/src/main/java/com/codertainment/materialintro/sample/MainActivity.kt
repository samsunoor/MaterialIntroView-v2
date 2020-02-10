package com.codertainment.materialintro.sample

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.codertainment.materialintro.sample.fragment.FocusFragment
import com.codertainment.materialintro.sample.fragment.GravityFragment
import com.codertainment.materialintro.sample.fragment.MainFragment
import com.codertainment.materialintro.sample.fragment.RecyclerViewFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
    setSupportActionBar(toolbar)
    if (savedInstanceState == null) supportFragmentManager
      .beginTransaction()
      .add(R.id.container, MainFragment())
      .commit()
    val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
    val toggle = ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
    )
    drawer.setDrawerListener(toggle)
    toggle.syncState()
    val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
    navigationView.setNavigationItemSelectedListener(this)
  }

  override fun onBackPressed() {
    val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.main, menu)
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
}
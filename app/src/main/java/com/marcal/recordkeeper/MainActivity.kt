package com.marcal.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.marcal.recordkeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
            R.id.reset_running -> onRestRunningMenuOptionClick(this)
            R.id.reset_cycling -> onRestCyclingMenuOptionClick(this)
            R.id.reset_all -> onRestAllMenuOptionClick(this)
            else -> super.onOptionsItemSelected(item)
    }


    private fun onRestRunningMenuOptionClick(activity: MainActivity): Boolean {
        Toast.makeText(activity, "Clear all Runs", Toast.LENGTH_SHORT).show()
        return true
    }
    private fun onRestCyclingMenuOptionClick(activity: MainActivity): Boolean {
        Toast.makeText(activity, "Clear all cycling", Toast.LENGTH_SHORT).show()
        return true
    }

    private fun onRestAllMenuOptionClick(activity: MainActivity): Boolean {
        Toast.makeText(activity, "Clear all", Toast.LENGTH_SHORT).show()
        return true
    }


    private fun onCyclingClick(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    private fun onRunningClick(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_running -> onRunningClick()
        R.id.nav_cycling -> onCyclingClick()
        else -> false
    }
}



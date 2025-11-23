package com.marcal.recordkeeper

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.marcal.recordkeeper.cycling.CyclingFragment
import com.marcal.recordkeeper.databinding.ActivityMainBinding
import com.marcal.recordkeeper.running.RunningFragment

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val optionClickHandled = when (item.itemId) {
            R.id.reset_running -> onRestRunningMenuOptionClick(this)
            R.id.reset_cycling -> onRestCyclingMenuOptionClick(this)
            R.id.reset_all -> onRestAllMenuOptionClick(this)
            else -> super.onOptionsItemSelected(item)
        }

        when (binding.bottomNav.selectedItemId) {
            R.id.nav_cycling -> onCyclingClick()
            R.id.nav_running -> onRunningClick()
            else -> {}
        }

        return optionClickHandled
    }


    private fun onRestRunningMenuOptionClick(activity: MainActivity): Boolean {
        getSharedPreferences(RunningFragment.sharedPropertyName, Context.MODE_PRIVATE).edit { clear() }
        return true
    }

    private fun onRestCyclingMenuOptionClick(activity: MainActivity): Boolean {
        getSharedPreferences(CyclingFragment.sharedPropertyFileName, Context.MODE_PRIVATE).edit { clear() }
        return true
    }

    private fun onRestAllMenuOptionClick(activity: MainActivity): Boolean {
        getSharedPreferences(RunningFragment.sharedPropertyName, Context.MODE_PRIVATE).edit { clear() }
        getSharedPreferences(CyclingFragment.sharedPropertyFileName, Context.MODE_PRIVATE).edit { clear() }
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



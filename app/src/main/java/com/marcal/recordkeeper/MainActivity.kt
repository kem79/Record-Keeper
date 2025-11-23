package com.marcal.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar
import com.marcal.recordkeeper.cycling.CyclingFragment
import com.marcal.recordkeeper.databinding.ActivityMainBinding
import com.marcal.recordkeeper.running.RunningFragment

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    companion object {
        const val allCategoriesOfRecords = "all"
    }

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
            R.id.reset_running -> {
                showConfirmationDialog(RunningFragment.sharedPropertyFileName)
                true
            }

            R.id.reset_cycling -> {
                showConfirmationDialog(CyclingFragment.sharedPropertyFileName)
                true
            }

            R.id.reset_all -> {
                showConfirmationDialog(MainActivity.allCategoriesOfRecords)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
        return optionClickHandled
    }

    private fun showConfirmationDialog(selection: String) {
        AlertDialog.Builder(this)
            .setTitle("Reset $selection Records")
            .setMessage("Are you sure you want to clear the records?")
            .setPositiveButton("Yes") { _, _ ->
                when (selection) {
                    MainActivity.allCategoriesOfRecords -> {
                        getSharedPreferences(
                            RunningFragment.sharedPropertyFileName,
                            MODE_PRIVATE
                        ).edit { clear() }
                        getSharedPreferences(
                            CyclingFragment.sharedPropertyFileName,
                            MODE_PRIVATE
                        ).edit { clear() }
                    }

                    else -> getSharedPreferences(selection, MODE_PRIVATE).edit { clear() }
                }
                refreshCurrentFragment()
                showConfirmation()
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun showConfirmation() {
        val snackBar = Snackbar.make(
            binding.frameContent,
            "Records cleared successfully.",
            Snackbar.LENGTH_LONG
        )
        snackBar.anchorView = binding.bottomNav
        snackBar.setAction("Undo") { }
        snackBar.show()
    }

    private fun refreshCurrentFragment() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_cycling -> onCyclingClick()
            R.id.nav_running -> onRunningClick()
            else -> {}
        }
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



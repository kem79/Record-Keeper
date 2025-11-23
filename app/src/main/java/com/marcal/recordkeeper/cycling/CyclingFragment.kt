package com.marcal.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcal.recordkeeper.databinding.FragmentCyclingBinding
import com.marcal.recordkeeper.editrecord.EditRecordActivity

class CyclingFragment: Fragment() {

    lateinit var binding: FragmentCyclingBinding
    companion object {
        const val sharedPropertyFileName = "cycling"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCyclingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val prefs = requireContext().getSharedPreferences(sharedPropertyFileName, Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text = prefs.getString("Longest Ride record", null)
        binding.textViewLongestRideDate.text = prefs.getString("Longest Ride date", null)
        binding.textViewBestAverageSpeedValue.text = prefs.getString("Best Average Speed record", null)
        binding.textViewBestAverageSpeedDate.text = prefs.getString("Best Average Speed date", null)
        binding.textViewBiggestClimbValue.text = prefs.getString("Biggest Climb record", null)
        binding.textViewBiggestClimbDate.text = prefs.getString("Biggest Climb date", null)
    }

    private fun setOnClickListeners() {
        binding.containerLongestRide.setOnClickListener { launchEditCyclingActivity("Longest Ride", "Distance") }
        binding.containerBestAverageSpeed.setOnClickListener { launchEditCyclingActivity("Best Average Speed", "Average Speed") }
        binding.containerBiggestClimb.setOnClickListener { launchEditCyclingActivity("Biggest Climb", "Height") }
    }

    private fun launchEditCyclingActivity(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("screen_data",
            EditRecordActivity.ScreenData(record, "cycling", recordFieldHint))
        startActivity(intent)
    }

}
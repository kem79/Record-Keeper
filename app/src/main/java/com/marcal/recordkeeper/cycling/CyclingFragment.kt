package com.marcal.recordkeeper.cycling

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
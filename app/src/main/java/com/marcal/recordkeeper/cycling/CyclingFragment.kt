package com.marcal.recordkeeper.cycling

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcal.recordkeeper.cycling.EditCyclingRecordActivity
import com.marcal.recordkeeper.databinding.FragmentCyclingBinding

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
        binding.containerLongestRide.setOnClickListener { launchEditCyclingActivity("Longest Ride") }
        binding.containerBestAverageSpeed.setOnClickListener { launchEditCyclingActivity("Best Average Speed") }
        binding.containerBiggestClimb.setOnClickListener { launchEditCyclingActivity("Biggest Climb") }
    }

    private fun launchEditCyclingActivity(record: String) {
        val intent = Intent(context, EditCyclingRecordActivity::class.java)
        intent.putExtra("Record", record)
        startActivity(intent)
    }

}
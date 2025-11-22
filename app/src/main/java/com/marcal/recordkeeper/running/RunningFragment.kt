package com.marcal.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcal.recordkeeper.running.EditRunningRecordActivity
import com.marcal.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {
    private lateinit var binding: FragmentRunningBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        displayRecord()
    }

    private fun displayRecord() {
        val runningPref = requireContext().getSharedPreferences("running_preferences", Context.MODE_PRIVATE)

        binding.textView5kmValue.text = runningPref.getString("5km record", null)
        binding.textView5kmDate.text = runningPref.getString("5km date", null)
        binding.textView10kmValue.text = runningPref.getString("10km record", null)
        binding.textView10kmDate.text = runningPref.getString("10km date", null)
        binding.textViewHalfMarathonValue.text = runningPref.getString("Half Marathon record", null)
        binding.textViewHalfMarathonDate.text = runningPref.getString("Half Marathon date", null)
        binding.textViewMarathonValue.text = runningPref.getString("Marathon record", null)
        binding.textViewMarathonDate.text = runningPref.getString("Marathon date", null)
    }

    private fun setupClickListeners() {
        binding.container5km.setOnClickListener { launchEditRunningRecordScreen("5km") }
        binding.container10km.setOnClickListener { launchEditRunningRecordScreen("10km") }
        binding.containerHalfMarathon.setOnClickListener { launchEditRunningRecordScreen("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchEditRunningRecordScreen("Marathon") }
    }

    private fun launchEditRunningRecordScreen(distance: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }

}
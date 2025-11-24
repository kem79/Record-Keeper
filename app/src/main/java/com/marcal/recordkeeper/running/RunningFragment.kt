package com.marcal.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcal.recordkeeper.databinding.FragmentRunningBinding
import com.marcal.recordkeeper.editrecord.EditRecordActivity
import com.marcal.recordkeeper.editrecord.EditRecordActivity.Companion.SHARED_PREFERENCES_DATE_KEY
import com.marcal.recordkeeper.editrecord.EditRecordActivity.Companion.SHARED_PREFERENCES_RECORD_KEY

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
        val runningPref = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

        binding.textView5kmValue.text = runningPref.getString("5km $SHARED_PREFERENCES_RECORD_KEY", null)
        binding.textView5kmDate.text = runningPref.getString("5km $SHARED_PREFERENCES_DATE_KEY", null)
        binding.textView10kmValue.text = runningPref.getString("10km $SHARED_PREFERENCES_RECORD_KEY", null)
        binding.textView10kmDate.text = runningPref.getString("10km $SHARED_PREFERENCES_DATE_KEY", null)
        binding.textViewHalfMarathonValue.text = runningPref.getString("Half Marathon $SHARED_PREFERENCES_RECORD_KEY", null)
        binding.textViewHalfMarathonDate.text = runningPref.getString("Half Marathon $SHARED_PREFERENCES_DATE_KEY", null)
        binding.textViewMarathonValue.text = runningPref.getString("Marathon $SHARED_PREFERENCES_RECORD_KEY", null)
        binding.textViewMarathonDate.text = runningPref.getString("Marathon $SHARED_PREFERENCES_DATE_KEY", null)
    }

    private fun setupClickListeners() {
        binding.container5km.setOnClickListener { launchEditRunningRecordScreen("5km") }
        binding.container10km.setOnClickListener { launchEditRunningRecordScreen("10km") }
        binding.containerHalfMarathon.setOnClickListener { launchEditRunningRecordScreen("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchEditRunningRecordScreen("Marathon") }
    }

    private fun launchEditRunningRecordScreen(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(EditRecordActivity.INTENT_EXTRA_SCREEN_DATA,

            EditRecordActivity.ScreenData(distance, FILENAME, "Time"))
        startActivity(intent)
    }

    companion object {
        const val FILENAME = "running"

    }

}
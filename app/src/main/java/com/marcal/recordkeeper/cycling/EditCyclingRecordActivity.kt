package com.marcal.recordkeeper.cycling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marcal.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditCyclingRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val header = intent.getStringExtra("Record")
        title = "$header Record"
    }
}
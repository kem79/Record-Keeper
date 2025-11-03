package com.marcal.recordkeeper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
package com.example.newapp.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newapp.R
import com.example.newapp.databinding.ActivityLearnWordBinding
import com.example.newapp.databinding.ActivityTasksBinding

class TasksActivity : AppCompatActivity() {
    private var _binding: ActivityTasksBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
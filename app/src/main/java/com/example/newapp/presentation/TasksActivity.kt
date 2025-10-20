package com.example.newapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newapp.R
import com.example.newapp.databinding.ActivityTasksBinding
import com.example.newapp.presentation.fragments.BlankFragment

class TasksActivity : AppCompatActivity() {
    private var _binding: ActivityTasksBinding? = null

    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for TasksActivityBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.place_holder, BlankFragment.newInstance()).commit()
        }
    }
}
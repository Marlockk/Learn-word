package com.example.newapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newapp.databinding.HomePageBinding

class HomePageActivity : AppCompatActivity() {
    private var _binding: HomePageBinding? = null

    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for HomePageBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            translater.setOnClickListener {
                val intent = Intent(this@HomePageActivity, GameActivity::class.java)
                startActivity(intent)
            }
            tasks.setOnClickListener {
                val intent = Intent(this@HomePageActivity, TasksActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

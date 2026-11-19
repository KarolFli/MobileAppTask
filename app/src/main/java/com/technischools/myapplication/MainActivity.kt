package com.technischools.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var washingProgram = 0
    private var vacuumIsOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        washingProgram = savedInstanceState?.getInt("washingProgram") ?: 0
        vacuumIsOn = savedInstanceState?.getBoolean("vacuumIsOn") ?: false

        findViewById<TextView>(R.id.authorText).text =
            getString(R.string.author, getString(R.string.candidate_number))

        val programInput = findViewById<EditText>(R.id.washingProgramInput)
        findViewById<Button>(R.id.confirmButton).setOnClickListener {
            val program = programInput.text.toString().toIntOrNull()
            if (program != null && program in 1..12) {
                washingProgram = program
                programInput.error = null
                updateWashingProgram()
            } else {
                programInput.error = getString(R.string.washing_error)
            }
        }

        findViewById<Button>(R.id.vacuumButton).setOnClickListener {
            vacuumIsOn = !vacuumIsOn
            updateVacuumState()
        }
        updateWashingProgram()
        updateVacuumState()
    }

    private fun updateWashingProgram() {
        findViewById<TextView>(R.id.washingProgramText).text =
            if (washingProgram in 1..12) getString(R.string.washing_program, washingProgram)
            else getString(R.string.washing_not_set)
    }

    private fun updateVacuumState() {
        findViewById<Button>(R.id.vacuumButton).setText(
            if (vacuumIsOn) R.string.turn_off else R.string.turn_on
        )
        findViewById<TextView>(R.id.vacuumStateText).setText(
            if (vacuumIsOn) R.string.vacuum_on else R.string.vacuum_off
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("washingProgram", washingProgram)
        outState.putBoolean("vacuumIsOn", vacuumIsOn)
        super.onSaveInstanceState(outState)
    }
}

package com.hardcodedjoy.example.clockfullscreen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * HomeActivity:
 * - Declared as both LAUNCHER and HOME in the manifest.
 * - Immediately forwards to MainActivity (your fullscreen clock) when available.
 * - Shows a small fallback UI with a button to open Home settings if forwarding fails.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Attempt to forward to your existing fullscreen clock activity (MainActivity).
        val forward = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        // Use try/catch to handle unlikely ActivityNotFoundException for safety.
        try {
            startActivity(forward)
            finish()
            return
        } catch (e: ActivityNotFoundException) {
            // Fall through to show fallback UI below.
        }

        // Fallback UI when MainActivity cannot be launched for some reason.
        setContentView(R.layout.activity_home)

        val setDefaultButton = findViewById<Button>(R.id.btn_set_default)
        setDefaultButton.setOnClickListener {
            // Opens the Home settings so the user can select/set default launcher
            startActivity(Intent(Settings.ACTION_HOME_SETTINGS))
        }
    }
}
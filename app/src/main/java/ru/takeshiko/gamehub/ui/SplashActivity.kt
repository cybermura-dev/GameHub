package ru.takeshiko.gamehub.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.takeshiko.gamehub.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    /**
     * Companion object that contains constant values used in the SplashActivity.
     * In this case, the splash screen delay duration.
     */
    private companion object {
        /**
         * Delay duration for the splash screen in milliseconds.
         */
        const val SPLASH_DELAY_MS = 2000L
    }

    /**
     * Called when the activity is created.
     * This method initializes the splash screen layout and triggers navigation to the next activity
     * after a specified delay.
     *
     * @param savedInstanceState Bundle object that contains the activity's previous state, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToNextActivity() // Initiates the transition to the next activity
    }

    /**
     * Initiates a delay before transitioning to the HomeActivity.
     * After the delay, it starts MainActivity and finishes the SplashActivity.
     * This method uses Kotlin coroutines to handle the delay asynchronously.
     */
    private fun navigateToNextActivity() {
        lifecycleScope.launch {
            delay(SPLASH_DELAY_MS) // Wait for the splash screen duration
            if (!isFinishing) { // Check if the activity is not finishing before starting the next activity
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish() // Finish the current activity to prevent returning to it
            }
        }
    }
}
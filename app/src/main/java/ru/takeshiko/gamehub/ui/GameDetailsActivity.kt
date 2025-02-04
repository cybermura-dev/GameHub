package ru.takeshiko.gamehub.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.takeshiko.gamehub.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Activity to display detailed information about a selected game.
 * Displays the game's title, description, release date, rating, platforms, and cover image.
 *
 * @see AppCompatActivity
 */
class GameDetailsActivity : AppCompatActivity() {

    /**
     * Initializes the Activity when it is created.
     * Binds the UI elements, retrieves the data from the Intent, and sets up the views
     * to display detailed game information.
     *
     * @param savedInstanceState The saved state of the Activity, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        // Extracting data from the Intent
        val gameName = intent.getStringExtra("game_name")
        val gameDescription = intent.getStringExtra("game_description")
        val gameCoverUrl = intent.getStringExtra("game_cover_url")
        val gameRating = intent.getDoubleExtra("game_rating", 0.0).toFloat()
        val gamePlatforms = intent.getStringArrayListExtra("game_platforms")
        val gameReleaseDate = intent.getStringExtra("game_release_date")

        // Binding UI elements
        val gameTitleTextView: TextView = findViewById(R.id.tv_game_title)
        val gameDescriptionTextView: TextView = findViewById(R.id.tv_game_description)
        val gameCoverImageView: ImageView = findViewById(R.id.iv_game_cover)
        val ratingBar: RatingBar = findViewById(R.id.rating_bar)
        val ratingValueTextView: TextView = findViewById(R.id.tv_rating_value)
        val releaseDateTextView: TextView = findViewById(R.id.tv_release_date)
        val platformsTextView: TextView = findViewById(R.id.tv_platforms)

        // Setting values in UI
        gameTitleTextView.text = gameName
        gameDescriptionTextView.text = gameDescription

        // Formatting and displaying the release date
        val date = Date((gameReleaseDate?.toLong() ?: 0L) * 1000)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(date)
        releaseDateTextView.text = formattedDate

        // Calculating and setting the rating
        val rating = gameRating / 20.0
        ratingBar.rating = rating.toFloat()
        ratingValueTextView.text = getString(R.string.rating_format, rating)

        // Loading the game cover image using Glide
        Glide.with(this)
            .load(gameCoverUrl)
            .into(gameCoverImageView)

        // Setting the platforms
        platformsTextView.text = gamePlatforms?.joinToString(", ")
    }
}
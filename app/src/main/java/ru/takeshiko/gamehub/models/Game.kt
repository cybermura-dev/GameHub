package ru.takeshiko.gamehub.models

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a game with details such as name, rating, platforms, and release date.
 *
 * @param id The unique identifier of the game.
 * @param cover The cover image of the game.
 * @param firstReleaseDate The release date of the game.
 * @param name The name of the game.
 * @param platforms The list of platforms that the game is available on.
 * @param rating The rating of the game.
 * @param summary A brief summary of the game.
 */
data class Game(
    val id: Int,
    val cover: Cover?,
    @SerializedName("first_release_date")
    val firstReleaseDate: Long,
    val name: String,
    val platforms: List<Platform>,
    val rating: Double,
    val summary: String
)



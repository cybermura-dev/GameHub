package ru.takeshiko.gamehub.models

/**
 * Data class representing a platform that a game can be played on.
 *
 * @param id The unique identifier of the platform.
 * @param name The name of the platform (e.g., PlayStation, Xbox).
 */
data class Platform(
    val id: Int,
    val name: String
)

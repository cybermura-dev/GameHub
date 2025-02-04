package ru.takeshiko.gamehub.models

/**
 * Data class representing the cover of a game, which contains the cover image URL.
 */
data class Cover(
    val id: Int,
    val url: String
) {
    /**
     * Returns the full URL of the cover image.
     * If the URL does not start with "http", it prepends "https:" to the URL.
     *
     * @return The full URL of the cover image.
     */
    fun getFullUrl() = if (url.startsWith("http")) url else "https:$url"
}



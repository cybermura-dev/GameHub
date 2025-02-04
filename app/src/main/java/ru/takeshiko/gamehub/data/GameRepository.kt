package ru.takeshiko.gamehub.data

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import ru.takeshiko.gamehub.models.Game
import ru.takeshiko.gamehub.network.RetrofitClient

/**
 * Repository class for fetching game data from a remote API (IGDB).
 * This class provides methods to build the request body and fetch game data from the API.
 */
class GameRepository {

    /**
     * Builds the request body for fetching games based on a query.
     *
     * @param query The query string to use in the request.
     * @return The request body containing the query.
     */
    private fun buildRequestBody(query: String): RequestBody {
        val mediaType = "text/plain".toMediaType()
        return query.toRequestBody(mediaType)
    }

    /**
     * Fetches a list of games based on the provided query text.
     *
     * @param queryText The query string used to search for games.
     * @return A [Call] object that can be used to execute the request and get the result.
     */
    private fun getGames(queryText: String): Call<List<Game>> {
        val body = buildRequestBody(queryText)
        return RetrofitClient.apiService.fetchGames(body)
    }

    /**
     * Fetches a list of top-rated games, sorted by rating in descending order.
     * The default limit is 10 games, but this can be changed by passing a different limit and offset.
     *
     * @param limit The maximum number of games to fetch. Default is 10.
     * @param offset The offset for pagination. Default is 0.
     * @return A [Call] object that can be used to execute the request and get the result.
     */
    fun getTopGames(limit: Int = 10, offset: Int = 0): Call<List<Game>> {
        val query = "fields name,cover.url,rating,summary,first_release_date,platforms.name; sort rating desc; where cover != null; limit $limit; offset $offset;"
        return getGames(query)
    }
}
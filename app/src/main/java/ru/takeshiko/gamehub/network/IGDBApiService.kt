package ru.takeshiko.gamehub.network

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.takeshiko.gamehub.models.Game

/**
 * Retrofit API service interface for interacting with the IGDB API.
 * Contains a POST method for fetching games from the IGDB database based on a query.
 */
interface IGDBApiService {
    /**
     * Fetches a list of games from the IGDB API using a query body.
     *
     * @param body The request body containing the query for fetching games.
     * @return A [Call] object that can be used to execute the request and get the result.
     */
    @Headers(
        "Client-ID: YOUR_CLIENT_ID",
        "Authorization: Bearer YOUR_TOKEN",
        "Content-Type: text/plain"
    )
    @POST("games")
    fun fetchGames(@Body body: RequestBody): Call<List<Game>>
}

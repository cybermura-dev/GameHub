package ru.takeshiko.gamehub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.takeshiko.gamehub.data.GameRepository
import ru.takeshiko.gamehub.models.Game
import kotlin.math.min

/**
 * ViewModel to manage the game list data, handle pagination, and provide the logic for filtering and loading games.
 * It interacts with the repository to fetch game data and updates the UI accordingly.
 */
class GameListViewModel : ViewModel() {
    private val repository = GameRepository()
    private val allGames = mutableListOf<Game>()
    private val filteredGames = mutableListOf<Game>()

    val games = MutableLiveData<List<Game>>()
    val errorMessage = MutableLiveData<String>()
    private val currentPage = MutableLiveData(1)
    private val totalPages = MutableLiveData(10)
    val isPreviousEnabled = MutableLiveData(false)
    val isNextEnabled = MutableLiveData(true)
    val pageText = MutableLiveData("1/10")
    val isLoading = MutableLiveData(false)

    /**
     * Loads the top games from the repository and updates the game list.
     * It also handles pagination and visibility of loading state.
     */
    fun loadTopGames() {
        isLoading.value = true
        repository.getTopGames(100).enqueue(object : Callback<List<Game>> {
            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                isLoading.value = false
                if (response.isSuccessful) {
                    allGames.clear()
                    allGames.addAll(response.body() ?: emptyList())
                    filteredGames.clear()
                    filteredGames.addAll(allGames)
                    updatePagination()
                } else {
                    errorMessage.value = "Error loading games: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                isLoading.value = false
                errorMessage.value = t.message ?: "Network error"
            }
        })
    }

    /**
     * Filters the game list based on the search query and updates the pagination.
     *
     * @param query The search string to filter the game list.
     */
    fun searchGames(query: String) {
        filteredGames.clear()
        if (query.isEmpty()) {
            filteredGames.addAll(allGames)
        } else {
            filteredGames.addAll(allGames.filter {
                it.name.contains(query, ignoreCase = true)
            })
        }

        totalPages.value = (filteredGames.size + 9) / 10
        currentPage.value = 1
        updatePagination()
    }

    /**
     * Moves to the next page and updates the pagination data.
     */
    fun nextPage() {
        currentPage.value = (currentPage.value ?: 1) + 1
        updatePagination()
    }

    /**
     * Moves to the previous page and updates the pagination data.
     */
    fun previousPage() {
        currentPage.value = (currentPage.value ?: 1) - 1
        updatePagination()
    }

    /**
     * Updates the pagination state (next/previous buttons, page number).
     * Also updates the list of games to show based on the current page.
     */
    private fun updatePagination() {
        val page = currentPage.value ?: 1
        val total = totalPages.value ?: 10

        isPreviousEnabled.value = page > 1
        isNextEnabled.value = page < total

        pageText.value = "$page/$total"

        val start = (page - 1) * 10
        val end = min(start + 10, filteredGames.size)

        if (start < filteredGames.size) {
            games.value = filteredGames.subList(start, end)
        } else {
            games.value = emptyList()
        }
    }
}
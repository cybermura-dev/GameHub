package ru.takeshiko.gamehub.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.takeshiko.gamehub.adapters.GameAdapter
import ru.takeshiko.gamehub.databinding.ActivityHomeBinding
import ru.takeshiko.gamehub.viewmodel.GameListViewModel

/**
 * Main Activity for the Home screen, displaying a list of games in a RecyclerView.
 * It also supports pagination and search functionality.
 *
 * @see AppCompatActivity
 */
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: GameListViewModel by viewModels()
    private lateinit var adapter: GameAdapter

    /**
     * Initializes the HomeActivity when it is created.
     * Sets up the RecyclerView, binds observers, and adds listeners for pagination and search.
     *
     * @param savedInstanceState The saved state of the Activity, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupListeners()

        viewModel.loadTopGames()
    }

    /**
     * Sets up the RecyclerView with a linear layout and binds it to the adapter.
     */
    private fun setupRecyclerView() {
        adapter = GameAdapter()
        binding.recyclerViewGames.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = this@HomeActivity.adapter
        }
    }

    /**
     * Sets up the observers to listen for changes in the game list, loading state, pagination, and errors.
     */
    private fun setupObservers() {
        viewModel.games.observe(this) { games ->
            adapter.updateList(games)
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.isPreviousEnabled.observe(this) { enabled ->
            binding.btnPrevious.isEnabled = enabled
        }

        viewModel.isNextEnabled.observe(this) { enabled ->
            binding.btnNext.isEnabled = enabled
        }

        viewModel.pageText.observe(this) { text ->
            binding.tvCurrentPage.text = text
        }
    }

    /**
     * Sets up the listeners for pagination buttons (Next, Previous) and search functionality.
     */
    private fun setupListeners() {
        binding.btnNext.setOnClickListener {
            viewModel.nextPage()
        }

        binding.btnPrevious.setOnClickListener {
            viewModel.previousPage()
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchGames(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
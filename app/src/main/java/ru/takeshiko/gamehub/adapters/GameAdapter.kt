package ru.takeshiko.gamehub.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.takeshiko.gamehub.R
import ru.takeshiko.gamehub.models.Game
import ru.takeshiko.gamehub.ui.GameDetailsActivity

/**
 * Adapter class for displaying a list of games in a RecyclerView.
 * This adapter binds the game data to the corresponding views, including the game title,
 * rating, and cover image. It also handles user interaction when a game item is clicked,
 * launching the GameDetailsActivity with detailed game information.
 *
 * @property games The list of games to be displayed in the RecyclerView.
 */
class GameAdapter(private val games: MutableList<Game> = mutableListOf()) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    /**
     * ViewHolder for each game item in the RecyclerView.
     * Holds references to the views that will display game information.
     */
    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_game_title)
        val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
        val tvRating: TextView = itemView.findViewById(R.id.tv_rating_value)
        val ivCover: ImageView = itemView.findViewById(R.id.iv_game_cover)
    }

    /**
     * Called when RecyclerView needs a new ViewHolder to represent an item.
     * Inflates the layout for the game item and creates a new ViewHolder.
     *
     * @param parent The ViewGroup into which the new view will be added.
     * @param viewType The view type of the new view.
     * @return A new GameViewHolder for the game item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game_card, parent, false)
        return GameViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * Binds the game data (title, rating, cover) to the views in the ViewHolder.
     *
     * @param holder The ViewHolder to bind the data to.
     * @param position The position of the game in the list.
     */
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.tvTitle.text = game.name
        holder.ratingBar.rating = (game.rating / 20).toFloat()
        holder.tvRating.text = holder.itemView.context.getString(R.string.rating_format, game.rating / 20.0)

        game.cover?.getFullUrl()?.let { url ->
            Glide.with(holder.itemView)
                .load(url)
                .placeholder(R.drawable.ic_placeholder_cover)
                .error(R.drawable.ic_placeholder_cover)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.ivCover)
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val platformNames = ArrayList(game.platforms.map { it.name })
            val intent = Intent(context, GameDetailsActivity::class.java).apply {
                putExtra("game_name", game.name)
                putExtra("game_description", game.summary)
                putExtra("game_cover_url", game.cover?.getFullUrl())
                putExtra("game_rating", game.rating)
                putStringArrayListExtra("game_platforms", platformNames)
                putExtra("game_release_date", game.firstReleaseDate.toString())
            }
            context.startActivity(intent)
        }
    }

    /**
     * Returns the total number of game items in the RecyclerView.
     *
     * @return The number of game items.
     */
    override fun getItemCount() = games.size

    /**
     * Updates the list of games with a new list.
     * Uses DiffUtil to calculate the minimal set of updates to apply to the RecyclerView.
     *
     * @param newGames The new list of games to be displayed.
     */
    fun updateList(newGames: List<Game>) {
        val diffCallback = GameDiffCallback(games, newGames)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        games.clear()
        games.addAll(newGames)
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * Updates a specific game item at the given position in the list.
     *
     * @param position The position of the game item to update.
     * @param game The updated game data.
     */
    fun updateItem(position: Int, game: Game) {
        games[position] = game
        notifyItemChanged(position)
    }

    /**
     * Inserts a new game item at the specified position in the list.
     *
     * @param position The position where the game item should be inserted.
     * @param game The new game data to insert.
     */
    fun insertItem(position: Int, game: Game) {
        games.add(position, game)
        notifyItemInserted(position)
    }

    /**
     * Removes a game item at the specified position from the list.
     *
     * @param position The position of the game item to remove.
     */
    fun removeItem(position: Int) {
        games.removeAt(position)
        notifyItemRemoved(position)
    }
}
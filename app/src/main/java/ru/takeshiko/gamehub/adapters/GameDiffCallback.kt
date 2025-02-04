package ru.takeshiko.gamehub.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.takeshiko.gamehub.models.Game

/**
 * DiffUtil callback for calculating the differences between two lists of games.
 * This class is used to efficiently update the RecyclerView when the data set changes.
 *
 * @param oldList The old list of games.
 * @param newList The new list of games.
 */
class GameDiffCallback(
    private val oldList: List<Game>,
    private val newList: List<Game>
) : DiffUtil.Callback() {

    /**
     * Returns the size of the old list.
     *
     * @return The size of the old list.
     */
    override fun getOldListSize(): Int = oldList.size

    /**
     * Returns the size of the new list.
     *
     * @return The size of the new list.
     */
    override fun getNewListSize(): Int = newList.size

    /**
     * Compares the items at the given positions in the old and new lists.
     * This method checks if the items are the same by comparing their unique identifiers.
     *
     * @param oldItemPosition The position of the item in the old list.
     * @param newItemPosition The position of the item in the new list.
     * @return True if the items are the same (based on their IDs), false otherwise.
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    /**
     * Compares the contents of the items at the given positions in the old and new lists.
     * This method checks if the contents of the items are the same.
     *
     * @param oldItemPosition The position of the item in the old list.
     * @param newItemPosition The position of the item in the new list.
     * @return True if the contents of the items are the same, false otherwise.
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
package com.moblie.ketchupapp.ui.views

import android.text.TextUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.room.withTransaction
import com.moblie.ketchupapp.model.SearchHistory
import com.moblie.ketchupapp.room.KetchupDatabase
import com.moblie.ketchupapp.room.SearchHistoryDao
import kotlinx.coroutines.launch

interface SearchDBCallback {

    val database: KetchupDatabase

    val db: SearchHistoryDao

    val scope: LifecycleCoroutineScope

    /**
     * Save a query to the local database.
     *
     * @param query - The query to be saved. Can't be empty or null.
     */
    fun saveQueryToDb(query: String) {
        scope.launch {
            if (!TextUtils.isEmpty(query)) {
                database.withTransaction {
                    db.insert(
                        SearchHistory(
                            query,
                            true,
                        )
                    )
                }
            }
        }
    }

    /**
     * Add a single suggestion item to the suggestion list.
     * @param suggestion - The suggestion to be inserted on the database.
     */
    fun addSuggestion(suggestion: String) {
        scope.launch {
            if (!TextUtils.isEmpty(suggestion)) {
                database.withTransaction {
                    db.insert(
                        SearchHistory(
                            suggestion,
                            false,
                        )
                    )
                }
            }
        }
    }

    /**
     * Removes a single suggestion from the list. <br></br>
     * Disclaimer, this doesn't remove a single search history item, only suggestions.
     * @param suggestion - The suggestion to be removed.
     */
    fun removeSuggestion(suggestion: String) {
        scope.launch {
            if (suggestion?.isNotEmpty()) {
                database.withTransaction {
                    db.remove(suggestion)
                }
            }
        }
    }

    fun addSuggestions(suggestions: List<String>) {
        scope.launch {
            database.withTransaction {
                db.insertAll(suggestions.map { SearchHistory(it, false) })
            }
        }
    }

    fun addSuggestions(suggestions: Array<String>) {
        val list = ArrayList(listOf(*suggestions))
        addSuggestions(list)
    }

    fun clearSuggestions() {
        scope.launch {
            database.withTransaction {
                db.clearAllSuggestions()
            }
        }
    }

    fun clearHistory() {
        scope.launch {
            database.withTransaction {
                db.clearAllHistory()
            }
        }
    }

    fun clearAll() {
        scope.launch {
            database.withTransaction {
                db.clearAll()
            }
        }
    }

    fun filter(toString: String) {
        scope.launch {

        }
    }
}
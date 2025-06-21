package com.ravisingh.klistdsldemo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * KList - A custom DSL for building list UIs with fluent API inspired by Compose Modifier pattern
 *
 * This class follows the builder pattern to create configurable lists with chainable methods.
 * Each method returns a new instance of KList with updated configuration, maintaining immutability.
 *
 * Usage example:
 * KList
 *     .padding(16.dp)
 *     .header("My List")
 *     .items(dataList) { item ->
 *         ItemComponent(item)
 *     }
 *     .render()
 */
class KList private constructor(
    private val paddingValue: Dp = 0.dp,
    private val headerTitle: String? = null,
    private val itemsList: List<Any> = emptyList(),
    private val itemContent: (@Composable (Any) -> Unit)? = null,
    private val clickAction: ((Any) -> Unit)? = null,
    private val showDividers: Boolean = false
) {

    companion object {
        /**
         * Entry point for the KList DSL
         * Creates a new KList instance with default configuration
         */
        val Default = KList()
    }

    /**
     * Sets padding for the entire list
     * @param dp Padding value in density-independent pixels
     * @return New KList instance with updated padding
     */
    fun padding(dp: Dp): KList {
        return KList(
            paddingValue = dp,
            headerTitle = headerTitle,
            itemsList = itemsList,
            itemContent = itemContent,
            clickAction = clickAction,
            showDividers = showDividers
        )
    }

    /**
     * Adds a header to the list
     * @param title The title text to display in the header
     * @return New KList instance with header configuration
     */
    fun header(title: String): KList {
        return KList(
            paddingValue = paddingValue,
            headerTitle = title,
            itemsList = itemsList,
            itemContent = itemContent,
            clickAction = clickAction,
            showDividers = showDividers
        )
    }

    /**
     * Configures the list items and their content
     * @param list The list of data items to display
     * @param itemContent Composable function to render each item
     * @return New KList instance with items configuration
     */
    fun <T> items(
        list: List<T>,
        itemContent: @Composable (T) -> Unit
    ): KList {
        return KList(
            paddingValue = paddingValue,
            headerTitle = headerTitle,
            itemsList = list as List<Any>, // Cast List<T> to List<Any>
            itemContent = { item ->
                @Suppress("UNCHECKED_CAST")
                itemContent(item as T)
            },
            clickAction = clickAction,
            showDividers = showDividers
        )
    }

    /**
     * Adds click handling to list items (Bonus feature)
     * @param onClick Callback function when an item is clicked
     * @return New KList instance with click configuration
     */
    fun <T> clickable(onClick: (T) -> Unit): KList {
        return KList(
            paddingValue = paddingValue,
            headerTitle = headerTitle,
            itemsList = itemsList,
            itemContent = itemContent,
            clickAction = { item ->
                @Suppress("UNCHECKED_CAST")
                onClick(item as T)
            },
            showDividers = showDividers
        )
    }

    /**
     * Enables dividers between list items (Bonus feature)
     * @return New KList instance with dividers enabled
     */
    fun withDividers(): KList {
        return KList(
            paddingValue = paddingValue,
            headerTitle = headerTitle,
            itemsList = itemsList,
            itemContent = itemContent,
            clickAction = clickAction,
            showDividers = true
        )
    }

    /**
     * Renders the configured list as a Composable
     * This method converts the DSL configuration into actual Compose UI components
     *
     * FIXED: Changed from LazyColumn to regular Column to avoid nested scrolling issues
     * when used inside a scrollable parent container
     */
    @Composable
    fun render() {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Changed from fillMaxSize to fillMaxWidth
                .padding(paddingValue),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Render header if configured
            headerTitle?.let { title ->
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Render the list items using regular Column instead of LazyColumn
            if (itemsList.isNotEmpty() && itemContent != null) {
                itemsList.forEachIndexed { index, item ->
                    // Render the item content
                    itemContent.invoke(item)

                    // Add divider if enabled (except for last item)
                    if (showDividers && index < itemsList.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp),
                            thickness = 1.dp
                        )
                    }

                    // Add spacing between items (except for last item)
                    if (index < itemsList.size - 1) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }

    /**
     * Alternative render method for single list use cases where LazyColumn is preferred
     * Use this when KList is the only scrollable content on the screen
     */
    @Composable
    fun renderAsLazyColumn() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            // Render header if configured
            headerTitle?.let { title ->
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Render the list items using LazyColumn for better performance with large lists
            if (itemsList.isNotEmpty() && itemContent != null) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(itemsList) { item ->
                        Column {
                            // Render the item content
                            itemContent.invoke(item)

                            // Add divider if enabled (except for last item)
                            if (showDividers && item != itemsList.last()) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    thickness = 1.dp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Extension properties to make KList usage more natural
 * Allows usage like: KList.padding(...) instead of KList.Default.padding(...)
 */
val KList.Companion.padding: (Dp) -> KList
    get() = { dp -> Default.padding(dp) }

val KList.Companion.header: (String) -> KList
    get() = { title -> Default.header(title) }
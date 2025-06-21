package com.ravisingh.klistdsldemo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.widget.Toast

/**
 * Demo composable showcasing various KList DSL usage patterns
 * This demonstrates the flexibility and power of the custom DSL
 */
@Composable
fun KListDemo() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Keep the outer scroll
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp) // Increased spacing
    ) {

        // Example 1: Basic KList with cryptocurrency data
        Text(
            text = "Example 1: Top Gainers",
            style = MaterialTheme.typography.headlineSmall
        )

        // This is the main usage pattern as requested in the assignment
        KList
            .padding(10.dp)
            .header(title = "Top Gainers")
            .items(SampleData.getTopGainers()) { coin ->
                KListItem(coin = coin)
            }
            .render() // Using regular render() method now

        // Example 2: KList with click handling (Bonus feature)
        Text(
            text = "Example 2: Clickable List",
            style = MaterialTheme.typography.headlineSmall
        )

        KList
            .padding(16.dp)
            .header("Top Losers (Clickable)")
            .items(SampleData.getTopLosers()) { coin ->
                KListItem(
                    coin = coin,
                    onClick = {
                        Toast.makeText(
                            context,
                            "Clicked on ${coin.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
            .render()

        // Example 3: Simple string list with dividers (Bonus feature)
        Text(
            text = "Example 3: Simple List with Dividers",
            style = MaterialTheme.typography.headlineSmall
        )

        KList
            .padding(12.dp)
            .header("Simple Items")
            .items(SampleData.getSimpleStringList()) { item ->
                SimpleKListItem(
                    text = item,
                    onClick = {
                        Toast.makeText(
                            context,
                            "Selected: $item",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
            .withDividers()
            .render()
    }
}

/**
 * Alternative usage pattern for single list scenarios
 * This shows how the DSL can be used with LazyColumn for better performance
 */
@Composable
fun HomeScreen() {
    // This demonstrates the exact usage pattern mentioned in the assignment
    // Using renderAsLazyColumn() for single list scenarios
    KList
        .padding(16.dp)
        .header("Coins")
        .items(SampleData.getTopGainers()) { coin ->
            KListItem(coin)
        }
        .renderAsLazyColumn() // Use LazyColumn version for single list
}

/**
 * Advanced usage example showing multiple sections
 * This demonstrates the flexibility of the DSL for complex UIs
 */
@Composable
fun AdvancedKListDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Outer scroll container
    ) {
        // Multiple KList instances can be composed together
        KList
            .padding(16.dp)
            .header("🚀 Top Gainers")
            .items(SampleData.getTopGainers().take(3)) { coin ->
                KListItem(coin)
            }
            .render() // Regular render for nested usage

        Spacer(modifier = Modifier.height(24.dp))

        KList
            .padding(16.dp)
            .header("📉 Top Losers")
            .items(SampleData.getTopLosers()) { coin ->
                KListItem(coin)
            }
            .withDividers()
            .render() // Regular render for nested usage
    }
}

/**
 * Single KList demo showing LazyColumn usage
 * Use this pattern when you have only one list on the screen
 */
@Composable
fun SingleKListDemo() {
    // No outer scrolling container needed - LazyColumn handles it
    KList
        .padding(16.dp)
        .header("All Cryptocurrencies")
        .items(SampleData.getTopGainers() + SampleData.getTopLosers()) { coin ->
            KListItem(coin)
        }
        .renderAsLazyColumn() // LazyColumn for better performance with large lists
}

// Preview functions for Android Studio
@Preview(showBackground = true, name = "Multiple Lists Demo")
@Composable
fun KListDemoPreview() {
    MaterialTheme {
        KListDemo()
    }
}

@Preview(showBackground = true, name = "Single List (LazyColumn)")
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "Advanced Demo")
@Composable
fun AdvancedKListDemoPreview() {
    MaterialTheme {
        AdvancedKListDemo()
    }
}

@Preview(showBackground = true, name = "Single KList Demo")
@Composable
fun SingleKListDemoPreview() {
    MaterialTheme {
        SingleKListDemo()
    }
}
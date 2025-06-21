package com.ravisingh.klistdsldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ravisingh.klistdsldemo.ui.theme.KListTheme

/**
 * Main Activity demonstrating the KList DSL
 * Sets up the app with Material 3 theming and displays the demo
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Choose which demo to display:

                    // Multiple scrollable lists demo (FIXED - no more crashes)
                    KListDemo()

                    // Single list with LazyColumn (optimal performance)
                    // HomeScreen()

                    // Advanced multi-section demo
                    // AdvancedKListDemo()

                    // Single large list demo
                    // SingleKListDemo()
                }
            }
        }
    }
}
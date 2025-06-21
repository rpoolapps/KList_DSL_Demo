package com.ravisingh.klistdsldemo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * KListItem - A reusable item component for displaying cryptocurrency data
 *
 * This component demonstrates how to create reusable UI components that work
 * seamlessly with the KList DSL. It follows Material Design 3 principles.
 *
 * @param coin The cryptocurrency data to display
 * @param onClick Optional click handler for the item
 */
@Composable
fun KListItem(
    coin: Coin,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Coin information section
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = coin.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = coin.symbol,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Price and change section
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${coin.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Change percentage with color coding
                val changeColor = if (coin.changePercent >= 0) {
                    Color(0xFF4CAF50) // Green for positive
                } else {
                    Color(0xFFE53E3E) // Red for negative
                }

                val changeText = if (coin.changePercent >= 0) {
                    "+${coin.changePercent}%"
                } else {
                    "${coin.changePercent}%"
                }

                Text(
                    text = changeText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = changeColor,
                    modifier = Modifier
                        .background(
                            color = changeColor.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
    }
}

/**
 * Alternative simple list item for basic text display
 * Useful for simple string lists or when you need minimal styling
 */
@Composable
fun SimpleKListItem(
    text: String,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            ),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
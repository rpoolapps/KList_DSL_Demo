
# KList DSL - Custom List Builder for Jetpack Compose

## 🚀 Features

### Core Features
- **Fluent API Design**: Chain methods like `KList.padding().header().items().render()`
- **Immutable Builder Pattern**: Each method returns a new instance for thread safety
- **Jetpack Compose Integration**: Seamless integration with Compose UI toolkit
- **Type-Safe Generic Support**: Works with any data type using Kotlin generics

### Bonus Features
- **Click Handling**: Built-in support for item click events
- **Dividers**: Optional dividers between list items
- **Multiple Sections**: Support for complex multi-section layouts
- **Material 3 Design**: Follows latest Material Design principles

### Key Components

1. **KList.kt** - Core DSL implementation
   - Immutable builder pattern
   - Chainable configuration methods
   - Compose rendering logic

2. **KListItem.kt** - Reusable item components
   - Material 3 Card design
   - Cryptocurrency-specific styling
   - Generic simple item component

3. **Models.kt** - Data models and sample data
   - Coin data class
   - Sample data provider
   - Mock cryptocurrency data

4. **KListDemo.kt** - Usage demonstrations
   - Multiple example patterns
   - Preview functions for Android Studio
   - Advanced usage scenarios

## 📖 Usage Examples

### Basic Usage (As Required)
```kotlin
@Composable
fun HomeScreen() {
    KList
        .padding(16.dp)
        .header("Coins")
        .items(coinList) { coin ->
            KListItem(coin)
        }
        .render()
}
```

### Advanced Usage with Bonus Features
```kotlin
KList
    .padding(10.dp)
    .header(title = "Top Gainers")
    .items(listOfCoins) { coin ->
        KListItem(
            coin = coin,
            onClick = { handleCoinClick(coin) }
        )
    }
    .withDividers()
    .render()
```

### Multiple Sections
```kotlin
Column {
    KList
        .padding(16.dp)
        .header("🚀 Top Gainers")
        .items(gainers) { KListItem(it) }
        .render()
    
    KList
        .padding(16.dp)
        .header("📉 Top Losers")
        .items(losers) { KListItem(it) }
        .render()
}
```

## 🏗️ Setting Up in Android Studio

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- Kotlin 1.9.0+
- Compose BOM 2023.10.01+
- Min SDK 24, Target SDK 34

### Step-by-Step Setup

1. **Create New Project**
   ```
   File → New → New Project
   Choose "Empty Activity" with Compose
   Set package name: com.example.klist
   ```

2. **Project Structure**
   ```
   app/src/main/java/com/example/klist/
   ├── MainActivity.kt
   ├── KList.kt
   ├── KListItem.kt
   ├── Models.kt
   ├── KListDemo.kt
   └── ui/theme/
       └── Theme.kt
   ```

3. **Copy Files**
   - Copy all the provided code files into their respective locations
   - Update your `build.gradle.kts` with the provided dependencies

4. **Sync and Run**
   ```
   Tools → Sync Project with Gradle Files
   Run → Run 'app'
   ```

### Dependencies Required
```kotlin
// In build.gradle.kts (Module: app)
implementation("androidx.compose.material3:material3:1.1.2")
implementation("androidx.activity:activity-compose:1.8.1")
implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
```

## 🔧 API Reference

### KList Methods

| Method | Parameters | Description | Returns |
|--------|------------|-------------|---------|
| `padding()` | `dp: Dp` | Sets padding around the list | `KList` |
| `header()` | `title: String` | Adds header text to the list | `KList` |
| `items()` | `list: List<T>`, `itemContent: @Composable (T) -> Unit` | Configures list items and their rendering | `KList` |
| `clickable()` | `onClick: (T) -> Unit` | Adds click handling to items | `KList` |
| `withDividers()` | None | Enables dividers between items | `KList` |
| `render()` | None | Renders the configured list | `@Composable Unit` |



## 🎨 Design Patterns Used

1. **Builder Pattern**: Fluent API with method chaining
2. **Immutable Objects**: Each method returns new instance
3. **DSL (Domain Specific Language)**: Custom syntax for UI building
4. **Generic Programming**: Type-safe item handling
5. **Composition over Inheritance**: Compose UI components


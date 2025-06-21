
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


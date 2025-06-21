package com.ravisingh.klistdsldemo

/**
 * Data class representing a cryptocurrency
 * Used for demonstration purposes in the KList DSL
 */
data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val price: Double,
    val changePercent: Double
)

/**
 * Sample data provider for testing and demonstration
 */
object SampleData {
    /**
     * Generates sample cryptocurrency data for testing
     * In a real app, this would come from an API or database
     */
    fun getTopGainers(): List<Coin> = listOf(
        Coin("btc", "Bitcoin", "BTC", 45250.50, 5.67),
        Coin("eth", "Ethereum", "ETH", 3250.75, 8.23),
        Coin("ada", "Cardano", "ADA", 1.25, 12.45),
        Coin("sol", "Solana", "SOL", 98.30, 15.67),
        Coin("dot", "Polkadot", "DOT", 22.45, 9.12),
        Coin("avax", "Avalanche", "AVAX", 78.90, 18.34),
        Coin("luna", "Terra Luna", "LUNA", 45.67, 7.89),
        Coin("atom", "Cosmos", "ATOM", 28.45, 11.23)
    )

    fun getTopLosers(): List<Coin> = listOf(
        Coin("doge", "Dogecoin", "DOGE", 0.08, -8.45),
        Coin("shib", "Shiba Inu", "SHIB", 0.000025, -12.67),
        Coin("icp", "Internet Computer", "ICP", 28.90, -15.23),
        Coin("etc", "Ethereum Classic", "ETC", 32.45, -6.78)
    )

    fun getSimpleStringList(): List<String> = listOf(
        "First Item",
        "Second Item",
        "Third Item",
        "Fourth Item",
        "Fifth Item"
    )
}
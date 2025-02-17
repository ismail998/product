package com.ismailbelgacem.produit.core

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



val aisleNumbers = hashMapOf(
    "banana" to 1,
    "grapes" to 1,
    "apple" to 1,
    "watermelon" to 1,
    "peach" to 1,
    "pineapple" to 1,
    "lemon" to 1,
    "carrot" to 2,
    "corn" to 2,
    "eggs" to 3,
    "milk" to 3,
    "cheese" to 3,
    "fish" to 4,
    "chicken" to 4,
    "bread" to 5,
    "cake" to 5,
    "donut" to 5,
    "cookie" to 5,
    "popcorn" to 6,
    "chocolate" to 6,
    "pizza" to 7,
    "hamburger" to 7,
    "hotdog" to 7,
    "taco" to 7,
    "salad" to 7,
    "coffee" to 8,
    "tea" to 8,
    "sushi" to 8
)



val productEmojiMap = hashMapOf(
    "eggs" to "🥚",
    "milk" to "🥛",
    "bread" to "🍞",
    "cheese" to "🧀",
    "banana" to "🍌",
    "apple" to "🍎",
    "carrot" to "🥕",
    "fish" to "🐟",
    "chicken" to "🍗",
    "cake" to "🍰",
    "grapes" to "🍇",
    "watermelon" to "🍉",
    "peach" to "🍑",
    "pineapple" to "🍍",
    "strawberry" to "🍓",
    "taco" to "🌮",
    "pizza" to "🍕",
    "hamburger" to "🍔",
    "hotdog" to "🌭",
    "salad" to "🥗",
    "coffee" to "☕",
    "tea" to "🍵",
    "sushi" to "🍣",
    "popcorn" to "🍿",
    "chocolate" to "🍫",
    "ice cream" to "🍦",
    "donut" to "🍩",
    "cookie" to "🍪",
    "lemon" to "🍋",
    "corn" to "🌽"
)

fun getEmojiForProduct(product: String): String {
    return productEmojiMap[product] ?: "❓"
}
fun getAisleForProduct(product: String): Int {
    return aisleNumbers[product] ?: -1
}
fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        flow.collectLatest(collect)
    }
}
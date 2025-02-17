package com.ismailbelgacem.produit.core

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        flow.collectLatest(collect)
    }
}
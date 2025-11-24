package com.randomperson22.piggymodtest.interfaces

interface IBot {
    // 1 = low, 2 = high
    val priority: Int

    fun getStunned(ticks: Int)
}

package com.randomperson22.piggymodtest.interfaces;

public interface IBot {
    int getPriority(); // 1 = low, 2 = high
    void getStunned(int ticks);
    boolean canBeTargeted(); // returns false if currently stunned
}

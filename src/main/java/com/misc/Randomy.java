package com.misc;

import java.util.Random;

public class Randomy {

    public int generateRandomNumber(int range) {
        Random random = new Random();
        return random.nextInt(range + 1);
    }
}

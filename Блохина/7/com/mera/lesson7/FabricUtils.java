package com.mera.lesson7;

import java.util.concurrent.ThreadLocalRandom;

public class FabricUtils {
    public static int getRandom(int begin, int end){
        return ThreadLocalRandom.current().nextInt(begin, end);
    }

}

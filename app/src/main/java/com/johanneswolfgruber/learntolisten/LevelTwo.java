package com.johanneswolfgruber.learntolisten;


class LevelTwo {
    private static int levelTime = 60000;

    private static String[] mLevelTwo = {"LEFT_W", "EMPTY"       , "EMPTY", "EMPTY"   , "INT_HIGH_LOW", "HIGH_LOW", "LADDER"     , "EMPTY", "RIGHT_W",
                                         "LEFT_W", "MAJOR_MINOR" , "EMPTY", "LADDER"  , "MAJOR_MINOR" , "HIGH_LOW", "INVERSION"  , "EMPTY", "RIGHT_W",
                                         "LEFT_W", "INT_HIGH_LOW", "EMPTY", "INTERVAL", "INVERSION"   , "HIGH_LOW", "MAJOR_MINOR", "EMPTY", "FINISH"  };

    static String[] getLevelTwo() {
        return mLevelTwo;
    }

    static int getLevelTime() {
        return levelTime;
    }

}

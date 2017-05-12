package com.johanneswolfgruber.learntolisten;


class LevelOne {
    private static String[] mLevelOne = {"LEFT_W", "HIGH_LOW"    , "EMPTY", "INTERVAL", "INVERSION", "EMPTY"   , "LADDER"     , "EMPTY", "RIGHT_W",
                                         "LEFT_W", "MAJOR_MINOR" , "EMPTY", "LADDER"  , "EMPTY"    , "HIGH_LOW", "INVERSION"  , "EMPTY", "RIGHT_W",
                                         "LEFT_W", "INT_HIGH_LOW", "EMPTY", "INTERVAL", "INVERSION", "HIGH_LOW", "MAJOR_MINOR", "EMPTY", "FINISH"  };

    static String[] getLevelOne() {
        return mLevelOne;
    }
}

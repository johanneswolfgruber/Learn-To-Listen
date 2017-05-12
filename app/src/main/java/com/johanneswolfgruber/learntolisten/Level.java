package com.johanneswolfgruber.learntolisten;


class Level {
    /*
    private static String[] mLevel = {"LEFT_W", "HIGH_LOW"    , "EMPTY", "INTERVAL", "INVERSION", "EMPTY"   , "LADDER"     , "EMPTY", "RIGHT_W",
            "LEFT_W", "MAJOR_MINOR" , "EMPTY", "LADDER"  , "EMPTY"    , "HIGH_LOW", "INVERSION"  , "EMPTY", "RIGHT_W",
            "LEFT_W", "INT_HIGH_LOW", "EMPTY", "INTERVAL", "INVERSION", "HIGH_LOW", "MAJOR_MINOR", "EMPTY", "FINISH"  };
    */
    private String[] mLevel;

    void setLevelIndex(int level) {
        switch (level) {
            case 1:
                mLevel = LevelOne.getLevelOne();
                break;
            case 2:
                mLevel = LevelTwo.getLevelTwo();
                break;
        }
    }

    int numberOfFieldsPerRow(){
        return 9;
    }

    String getCurrentFieldValue(int index){
        return mLevel[index];
    }

}

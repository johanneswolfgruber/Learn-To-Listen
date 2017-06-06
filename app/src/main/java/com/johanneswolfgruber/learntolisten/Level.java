package com.johanneswolfgruber.learntolisten;


class Level {
    private String[] mLevel;

    void setLevelIndex(int level) {
        switch (level) {
            case 1:
                mLevel = GameActivity.getLevelOne();
                break;
            case 2:
                mLevel = GameActivity.getLevelTwo();
                break;
        }
    }

    int getLevelTime(int level) {
        switch (level) {
            case 1:
                int mLevelTime = LevelOne.getLevelTime();
                return mLevelTime;
            case 2:
                mLevelTime = LevelTwo.getLevelTime();
                return mLevelTime;
        }
        return 0;
    }

    void setCurrentFieldValue(int index){
        mLevel[index] = "EMPTY";
    }

    int numberOfFieldsPerRow(){
        return 9;
    }

    String getCurrentFieldValue(int index){
        return mLevel[index];
    }

}

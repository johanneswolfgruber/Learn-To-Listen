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
                return 45000;
            case 2:
                return 60000;
        }
        return 0;
    }

    void setCurrentFieldValue(int index){
        mLevel[index] = "EMPTY";
    }

    int numberOfFieldsPerRow(int level){
        switch (level) {
            case 1:
                return 9;
            case 2:
                return 9;
        }
        return 0;
    }

    String getCurrentFieldValue(int index){
        return mLevel[index];
    }

}

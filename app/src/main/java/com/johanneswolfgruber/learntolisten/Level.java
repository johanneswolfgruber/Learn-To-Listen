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
            case 3:
                mLevel = GameActivity.getLevelThree();
        }
    }

    int getLevelTime(int level) {
        switch (level) {
            case 1:
                return 45000;
            case 2:
                return 60000;
            case 3:
                return 90000;
        }
        return 0;
    }

    void setCurrentFieldValue(int index, String Value){
        mLevel[index] = Value;
    }

    int numberOfFieldsPerRow(int level){
        switch (level) {
            case 1:
                return 9;
            case 2:
                return 9;
            case 3:
                return 9;
        }
        return 0;
    }

    int getExerciseCount(int level) {
        switch (level) {
            case 1:
                return 11;
            case 2:
                return 12;
            case 3:
                return 13;
        }
        return 0;
    }

    int getFieldCount(int level) {
        switch (level) {
            case 1:
                return 26;
            case 2:
                return 26;
            case 3:
                return 44;
        }
        return 0;
    }

    String getCurrentFieldValue(int index){
        return mLevel[index];
    }

}

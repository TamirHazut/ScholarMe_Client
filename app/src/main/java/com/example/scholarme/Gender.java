package com.example.scholarme;

public enum Gender {
    FEMALE(0), MALE(1);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public Gender getGenderByValue(int value) {
        if (value >= Gender.values().length) {
            return Gender.MALE;
        }
        return Gender.values()[value];
    }

    @Override
    public String toString() {
        return this.name();
    }
}

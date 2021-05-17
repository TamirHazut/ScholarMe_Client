package com.example.scholarme;

public enum Gender {
    FEMALE, MALE;

    @Override
    public String toString() {
        return this.name();
    }
}

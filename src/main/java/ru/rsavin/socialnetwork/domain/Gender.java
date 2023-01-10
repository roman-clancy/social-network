package ru.rsavin.socialnetwork.domain;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE");
    public final String value;
    Gender(String value) {
        this.value = value;
    }
}

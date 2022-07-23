package com.mysite.sbb.User;

import lombok.Getter;

@Getter
public enum UserRole { // enum : 열거자료형
    ADMIN("ROLE_ADMIN"),
        USER("ROLE_URER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}

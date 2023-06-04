package com.ua.vinn.GammaLight.models.securityElements;

public enum Permission {

    READ_CONTENT("content:read"),
    WRITE_CONTENT("content:write"),
    DELETE_CONTENT("content:delete"),
    DELETE_USERS("user:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

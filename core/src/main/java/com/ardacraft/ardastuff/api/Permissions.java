package com.ardacraft.ardastuff.api;

public enum Permissions {
    WATER_SPREAD("metatweaks.cwaterspread");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    /**
     * @return The permission node
     */
    public String getPermission() {
        return permission;
    }

    @Override
    public String toString() {
        return permission;
    }
}

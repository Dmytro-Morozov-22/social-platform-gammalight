package com.ua.vinn.GammaLight.models.securityElements;

import org.springframework.security.access.method.P;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.READ_CONTENT)),
    MODERATOR(Set.of(Permission.READ_CONTENT, Permission.WRITE_CONTENT, Permission.DELETE_CONTENT)),
    ADMINISTRATOR(Set.of(Permission.READ_CONTENT, Permission.WRITE_CONTENT, Permission.DELETE_CONTENT, Permission.DELETE_USERS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}

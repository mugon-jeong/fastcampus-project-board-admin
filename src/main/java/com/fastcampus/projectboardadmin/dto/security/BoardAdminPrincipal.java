package com.fastcampus.projectboardadmin.dto.security;

import com.fastcampus.projectboardadmin.domain.constant.RoleType;
import com.fastcampus.projectboardadmin.dto.UserAccountDto;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public record BoardAdminPrincipal(
    String username,
    String password,
    Collection<? extends GrantedAuthority> getAuthorities,
    String email,
    String nickname,
    String memo,
    Map<String, Object> oauth2Attributes
) implements UserDetails, OAuth2User {

    public static BoardAdminPrincipal of(String username, String password, Set<RoleType> roleTypes,
        String email, String nickname, String memo) {
        return BoardAdminPrincipal.of(username, password, roleTypes, email, nickname, memo,
            Map.of());
    }

    public static BoardAdminPrincipal of(String username, String password, Set<RoleType> roleTypes,
        String email, String nickname, String memo, Map<String, Object> oauth2Attributes) {
        return new BoardAdminPrincipal(
            username,
            password,
            roleTypes.stream()
                     .map(RoleType::getRoleName)
                     .map(SimpleGrantedAuthority::new)
                     .collect(Collectors.toUnmodifiableSet()),
            email,
            nickname,
            memo,
            oauth2Attributes
        );
    }

    public static BoardAdminPrincipal from(UserAccountDto dto) {
        return BoardAdminPrincipal.of(
            dto.userId(),
            dto.userPassword(),
            dto.roleTypes(),
            dto.email(),
            dto.nickname(),
            dto.memo()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
            username,
            password,
            getAuthorities.stream()
                          .map(GrantedAuthority::getAuthority)
                          .map(RoleType::valueOf)
                          .collect(Collectors.toUnmodifiableSet()),
            email,
            nickname,
            memo
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // oauth client에서 필요로 하는 요소
    @Override
    public Map<String, Object> getAttributes() {
        return oauth2Attributes;
    }

    @Override
    public String getName() {
        return username;
    }


}

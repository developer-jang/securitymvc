package com.example.securitymvc.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class SecurityUser extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private Member memeber;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUser(Member memeber) {
        super(memeber.getUrid(), memeber.getPassword(), makeGrantedAuthority(memeber.getRoleList()));
        this.memeber = memeber;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<Role> roleList) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roleList.forEach(
                role -> grantedAuthorities.add(
                        new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return grantedAuthorities;
    }


}

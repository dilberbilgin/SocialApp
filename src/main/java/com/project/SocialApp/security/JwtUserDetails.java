//package com.project.SocialApp.security;
//
//import com.project.SocialApp.entity.User;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Getter
//@Setter
//public class JwtUserDetails implements UserDetails {
//
//    public Long id;
//    private String username;
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    private JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static JwtUserDetails create(User user) {
//        List<GrantedAuthority> authoritiesList = new ArrayList<>();
//        authoritiesList.add(new SimpleGrantedAuthority("user"));
//        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getAuthorities());
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
//}

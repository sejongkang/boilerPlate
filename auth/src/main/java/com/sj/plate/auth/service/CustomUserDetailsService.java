package com.sj.plate.auth.service;


import com.mco.pc_store.core.service.AdminAuthService;
import com.mco.pc_store.core.vo.AuthUserVO;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminAuthService adminAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserVO userInfo = adminAuthService.selectApiUser(username);
        if (userInfo != null) {
            return createUserDetails(userInfo);
        }
        throw(new UsernameNotFoundException(username + "없는 사용자입니다."));
    }
    private UserDetails createUserDetails(AuthUserVO member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("admin");

        return new User(
                String.valueOf(member.getUsername()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

}

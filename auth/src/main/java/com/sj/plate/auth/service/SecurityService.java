package com.sj.plate.auth.service;

import com.mco.pc_store.admin.security.AuthLoginVO;
import com.mco.pc_store.admin.security.AuthTokenVO;
import com.mco.pc_store.admin.security.TokenProvider;
import com.mco.pc_store.core.domain.request.AdminUserAuthReq;
import com.mco.pc_store.core.response.error.BusinessException;
import com.mco.pc_store.core.response.error.StatusCode;
import com.mco.pc_store.core.service.AdminAuthService;
import com.mco.pc_store.core.vo.AuthUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final AdminAuthService adminAuthService;

    public AuthTokenVO userLogin(AuthLoginVO user) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);

        int userAuth = 0;

        AuthTokenVO authTokenVO = tokenProvider.generateTokenDto(authentication, userAuth, "login",
            null);

        authTokenVO.setAccessName(adminAuthService.getUserName(user.getUsername()));
        authTokenVO.setAuthority(userAuth);

        return authTokenVO;
    }

    public void signup(AdminUserAuthReq userAuthReqVO) {
        if (adminAuthService.selectApiUser(userAuthReqVO.getUserName()) == null) {
            userAuthReqVO.setPassword(passwordEncoder.encode(userAuthReqVO.getPassword()));
            if (adminAuthService.registUser(userAuthReqVO) == 0) {
                throw new BusinessException(StatusCode.USER_DB_ERROR);
            }
        } else {
            throw new BusinessException(StatusCode.USER_DUPLICATE_ERROR);
        }
    }

    public void updateUserPassword(UserDetails userDetails, String password, String newPassword) {

        AuthUserVO user = adminAuthService.selectApiUser(userDetails.getUsername());
        if (passwordEncoder.matches(password, user.getPassword())) {
            AdminUserAuthReq userAuthReq = AdminUserAuthReq.builder()
                .userName(userDetails.getUsername()).password(passwordEncoder.encode(newPassword))
                .build();
            if (adminAuthService.changePassword(userAuthReq) == 0) {
                throw new BusinessException(StatusCode.USER_DB_ERROR);
            }
        } else {
            throw new BusinessException(StatusCode.INVALID_PASSWORD_ERROR);
        }
    }

}

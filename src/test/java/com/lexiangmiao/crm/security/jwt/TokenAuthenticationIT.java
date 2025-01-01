package com.lexiangmiao.crm.security.jwt;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@AuthenticationIntegrationTest
class TokenAuthenticationIT {

    @Autowired
    private MockMvc mvc;

    @Value("${jhipster.security.authentication.jwt.base64-secret}")
    private String jwtKey;

    @Test
    void testLoginWithValidToken() throws Exception {
        expectOk(JwtAuthenticationTestUtils.createValidToken(jwtKey));
    }

    @Test
    void testReturnFalseWhenJWThasInvalidSignature() throws Exception {
        expectUnauthorized(JwtAuthenticationTestUtils.createTokenWithDifferentSignature());
    }

    @Test
    void testReturnFalseWhenJWTisMalformed() throws Exception {
        expectUnauthorized(JwtAuthenticationTestUtils.createSignedInvalidJwt(jwtKey));
    }

    @Test
    void testReturnFalseWhenJWTisExpired() throws Exception {
        expectUnauthorized(JwtAuthenticationTestUtils.createExpiredToken(jwtKey));
    }

    private void expectOk(String token) throws Exception {
        mvc
            .perform(MockMvcRequestBuilders.get("/api/authenticate").header(AUTHORIZATION, JwtAuthenticationTestUtils.BEARER + token))
            .andExpect(status().isOk());
    }

    private void expectUnauthorized(String token) throws Exception {
        mvc
            .perform(MockMvcRequestBuilders.get("/api/authenticate").header(AUTHORIZATION, JwtAuthenticationTestUtils.BEARER + token))
            .andExpect(status().isUnauthorized());
    }
}

package com.oauth2.handler;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ClientDetailsService clientDetailsService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        String header = request.getHeader("Authorization");
//        String name = authentication.getName();
//        if (header == null || header.startsWith("Basic ")) {
//            throw new UnapprovedClientAuthenticationException("请求头没有client信息");
//        }
//        String[] tokens = extract(header);
        String clientId = "clientid";//tokens[0];
        String secret = "secret";//tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId信息不存在");
        }
        if (!secret.equals(clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("getClientSecret 不匹配");
        }
        Map<String, String> requestParameters = new HashMap<>();
        TokenRequest tokenRequest = new TokenRequest(requestParameters, clientId, clientDetails.getScope(), "custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        response.setContentType("application/json;charset=UTf-8");
        response.getWriter().write(objectMapper.writeValueAsString(accessToken));
    }
    private String[] extract(String header) {
        String token = null;
        try {
            byte[] base64Token = header.substring(6).getBytes("UTF-8");
            byte[] decodeToken = Base64.getDecoder().decode(base64Token);
            token = new String(decodeToken, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new UnapprovedClientAuthenticationException("请求头没有client信息");
        }
        return new String[] {token.substring(0, delim), token.substring(delim+1)};



    }
}

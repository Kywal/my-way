package br.ufrn.myway.Service.AuthService;

import br.ufrn.myway.model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Service.BusinessException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthorizationDecoder {

    @Bean
    public static String[] decodeBasicAuth(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring("Basic ".length());
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
            return credentials.split(":", 2); // Splits into [email, password]
        }
        throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_LOGIN.getMessage());
    }
}

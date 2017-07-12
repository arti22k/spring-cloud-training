package pl.training.cloud.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
           String token =  ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext()
                   .getAuthentication().getDetails()).getTokenValue();
            template.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, token));
    }

}

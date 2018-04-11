package io.office360.restapi.spring;

import io.office360.restapi.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class Office360AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()");
        super.configure(security);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()

                .withClient("trusted-app")
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
//                .authorities(Role.ROLE_TRUSTED_CLIENT.toString())
                .scopes("read", "write")
                .resourceIds(resourceId)
                .accessTokenValiditySeconds(10)
                .refreshTokenValiditySeconds(30000)
                .secret("secret")
                .and()
                .withClient("register-app")
                .authorizedGrantTypes("client_credentials")
//                .authorities(Role.ROLE_REGISTER.toString())
                .scopes("registerUser")
                .accessTokenValiditySeconds(10)
                .refreshTokenValiditySeconds(10)
                .resourceIds(resourceId)
                .secret("secret");
    }


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());

    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(
                        new ClassPathResource("ms-auth.jks"),
                        "ms-auth-pass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("ms-auth"));
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenService = new DefaultTokenServices();
        tokenService.setTokenStore(tokenStore());
        tokenService.setSupportRefreshToken(true);
        tokenService.setTokenEnhancer(accessTokenConverter());
        return tokenService;
    }

}
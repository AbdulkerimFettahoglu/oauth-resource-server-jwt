package dev.kerimfettahoglu.oauthresourceserver.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RSAKeyProperties rsaKeyProperties;

    @Bean
    public InMemoryUserDetailsManager user() {
        return new InMemoryUserDetailsManager(User.withUsername("kerim").password("{noop}kerim").authorities("READ").build());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests(auth ->
                        auth.anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    /*public static final String DEFAULT_ENCODED_SECRET_KEY = "bCzY/M48bbkwBEWjmNSIEPfwApcvXOnkCxORBEbPr+4=";
    public static final SecretKey DEFAULT_SECRET_KEY = new SecretKeySpec(Base64.getDecoder().decode(DEFAULT_ENCODED_SECRET_KEY), "AES");*/
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.getPublicKey()).build();
    }
}

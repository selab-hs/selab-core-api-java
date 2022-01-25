package kr.ac.hs.selab.common.config;

import javax.validation.constraints.NotNull;
import kr.ac.hs.selab.auth.jwt.JwtAccessDeniedHandler;
import kr.ac.hs.selab.auth.jwt.JwtAuthenticationEntryPoint;
import kr.ac.hs.selab.auth.jwt.JwtSecurityConfig;
import kr.ac.hs.selab.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @NotNull
    private final JwtTokenProvider tokenProvider;
    @NotNull
    private final CorsConfig corsConfig;
    @NotNull
    private final SwaggerConfig swaggerConfig;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable();

        httpSecurity
            .cors()
            .configurationSource(corsConfigurationSource());

        httpSecurity
            .exceptionHandling()
            .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            .accessDeniedHandler(new JwtAccessDeniedHandler())

            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers(swaggerConfig.whiteListInSwagger()).permitAll()
            .antMatchers("/api/v1/auth/login").permitAll()
            .antMatchers("/api/v1/members").permitAll()
            .antMatchers("/api/**/admin/**").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers("/api/**").hasAnyAuthority("ROLE_USER")
            .anyRequest().authenticated()

            .and()
            .apply(new JwtSecurityConfig(tokenProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        return corsConfig.corsConfigurationSource();
    }
}
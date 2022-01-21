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

    private final JwtTokenProvider tokenProvider;
    private final CorsConfig corsConfig;
    private final SwaggerConfig swaggerConfig;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable();

        httpSecurity
            .cors()
            .configurationSource(CorsConfigurationSource());

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

            // TODO : Admin 및 일반 유저에 대한 필터링 기능 제공하기
            .and()
            .authorizeRequests()
            .antMatchers(swaggerConfig.whiteListInSwagger()).permitAll()
            .antMatchers("/api/v1/auth/login").permitAll()
            .antMatchers("/api/v1/members").permitAll()
            //   .antMatchers("/api/**/admin/**").hasAnyAuthority("ROLE_ADMIN")
            //  .antMatchers("/api/**").hasAnyAuthority("ROLE_USER")
            .anyRequest().authenticated()

            .and()
            .apply(new JwtSecurityConfig(tokenProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    private UrlBasedCorsConfigurationSource CorsConfigurationSource() {
        return corsConfig.corsConfigurationSource();
    }

}
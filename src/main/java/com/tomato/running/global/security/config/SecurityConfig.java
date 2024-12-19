package com.tomato.running.global.security.config;
import com.tomato.running.global.security.handler.JwtAccessDeniedHandle;
import com.tomato.running.global.security.handler.JwtAuthenticationEntryPoint;
import com.tomato.running.global.security.jwt.JwtFilter;
import com.tomato.running.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAccessDeniedHandle jwtAccessDeniedHandle;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionConfig ->
                        exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandle)
                )

                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                                // auth
                                .requestMatchers(HttpMethod.POST, "/auth/join").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/naver").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/auth").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/auth").authenticated()

                                // user
                                .requestMatchers(HttpMethod.PATCH, "/user").authenticated()
                                .requestMatchers(HttpMethod.GET, "/user").authenticated()
                                .requestMatchers(HttpMethod.GET, "/user/meetings").authenticated()
                                .requestMatchers(HttpMethod.GET, "/user/meetings/application").authenticated()

                                // run
                                .requestMatchers(HttpMethod.POST, "/run").authenticated()

                                //meeting
                                .requestMatchers(HttpMethod.POST, "/meeting").authenticated()
                                .requestMatchers(HttpMethod.GET, "/meeting").authenticated()
                                .requestMatchers(HttpMethod.GET, "/meeting/{meeting_id}").authenticated()
                                .requestMatchers(HttpMethod.PATCH, "/meeting/{meeting_id}").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/meeting/{meeting_id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/meeting/search").authenticated()


                                .anyRequest().authenticated()
                )

                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}

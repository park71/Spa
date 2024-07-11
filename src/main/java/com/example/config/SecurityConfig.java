package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests((auth) ->auth
                        .requestMatchers("home","/login","/loginProc","/save","/saveProc","/login?error", "/css/**", "/js/**","/list","/board/**","/board/list","/boardlist","/boardmodify","/board/modify/**",
                                "/board/view","/message","/boardview","/board/file/**","/board/update","/board/writepro","/boardwrite").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
    //                    .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/", true) // 로그인 성공 시 리다이렉트할 URL 설정
                        .failureUrl("/login?error")
                        .permitAll()
                );
        http
                .csrf((AbstractHttpConfigurer::disable));


        http
                .sessionManagement((auth) -> auth //다중 접속 세션
                        .maximumSessions(100)
                        .maxSessionsPreventsLogin(false));

        http
                .sessionManagement((auth) -> auth  // 해킹보안
                        .sessionFixation().changeSessionId());
        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .permitAll()
                );
        http
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }

    //@Bean
   // public PasswordEncoder passwordEncoder() {
    //    return NoOpPasswordEncoder.getInstance();
  //  }
}

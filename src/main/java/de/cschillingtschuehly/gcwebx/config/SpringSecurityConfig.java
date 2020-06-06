package de.cschillingtschuehly.gcwebx.config;

import de.cschillingtschuehly.gcwebx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserService.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());;
        //jdbcAuthentication()
                //.dataSource(dataSource);
                //.withDefaultSchema()
                //.withUser(User.withUsername("admin")
                       // .password(passwordEncoder().encode("admin"))
                        //.roles("USER","ADMIN"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http.authorizeRequests()
                .antMatchers("/h2-console/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                //and().formLogin().loginPage("/login").permitAll()
                .and().httpBasic()
                .and().logout().permitAll();
        http.csrf().ignoringAntMatchers("/h2-console/**").and().headers().frameOptions().sameOrigin();
*/
        http
                .cors().
                and().
                headers().frameOptions().disable().and().
                httpBasic()

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user").permitAll()
                .antMatchers( "/login").permitAll()
                .antMatchers( "/h2-console/**").permitAll()
                //.antMatchers( "/getMembers").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")//hasAnyRole("ADMIN", "USER")
                .antMatchers( "/getMembers").hasRole("ADMIN")
                .antMatchers( "/admin").hasRole("ADMIN")
                //.anyRequest().authenticated()
                .and()
                .formLogin()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type","x-requested-with", "x-auth-token", "Cache-Control", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

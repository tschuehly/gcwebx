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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

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
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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
        final CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");

        http
                .csrf()
                .disable() //TODO: für h2 console
                //.csrfTokenRepository(tokenRepository).and()
                .cors().
                and().
                headers().frameOptions().disable().and().
                httpBasic()
                //.and().requiresChannel().anyRequest().requiresSecure() TODO: required if run inside docker?
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/login","/api/getRole/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/content","/api/getTeams","/api/team","/api/match","/api/member/getStreamer").permitAll()
                .antMatchers( "/login","/h2-console/**").permitAll()
                .antMatchers( "/api/getMembers",
                        "/api/updateMember",
                        "/api/createMember",
                        "/api/deleteMember"
                       ).hasAnyRole("ADMIN", "MODERATOR","SUPPORT")
                .antMatchers(HttpMethod.DELETE, "/api/match").hasAnyRole("ADMIN", "MODERATOR","SUPPORT")
                .antMatchers( "/api/user").hasAnyRole("ADMIN")
                .antMatchers( "/api/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
 }

}

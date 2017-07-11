package pl.training.cloud.users.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.sql.DataSource;

import static pl.training.cloud.common.controller.Controller.ALL_URIS;
import static pl.training.cloud.common.controller.Controller.GLOBAL_PUBLIC_URIS;

@EnableAuthorizationServer
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private static final String USERS_URI = "/users";
    private static final String SELECT_USER_SQL = "select login,password,active from users where login = ?";
    private static final String SELECT_AUTHORITY_SQL = "select login,authority from authorities where login = ?";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, USERS_URI).permitAll()
                .antMatchers(GLOBAL_PUBLIC_URIS).permitAll()
                .antMatchers(ALL_URIS).hasRole(Role.ADMIN);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SELECT_USER_SQL)
                .authoritiesByUsernameQuery(SELECT_AUTHORITY_SQL)
                .passwordEncoder(passwordEncoder);
    }

}

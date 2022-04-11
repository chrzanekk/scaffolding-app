package pl.com.chrzanowski.scaffolding.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.com.chrzanowski.scaffolding.auth.ShaPasswordEncoder;
import pl.com.chrzanowski.scaffolding.logic.user.UserAuthority;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder(256);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login, password_hash, is_enabled from users where login = ?;")
                .authoritiesByUsernameQuery("select (select login from users where id = user_id) as username, " +
                        "authority from user_authorities where user_id = (select id from users where " +
                        "login = ?)");
    }


    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

//                .antMatchers("/admin/**").hasAnyRole(UserAuthority.ADMIN.getCode())
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/actuator/**").hasRole(UserAuthority.ADMIN.getCode())

//                .antMatchers("/admin/**").hasAnyRole(UserAuthority.USER.getCode())
                .antMatchers("/my-account/**").hasRole(UserAuthority.USER.getCode())
                .antMatchers("/my-courses").hasRole(UserAuthority.USER.getCode())
                .antMatchers("/payments").hasRole(UserAuthority.USER.getCode())
                .antMatchers("/watch/**").hasRole(UserAuthority.USER.getCode())

                .antMatchers("/api/adviser/**").authenticated()
                .antMatchers("/api/crs/change-email").authenticated()
                .antMatchers("/api/crs/change-password").authenticated()
                .antMatchers("/api/crs/courses-bought-by-logged-customer").authenticated()

                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().logoutUrl("/perform/logout").logoutSuccessUrl("/login?logout").permitAll().deleteCookies("JSESSIONID").invalidateHttpSession(false)
                .and()
                .cors()
                .and()
                .csrf().disable();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

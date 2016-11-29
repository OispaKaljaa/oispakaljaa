package fi.oispakaljaa.karhu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

/*
    @Autowired
    private UserDetailsService userDetailsService;
*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // poistetaan csrf-tarkistus käytöstä h2-konsolin vuoksi
        http.csrf().disable();
        // sallitaan framejen käyttö
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .anyRequest().permitAll();

                http.formLogin().permitAll();
    }

/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

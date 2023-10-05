package security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/login")).permitAll()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/**")).hasAnyRole("BASICO")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/hola")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable();
    }
}
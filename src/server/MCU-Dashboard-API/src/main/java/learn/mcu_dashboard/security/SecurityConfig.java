package learn.mcu_dashboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/create_account").permitAll()
                // Movie
                .antMatchers(HttpMethod.GET,
                        "/api/movie", "/api/movie/*").permitAll()
//                .antMatchers(HttpMethod.POST,
//                        "/api/movie").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/api/movie").permitAll()
                .antMatchers(HttpMethod.DELETE,
                        "/api/movie/*").permitAll()
                // Movie_Person
                .antMatchers(HttpMethod.GET,
                        "/api/movie_person/*").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/movie_person").permitAll()
                .antMatchers(HttpMethod.DELETE,
                        "/api/movie_person/*").permitAll()
                // Person
                .antMatchers(HttpMethod.GET,
                        "/api/person/*").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/person").permitAll()
                .antMatchers(HttpMethod.DELETE,
                        "/api/person/*").permitAll()
                .antMatchers("/api/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Autowired
//    private PasswordEncoder encoder;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        var userBuilder = User.withUsername("user")
//                .password("user").passwordEncoder(password -> encoder.encode(password))
//                .roles("USER");
//
//        var adminBuilder = User.withUsername("admin")
//                .password("admin").passwordEncoder(password -> encoder.encode(password))
//                .roles("ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder)
//                .withUser(adminBuilder);
//    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowedMethods("*");
//            }
//        };
//    }

    //Unfortunately, configuring Spring Security to support CORS will cause your @SpringBootTest test classes
    // to throw runtime exceptions if you're configuring the web environment to "NONE" like this:
//    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//    class MyServiceTest {
//    }
}


package org.serratec.ecommerce.config;

//import java.io.Console;
//import java.util.Scanner;
import java.util.logging.Logger;

//import org.apache.tomcat.jni.User;
import org.serratec.ecommerce.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private static Logger log = Logger.getLogger("InfoLogging");
    
    @Autowired
    CustomUserDetailService customizacao;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customizacao).passwordEncoder(passwordEncoder());
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Note: 
        // Use this to enable the Tomcat basic authentication (Tomcat popup rather than spring login page)
        // Note that the CSRf token is disabled for all requests
        log.info("Disabling CSRF, enabling basic authentication...");
        http.httpBasic()
        	.and()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.POST, "/categorias").authenticated()
        	.antMatchers(HttpMethod.POST, "/cliente").authenticated()
        	.antMatchers(HttpMethod.POST, "/endereco").authenticated()
        	.antMatchers(HttpMethod.POST, "/itemPedido").authenticated()
        	.antMatchers(HttpMethod.POST, "/pedido").authenticated()
        	.antMatchers(HttpMethod.POST, "/produto").authenticated()
        	
        	
        	.antMatchers(HttpMethod.PUT, "/cliente").authenticated()
        	.antMatchers(HttpMethod.PUT, "/endereco").authenticated()
        	.antMatchers(HttpMethod.PUT, "/itemPedido").authenticated()
        	.antMatchers(HttpMethod.PUT, "/pedido").authenticated()
        	.antMatchers(HttpMethod.PUT, "/produto").authenticated()
        	
        	
        	.antMatchers(HttpMethod.DELETE, "/categorias").authenticated()
        	.antMatchers(HttpMethod.DELETE, "/cliente").authenticated()
        	.antMatchers(HttpMethod.DELETE, "/endereco").authenticated()
        	.antMatchers(HttpMethod.DELETE, "/itemPedido").authenticated()
        	.antMatchers(HttpMethod.DELETE, "/pedido").authenticated()
        	.antMatchers(HttpMethod.DELETE, "/produto").authenticated()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            
         http.headers().frameOptions().disable();
    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        log.info("Setting in-memory security using the user input...");
//
//        String username = "null";
//        String password = "null";
//
//        System.out.println("\nDefina as credenciais de administrador para este aplicativo da web (será necessário ao navegar para o aplicativo da web) ");
//        Console console = System.console();
//
//        // Read the credentials from the user console: 
//        // Note: 
//        // Console supports password masking, but is not supported in IDEs such as Eclipse; 
//        // thus if in IDE (where console == null) use scanner instead:
//        if (console == null) {
//            // Use scanner:
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.print("Username: ");
//                username = scanner.nextLine();
//                System.out.print("Password: ");
//                password = scanner.nextLine();
//                System.out.print("Confirm Password: ");
//                String inputPasswordConfirm = scanner.nextLine();
//
//                if (username.isEmpty()) {
//                    System.out.println("Error: user must be set - please try again");
//                } else if (password.isEmpty()) {
//                    System.out.println("Error: password must be set - please try again");
//                } else if (!password.equals(inputPasswordConfirm)) {
//                    System.out.println("Error: password and password confirm do not match - please try again");
//                } else {
//                    log.info("Setting the in-memory security using the provided credentials...");
//                    break;
//                }
//                System.out.println("");
//            }
//            scanner.close();
//        } else {
//            // Use Console
//            while (true) {
//                username = console.readLine("Username: ");
//                char[] passwordChars = console.readPassword("Password: ");
//                password = String.valueOf(passwordChars);
//                char[] passwordConfirmChars = console.readPassword("Confirm Password: ");
//                String passwordConfirm = String.valueOf(passwordConfirmChars);
//
//                if (username.isEmpty()) {
//                    System.out.println("Error: Username must be set - please try again");
//                } else if (password.isEmpty()) {
//                    System.out.println("Error: Password must be set - please try again");
//                } else if (!password.equals(passwordConfirm)) {
//                    System.out.println("Error: Password and Password Confirm do not match - please try again");
//                } else {
//                    log.info("Setting the in-memory security using the provided credentials...");
//                    break;
//                }
//                System.out.println("");
//            }
//        }
//
//        // Set the inMemoryAuthentication object with the given credentials:
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        if (username != null && password != null) {
//            String encodedPassword = passwordEncoder().encode(password);
//            manager.createUser(User.username(username).password(encodedPassword).roles("USER").build());
//        }
//        return manager;
//    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

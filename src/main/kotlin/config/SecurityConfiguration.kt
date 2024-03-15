package config


import jwt.AuthEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import org.springframework.http.HttpMethod.*

@EnableWebSecurity
@Configuration
@Component
class WebSecurityConfig {

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.getAuthenticationManager()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    class WebSecurityConfig {
        @Bean
        fun provideAuthEntryPoint(): AuthenticationEntryPoint = AuthEntryPoint()


        @Bean
        fun getEncoder(): PasswordEncoder {
            return BCryptPasswordEncoder()
        }

        @Bean
        fun userDetailsService(): UserDetailsService {
            println("TestUser")
            val user: UserDetails = User.builder()
                .username("user").password(getEncoder().encode("password"))
                .roles("USER").build()
            val admin: UserDetails = User.builder()
                .username("admin").password(getEncoder().encode("1357"))
                .roles("ADMIN").build()
            return InMemoryUserDetailsManager(user, admin)
        }

        @Bean
        fun configure(http: HttpSecurity):SecurityFilterChain{
            http.authorizeHttpRequests {requests ->
                requests.requestMatchers("/admin/**").hasRole("ADMIN")
                requests.anyRequest().authenticated()
            }
            http.httpBasic(Customizer.withDefaults())
            return http.build()
        }
    }
}
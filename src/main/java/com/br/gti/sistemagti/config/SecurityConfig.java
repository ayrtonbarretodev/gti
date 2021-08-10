package com.br.gti.sistemagti.config;

import com.br.gti.sistemagti.domain.PerfilTipo;
import com.br.gti.sistemagti.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
    private static final String ESTAGIARIO = PerfilTipo.ESTAGIARIO.getDesc();


    @Autowired
    private UsuarioService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //acessos públicos liberados
                .antMatchers("/webjars/**", "/css/**", "/js/**", "/image/**").permitAll()
                .antMatchers("/u/e/**").permitAll()
                .antMatchers("/expired").permitAll()

                //acessos privados admin
                .antMatchers("/u/editar/senha", "/u/confirmar/senha").hasAuthority(ESTAGIARIO)
                .antMatchers("/u/**").hasAuthority(ADMIN)
                .antMatchers("/departamentos/**", "/categorias/**").hasAuthority(ADMIN)


                //acessos privados estagiarios
                .antMatchers("/estagiarios/dados", "/estagiarios/salvar", "/estagiarios/editar").hasAnyAuthority(ESTAGIARIO,ADMIN)
                .antMatchers("/estagiarios/**").hasAuthority(ESTAGIARIO)

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login-error")
                .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/acesso-negado")
                .and()
                    .rememberMe();

        http.sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/expired")
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry());

        http.sessionManagement()
                .sessionFixation().newSession()
                .sessionAuthenticationStrategy(sesseionAuthStrategy());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SessionAuthenticationStrategy sesseionAuthStrategy(){
        return  new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
}

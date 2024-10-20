package com.retailhome.configurations;

import com.retailhome.dtos.ClientDTO;
import com.retailhome.mappers.ClientMapper;
import com.retailhome.repositories.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {


    ClientRepository clientRepository;
    ClientMapper clientMapper;


    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            ClientDTO clientDto = this.clientMapper.clientToClientDto(this.clientRepository.findByEmail(inputName));

            if (clientDto != null) {
                return new User(clientDto, clientDto(),
                        AuthorityUtils.createAuthorityList("CLIENT"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }

        });

    }

}

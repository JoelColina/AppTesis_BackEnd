package com.retailhome.config;

//import com.newton.security.data.base.mapper.UserMapper;

//import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@EnableWebMvc
public class GeneralConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error_403").setViewName("error_403");
    }

 /*   @Bean
     public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }*/
//
//    @Bean
//    public ModelMapper modelMapper(){
//        return new ModelMapper();
//    }
//
//
//
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(new Locale("es", "ES"));
//        return localeResolver;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
//        localeInterceptor.setParamName("lang");
//        return localeInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }


//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//                .allowedOrigins("http://localhost:4200")
//                .allowedMethods("*")
//                .maxAge(3600L)
//                .allowedHeaders("*")
//                .exposedHeaders("Authorization")
//                .allowCredentials(true);
//    }



}

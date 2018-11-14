package com.excilys.cdb.configspring;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc

@ComponentScan({"com.excilys.cdb.service","com.excilys.cdb.mapper","com.excilys.cdb.persistence","com.excilys.cdb.controller","com.excilys.cdb.configspring"})
public class WebConfig implements WebMvcConfigurer {


	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/lib/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/lib/css/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/lib/fonts/");
	}

	@Bean(name="messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		messageSource.setDefaultEncoding("UTF-8");
		//messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}


	 @Bean
	    public LocaleChangeInterceptor localeInterceptor(){
	        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	        interceptor.setParamName("lang");
	        return interceptor;
	    }
	    
	    @Bean
	    public CookieLocaleResolver localeResolver(){
	    	CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	        localeResolver.setDefaultLocale(Locale.ENGLISH);
	        localeResolver.setCookieName("language-preference");
	        localeResolver.setCookieMaxAge(3600);
	        return localeResolver;
	    } 
	    
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	    	registry.addInterceptor(localeInterceptor());
	    }

}

package com.jdc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("com.jdc.controller")
@EnableWebMvc
public class ServletConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "home");
		
	}
	
	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		var resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".html");
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine springTemplateEngine(SpringResourceTemplateResolver springResourceTemplateResolver) {
		var templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(springResourceTemplateResolver);
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine springTemplateEngine) {
		var thymeleaf =  new ThymeleafViewResolver();
		thymeleaf.setTemplateEngine(springTemplateEngine);
		return thymeleaf;
	}
	
}

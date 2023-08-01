package com.akshithaSecondProject.restfulwebservices.hello;


import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerJava {
	
	private MessageSource messageSource;
	
	public HelloControllerJava(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
		@GetMapping(path = "/hello-world")
		public String sample() {
			return "Akshitha";	
		}
		
		@GetMapping(path = "/hello-world-Bean")
		public HelloWorldBean helloWorldBean() {
			return new HelloWorldBean("Akshitha");
		}
		
		@GetMapping(path = "/pathvariable/hello-world-Bean/{name}")
		public HelloWorldBean helloWorldBean(@PathVariable String name) {
			return new HelloWorldBean("Hey"+name);
		}
		
		@GetMapping(path = "/hello-world-internationalized")
		public String helloWorldInternationalization() {
			Locale locale = LocaleContextHolder.getLocale();
		return	messageSource.getMessage("good.morning.messages", null, "Default Message", locale);
//			return "Hello Akshitha";
			
			
			
		}
}

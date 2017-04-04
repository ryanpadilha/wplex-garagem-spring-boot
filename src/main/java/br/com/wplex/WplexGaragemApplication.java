package br.com.wplex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * The main class that contains the entry-point of the web application.<br>
 * This launches an embedded web server.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@SpringBootApplication
public class WplexGaragemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WplexGaragemApplication.class, args);
	}

	/*
	 * 3. override the method configure to deploy a war file
	 * 
	 * The SpringBootServeletInitializer taps into a Servlet 3 <br>
	 * Programmatically defines a description of web.xml file
	 */

	private static Class<WplexGaragemApplication> applicationClass = WplexGaragemApplication.class;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(applicationClass);
	}
}

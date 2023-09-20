package clienteapp.springbootclienteapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "clienteapp.springbootclienteapp")
public class SpringbootClienteappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootClienteappApplication.class, args);
	}
}

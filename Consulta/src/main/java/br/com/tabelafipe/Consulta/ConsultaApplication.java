package br.com.tabelafipe.Consulta;

import br.com.tabelafipe.Consulta.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaApplication.class, args);
	}

	@Override
	public void run(String... args){
		Main main = new Main();

		main.exibeDados();
	}
}

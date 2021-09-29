package igorgroup.desafiopandemia;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import igorgroup.desafiopandemia.controller.EtapaController;
import igorgroup.desafiopandemia.controller.UnidadeSaudeController;
import igorgroup.desafiopandemia.controller.DTO.EtapaDTO;
import igorgroup.desafiopandemia.controller.DTO.UnidadeSaudeDTO;
import igorgroup.desafiopandemia.model.UnidadeSaude;

@SpringBootApplication
public class DesafiopandemiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiopandemiaApplication.class, args);
	}

}

package br.com.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domain.model.Tabela;
import br.com.domain.service.TabelaService;

@RestController
@RequestMapping("/tabela")
public class TabelaController {

	@Autowired
	private TabelaService tabelaService;

	@PostMapping("/criar/alunos")
	public String criarCodigoSQLParaCriarTabela(@RequestBody Tabela tabela) {
		return tabelaService.gerarCodigoSQLParaCriarTabela("alunos", tabela.getAtributos());
	}
}

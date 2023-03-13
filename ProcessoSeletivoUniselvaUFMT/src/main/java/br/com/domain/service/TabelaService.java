package br.com.domain.service;

import java.util.List;

import br.com.domain.model.Atributo;

public interface TabelaService {
	public String gerarCodigoSQLParaCriarTabela(String nomeTabela, List<Atributo> atributos);
}

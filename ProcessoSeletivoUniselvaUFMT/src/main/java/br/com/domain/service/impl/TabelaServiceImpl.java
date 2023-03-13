package br.com.domain.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.domain.model.Atributo;
import br.com.domain.service.TabelaService;

@Service
public class TabelaServiceImpl implements TabelaService {

	// Retorna o código SQL para a criação de tabela
	@Override
	public String gerarCodigoSQLParaCriarTabela(String nomeTabela, List<Atributo> atributos) {
		if (atributoListIsEmptyOrNull(atributos))
			return "O codigo nao pode ser gerado porque a lista de atributos está vazia";
		Iterator<Atributo> verificadorAtributos = atributos.iterator();
		Atributo atributo = null;
		String codigoSQL = "CREATE TABLE " + nomeTabela + " (";
		while (verificadorAtributos.hasNext()) {
			// Se nao for o primeiro atributo a ser adicionado, adiciona virgula ao código
			if (atributo != null)
				codigoSQL += ", ";
			atributo = verificadorAtributos.next();
			if (!atributoIsValid(atributo))
				return "O código não pode ser gerado pois há atributos inválidos";
			codigoSQL = adicionarAtributoCodigoSQL(codigoSQL, atributo);
		}
		codigoSQL += ")";
		return codigoSQL;
	}

	// Retorna codigo SQL concatenado com atributo
	public String adicionarAtributoCodigoSQL(String codigoSQL, Atributo atributo) {
		codigoSQL = adicionaNomeAtributoCodigoSQL(codigoSQL, atributo);
		codigoSQL = adicionaTipoAtributoCodigoSQL(codigoSQL, atributo);
		codigoSQL = adicionaOpcionalCodigoSQL(codigoSQL, atributo);
		return codigoSQL;
	}

	// Retorna o código SQL com o nome do atributo concatenado
	public String adicionaNomeAtributoCodigoSQL(String codigoSQL, Atributo atributo) {
		return codigoSQL += atributo.getNome();
	}

	// Retorna o código SQL com o tipo do atributo concatenado
	public String adicionaTipoAtributoCodigoSQL(String codigoSQL, Atributo atributo) {
		if (atributo.getTipo().equals("string")) {
			return codigoSQL += " varchar(200)";
		}
		return codigoSQL += " " + atributo.getTipo();
	}

	// Se atributo for obrigatório, retorna código SQL concatenado com NOT NULL
	// Se não, retorna o próprio código SQL que foi passado como parâmetro
	public String adicionaOpcionalCodigoSQL(String codigoSQL, Atributo atributo) {
		if (!atributo.isOpcional())
			return codigoSQL += " NOT NULL";
		return codigoSQL;
	}

	// Retorna boolean informando se o atributo é válido
	public boolean atributoIsValid(Atributo atributo) {
		if (atributo.getNome() == null || atributo.getTipo() == null || !isTipo(atributo.getTipo()))
			return false;
		return true;
	}

	// Retorna boolean informando se o tipo de atributo é válido
	public boolean isTipo(String tipo) {
		if (tipo.equalsIgnoreCase("date") || tipo.equalsIgnoreCase("string") || tipo.equalsIgnoreCase("integer")
				|| tipo.equalsIgnoreCase("decimal") || tipo.equalsIgnoreCase("boolean"))
			return true;
		return false;
	}

	// Retorna boolean se lista é vazia ou nulo
	public boolean atributoListIsEmptyOrNull(List<Atributo> atributos) {
		return (atributos.isEmpty() || atributos == null);
	}
}

package com.exemplo.produto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("produto")
public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer codProduto;
	private String nome;
	private String descricao;

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "ProdutoDTO [codProduto=" + codProduto + ", nome=" + nome + ", descricao=" + descricao
				+ "]";
	}

}

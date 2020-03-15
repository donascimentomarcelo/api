package br.com.api.dto;

import java.io.Serializable;

public class QuantityDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer qtd;
	
	public QuantityDto(Integer qtd) {
		this.qtd = qtd;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	
}

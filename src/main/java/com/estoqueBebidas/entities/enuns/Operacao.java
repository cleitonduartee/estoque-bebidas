package com.estoqueBebidas.entities.enuns;

public enum Operacao {

	CADASTRO(1),
	COMPRA(2),
	VENDA(3);
	
	private int cod;
	
	private Operacao(int cod) {
		this.setCod(cod);
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	public static Operacao convertOperacao(int cod) {
		for(Operacao x : Operacao.values()) {
			if(x.getCod() == cod) {
				return x;
			}
		}
		throw new IllegalArgumentException("Operacão não encontrada. Código informado: "+cod);
	}
}

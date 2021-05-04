package com.estoqueBebidas.entities.enuns;

public enum Categoria {

	ALCOOLICA(1,500),
	NAOALCOOLICA(2,400);
	
	private int cod;
	private double limit;
	
	private Categoria(int cod, double limit) {
		this.cod = cod;
		this.limit = limit;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}
	public static Categoria convertCategoria(int cod) {
		for (Categoria x : Categoria.values()) {
			if(x.getCod() == cod) {
				return x;
			}
		}
		throw new IllegalArgumentException("Categoria não encontrada. Código informado: "+cod);
	}
	public static String tiposDeCategorias() {
		String buffer = "";
		for (Categoria x : Categoria.values()) {
			buffer += x+" ";
		}
		return buffer;
	}
}

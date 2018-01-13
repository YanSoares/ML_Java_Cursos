package br.com.yrsoares.modelos;

public class Vizinhos {

	public int Id;
	public double coeficiente;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public double getCoeficientePearson() {
		return coeficiente;
	}

	public void setCoeficientePearson(double coeficientePearson) {
		this.coeficiente = coeficientePearson;
	}

	@Override
	public String toString() {
		return "Vizinhos [Id=" + Id + ", coeficiente=" + coeficiente + "]";
	}

}

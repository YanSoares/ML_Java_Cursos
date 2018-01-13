package br.com.yrsoares.comparadores;

import java.util.Comparator;

import br.com.yrsoares.modelos.Vizinhos;

public class ComparadorDeVizinhos implements Comparator<Vizinhos> {
	public int compare(Vizinhos o1, Vizinhos o2) {
		if (o1.coeficiente > o2.coeficiente)
			return -1;
		else if (o1.coeficiente < o2.coeficiente)
			return +1;
		else
			return 0;
	}
}

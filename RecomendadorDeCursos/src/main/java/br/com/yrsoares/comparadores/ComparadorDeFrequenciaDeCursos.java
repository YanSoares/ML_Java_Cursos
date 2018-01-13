package br.com.yrsoares.comparadores;

import java.util.Comparator;

import br.com.yrsoares.FiltragemCursos;

public class ComparadorDeFrequenciaDeCursos implements Comparator<FiltragemCursos> {
	public int compare(FiltragemCursos o1, FiltragemCursos o2) {
		if (o1.count > o2.count)
			return -1;
		else if (o1.count < o2.count)
			return +1;
		else
			return 0;
	}
}

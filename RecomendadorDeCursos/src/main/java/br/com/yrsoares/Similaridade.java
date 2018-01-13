package br.com.yrsoares;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import br.com.yrsoares.comparadores.ComparadorDeVizinhos;
import br.com.yrsoares.modelos.Vizinhos;

public class Similaridade {
	public ArrayList<Integer> calculaSimilaridade(int usuario, DataModel model, DataModel modelsimilaridade,
			int qtdVizinhos, int tecnica, int tipoGrafico) throws TasteException {

		int idiniciocursos = 9;
		int idfimcursos = 14;

		ArrayList<Vizinhos> vizinhos = new ArrayList<Vizinhos>();
		UserSimilarity similarity = null;

		if (tecnica == 1) {
			similarity = new PearsonCorrelationSimilarity(modelsimilaridade);
		}
		if (tecnica == 2) {
			similarity = new SpearmanCorrelationSimilarity(modelsimilaridade);
		}

		if (tecnica == 3) {
			similarity = new TanimotoCoefficientSimilarity(modelsimilaridade);
		}
		if (tecnica == 4) {
			similarity = new EuclideanDistanceSimilarity(modelsimilaridade);
		}
		if (tecnica == 5) {
			similarity = new CityBlockSimilarity(modelsimilaridade);
		}

		// calcular similaridade entre o usuario x e os usuarios restantes e
		// adiciona na lista Vizinhos
		for (int i = 1; i <= modelsimilaridade.getNumUsers(); i++) {
			Vizinhos v = new Vizinhos();
			v.setId(i);
			v.setCoeficientePearson(similarity.userSimilarity(usuario, i));
			vizinhos.add(v);
		}
		// ordena a lista
		Collections.sort(vizinhos, new ComparadorDeVizinhos());

		// remove o primeiro (pois é o mesmo usuario)
		vizinhos.remove(0);

		System.out.println("usuario: " + usuario);
		System.out.println("tecnica: " + tecnica);
		/*
		 * System.out.println(vizinhos.get(0).toString());
		 * System.out.println(vizinhos.get(1).toString());
		 * System.out.println(vizinhos.get(2).toString());
		 * System.out.println(vizinhos.get(3).toString());
		 * System.out.println(vizinhos.get(4).toString());
		 */

		for (int i = 0; i < vizinhos.size(); i++) {
			System.out.println(vizinhos.get(i).toString());
		}

		// pegando os "qtdVizinhos" primeiros similares e passando pra outra
		// lista
		List<Vizinhos> vizinhosMaisSimilares = vizinhos.subList(0, qtdVizinhos);

		// pegando os cursos dos vizinhos mais proximos
		ArrayList<Integer> cursosVizinhos = new ArrayList<Integer>();

		if (tipoGrafico == 4) {
			for (int j = idiniciocursos; j < idfimcursos; j++) {
				if (model.getPreferenceValue(vizinhosMaisSimilares.get(0).getId(), j) != null) {
					Float auxfloat = new Float(model.getPreferenceValue(vizinhosMaisSimilares.get(0).getId(), j));
					Integer auxint = new Integer(auxfloat.intValue());
					cursosVizinhos.add(auxint);
				}
			}
		} else {

			for (int j = idiniciocursos; j < idfimcursos; j++) {
				for (int i = 0; i < vizinhosMaisSimilares.size(); i++) {
					if (model.getPreferenceValue(vizinhosMaisSimilares.get(i).getId(), j) != null) {
						Float auxfloat = new Float(model.getPreferenceValue(vizinhosMaisSimilares.get(i).getId(), j));
						Integer auxint = new Integer(auxfloat.intValue());
						cursosVizinhos.add(auxint);
					}
				}
			}
		}

		/*
		 * for (int j = idiniciocursos; j < idfimcursos; j++) { if
		 * (model.getPreferenceValue(vizinhos.get(0).getId(), j) != null) { Float
		 * auxfloat = new Float(model.getPreferenceValue(vizinhos.get(0).getId(), j));
		 * Integer auxint = new Integer(auxfloat.intValue());
		 * System.out.println(auxint); cursosVizinhos.add(auxint); } } if
		 * (cursosVizinhos.size() != 5) { for (int j = idiniciocursos; j < idfimcursos;
		 * j++) { if (model.getPreferenceValue(vizinhos.get(1).getId(), j) != null &&
		 * cursosVizinhos.size() != 5) { Float auxfloat = new
		 * Float(model.getPreferenceValue(vizinhos.get(0).getId(), j)); Integer auxint =
		 * new Integer(auxfloat.intValue()); System.out.println(auxint);
		 * cursosVizinhos.add(auxint); } } }
		 */
		return cursosVizinhos;
	}

}

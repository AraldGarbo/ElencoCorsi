package it.polito.tdp.corsi.model;

import java.util.*;

public class TestModel {

	public static void main(String[] args) {

		Model m = new Model();
		List<Corso> corsi = m.listaCorsiSemestre(2);
		for(Corso c : corsi) {
			System.out.println(c);
		}
		
		int matricola = 146101;
		String result = m.getNomeCognomeByMatricola(matricola);
		System.out.println("Matricola: "+matricola+" Cognome Nome: "+result);
		
		
		result = m.getStatisticheFromCorsi();
		System.out.print(result);
	}

}

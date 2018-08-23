package it.polito.tdp.corsi.model;

import java.util.*;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {

	private List<Corso> corsi;
	private CorsoDAO corsoDAO; 
	private StudenteDAO studenteDAO;
	
	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> listaCorsiSemestre(int pd){
		
		//opzione1: leggo tutto e filtro io
		/*this.corsi = corsoDAO.listAll();
		
		List<Corso> risultato = new ArrayList<>();
		for(Corso c : this.corsi) {
			if(c.getPd() == pd) risultato.add(c);
		}
		return risultato;*/
		
		//opzione2: filtro direttamente dal database e non con java
		List<Corso> risultato2 = corsoDAO.listByPD(pd);
		return risultato2;
	}

	public String getNomeCognomeByMatricola(int matricola) {
		Studente studente = StudenteDAO.getStudenteByMatricola(matricola);
		if(studente == null) return "Matricola Inesistente!";
		return studente.getCognome()+" "+studente.getNome();
	}

	public String getStatisticheFromCorsi() {

		StringBuilder sb = new StringBuilder();
		this.corsi = corsoDAO.listAll();
		for(Corso c: this.corsi) {
			Statistiche stat = corsoDAO.getStatisticheByCodins(c.getCodIns());
			sb.append("codins: "+ c.getCodIns() +"\n");
			for(String cds: stat.getMappaCDS().keySet()) {
				sb.append(" - "+ cds + " " + stat.getMappaCDS().get(cds) + "\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}

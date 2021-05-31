package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model
{
	private PremierLeagueDAO dao;
	private Map<Integer, Match> matches; 
	private Graph<Match, DefaultWeightedEdge> grafo;

	public Model()
	{
		dao = new PremierLeagueDAO();
	}

	public String creaGrafo(int min, int mese)
	{
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.matches = new HashMap<>();
		
		// vertici
		this.dao.getVertici(this.matches, mese);
		Graphs.addAllVertices(this.grafo, this.matches.values());
		
		// archi 
		List<Adiacenza> adj = new ArrayList<>(this.dao.getAdiacenze(min, mese));
		for (Adiacenza a : adj)
		{
			Match m1 = this.matches.get(a.getM1());
			Match m2 = this.matches.get(a.getM2());
			if (m1 != null && m2 != null)
				Graphs.addEdgeWithVertices(this.grafo, m1, m2, a.getPeso());
		}
		
		return "\n#Vertici: " + this.grafo.vertexSet().size() + "\n#Archi: " + this.grafo.edgeSet().size();
	}
	
	public String getConMax()
	{
		DefaultWeightedEdge bestE = null; 
		Double bestPeso = 0.0; 
		for(DefaultWeightedEdge e : this.grafo.edgeSet())
		{
			Double peso = this.grafo.getEdgeWeight(e); 
			if(peso > bestPeso)
			{
				bestPeso = peso; 
				bestE = e; 
			}
		}
		List<DefaultWeightedEdge> lista = new ArrayList<>(); 
		lista.add(bestE);
		for (DefaultWeightedEdge e : this.grafo.edgeSet())
		{
			if(this.grafo.getEdgeWeight(e) == bestPeso)
				if(!lista.contains(e))
					lista.add(e); 
		}
		String s = "\n\n"; 
		for (DefaultWeightedEdge e : lista)
		{
			Match m1 = this.grafo.getEdgeSource(e);
			Match m2 = this.grafo.getEdgeTarget(e);
			s += "" + m1.toString() + " - ";
			s += "" + m2.toString() + " (";
			s += "" + this.grafo.getEdgeWeight(e) + ")\n"; 
		}
		return s; 
	}
}
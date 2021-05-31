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
		
		return "\nVertici: " + this.grafo.vertexSet().size() + "\nArchi: " + this.grafo.edgeSet().size();
	}
}
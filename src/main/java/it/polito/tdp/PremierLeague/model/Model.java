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
	PremierLeagueDAO dao;
	Graph<Match, DefaultWeightedEdge> grafo;

	public Model()
	{
		dao = new PremierLeagueDAO();
	}

	public String creaGrafo(int min)
	{
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// vertici
		List<Match> vertici = this.dao.getVertici(min);
		Graphs.addAllVertices(this.grafo, vertici);
		
		
		return "\nVertici: " + this.grafo.vertexSet().size() + "\nArchi: " + this.grafo.edgeSet().size();
	}
}
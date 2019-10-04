package trab2;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice>{
    String nome;
    int distancia;
    Vertice anterior;
    List<String> historico;
    List<Aresta> adj;
    List<Vertice> verticesAdj;

    Vertice(String nome) {
        this.nome = nome;
        this.historico = new ArrayList<String>();
        this.distancia = 0;
        this.adj = new ArrayList<Aresta>();
        this.verticesAdj = new ArrayList<Vertice>();
    }

    void addAdj(Aresta e) {
        adj.add(e);
    }
    
    void addVerticeAdj(Vertice v) {
    	verticesAdj.add(v);
    }    

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	@Override
	public int compareTo(Vertice pVertice) {
		return (this.distancia - pVertice.getDistancia());
	}
}

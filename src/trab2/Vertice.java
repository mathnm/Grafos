package trab2;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    String nome;
    List<Aresta> adj;
    List<Vertice> verticesAdj;

    Vertice(String nome) {
        this.nome = nome;
        this.adj = new ArrayList<Aresta>();
        this.verticesAdj = new ArrayList<Vertice>();
    }

    void addAdj(Aresta e) {
        adj.add(e);
    }
    
    void addVerticeAdj(Vertice v) {
    	verticesAdj.add(v);
    }
}

package trab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {

	List<Vertice> vertices;
    List<Aresta> arestas;
    ArrayList<String> arestasString = new ArrayList<String>();
	public boolean orientado;
	public boolean valorado;
	
	//Dijkstra
	String djik = "DIJKSTRA:"+"\n";
	List<Vertice> naoVisitados = new ArrayList<Vertice>();
	Vertice atual = new Vertice(null);
	List<Vertice> menorCaminho = new ArrayList<Vertice>();
	
	
    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }
    
    Vertice adicionaVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }
    
    Aresta adicionaAresta(Vertice origem, Vertice destino, int valor) {
        Aresta e = new Aresta(origem, destino, valor);
        origem.addAdj(e);
        destino.addAdj(e);
        arestas.add(e);
        origem.addVerticeAdj(destino);
        if(!orientado) {
        	destino.addVerticeAdj(origem);
        }
        //origem.addVAdj(destino);
        if(valor!= 0) {
        	arestasString.add("["+e.origem.nome+","+e.destino.nome+","+valor+"]");
        } else {
        	arestasString.add("["+e.origem.nome+","+e.destino.nome+"]");
        }
        
        return e;
    }
    
    public String listaDeAresta() {
       String r = "LISTA DE ARESTAS"+"\n"+arestasString+"\n\n";
       System.out.println(r);
       return r;
    }
    
    public String listaDeAdj() {
    	String r = "LISTA DE ADJACÊNCIA"+"\n";
    	if(!orientado) {
    		for (Vertice u : vertices) {
                r += u.nome + " -> ";
                for (Aresta e : u.adj) {
                	if(u == e.destino) {
                		Vertice v = e.origem;
                		r += v.nome +", ";
                	} else {
                		Vertice v = e.destino;
                		r += v.nome + ", ";                         		
                   	}
                }
                r += "\n";
            }
    	} else {
    		for (Vertice u : vertices) {
                r += u.nome + " -> ";
                for (Aresta e : u.adj) {
                	if(u != e.destino) {
                		r += e.destino.nome + ", ";
                	}
                }
                r += "\n";
            }
    	}
    	
        
        r+="\n\n";
        System.out.println(r);
        return r;
    }
    
    public int[][] matrizDeAdj() {
    	System.out.println("MATRIZ DE ADJACÊNCIA"); 
		int mAdj[][] = new	int[vertices.size()][vertices.size()];
		
		for (int i = 0; i < mAdj.length; i++) { 
			for (int j = 0; j < mAdj.length; j++) {
				for (int k = 0; k < vertices.get(i).verticesAdj.size(); k++) {
					if(vertices.get(i).verticesAdj.get(k).equals(vertices.get(j))) {
						if(valorado) {
							for (int a = 0; a < vertices.get(i).adj.size(); a++) {
								if(!orientado) {
									if(vertices.get(i).adj.get(a).destino.equals(vertices.get(j)) || vertices.get(i).adj.get(a).origem.equals(vertices.get(j))){
										mAdj[i][j] = vertices.get(i).adj.get(a).valor;
									}
								}else {
									if(vertices.get(i).adj.get(a).destino.equals(vertices.get(j))){
										mAdj[i][j] = vertices.get(i).adj.get(a).valor;
									}	
								}								
							}
						} else {
							mAdj[i][j] = 1;
						}
					} 
				}
				System.out.print(mAdj[i][j] + "  ");
			}
			System.out.println(); 
		}
		System.out.println();
		System.out.println();
		
		return mAdj; 
    }
    
    public int[][] matrizDeIncid(){
    	System.out.println("MATRIZ DE INCIDÊNCIA"); 
		int mIncid[][] = new	int[vertices.size()][arestas.size()];
		     
		if(!orientado) {
			for (int i = 0; i < mIncid.length; i++) {
				for (int j = 0; j < arestas.size(); j++) {
					if(arestas.get(j).destino.equals(vertices.get(i)) || arestas.get(j).origem.equals(vertices.get(i))) {
						if(valorado) {
							mIncid[i][j] = arestas.get(j).valor;
						}else {
							if(arestas.get(j).destino.equals(arestas.get(j).origem)) {
								//HAVENDO LAÇO:
								mIncid[i][j] = 2;
							}else {
								mIncid[i][j] = 1;	
							}
						}						
					}
				System.out.print(mIncid[i][j] + "  ");
				}
				System.out.println();
			}	
		}else {
			for (int i = 0; i < mIncid.length; i++) {
				for (int j = 0; j < arestas.size(); j++) {
					if(arestas.get(j).origem.equals(vertices.get(i))){
						if(valorado) {
							mIncid[i][j] = arestas.get(j).valor;
						}else {
							if(arestas.get(j).origem.equals(arestas.get(j).destino)) {
								//HAVENDO LAÇO:
								mIncid[i][j] = 2;
							}else {
								mIncid[i][j] = 1;	
							}
						}		
					}
					if(arestas.get(j).destino.equals(vertices.get(i))) {
						if(valorado) {
							mIncid[i][j] = arestas.get(j).valor * (-1);
						}else {
							if(arestas.get(j).destino.equals(arestas.get(j).origem)) {
								//HAVENDO LAÇO:
								mIncid[i][j] = -2;
							}else {
								mIncid[i][j] = -1;	
							}
						}						
					}
				System.out.print(mIncid[i][j] + "  ");
				}
				System.out.println();
			}
		}
		
		return mIncid;
		
    }
    
    public static List<String> getShortestPathTo(Vertice target)
    {
        List<String> path = new ArrayList<String>();
        for (Vertice v = target; v != null; v = v.anterior)
            path.add(v.nome);

        Collections.reverse(path);
        return path;
    }
    
    public String dijkstra(Grafo g, Vertice s) {
		for (int i = 0; i < g.vertices.size(); i++) {
			if(g.vertices.get(i) == s) {
				g.vertices.get(i).distancia = 0;
				naoVisitados.add(g.vertices.get(i));
			}else {
				g.vertices.get(i).distancia = 9999;
				naoVisitados.add(g.vertices.get(i));
			}
		}
		
		while(!naoVisitados.isEmpty()) {
			atual = naoVisitados.get(0);
			naoVisitados.remove(0);
			
			for (int i = 0; i < atual.verticesAdj.size(); i++) {
				for (int j = 0; j < atual.adj.size(); j++) {
					if(!orientado) { //Se não for orientado, o vértice de origem do objeto aresta também é considerado adjacente
						if(atual.adj.get(j).destino.equals(atual.verticesAdj.get(i)) || atual.adj.get(j).origem.equals(atual.verticesAdj.get(i))) {
							if(atual.verticesAdj.get(i).distancia > atual.distancia + atual.adj.get(i).valor) {
								atual.verticesAdj.get(i).distancia = atual.distancia + atual.adj.get(i).valor;
								atual.verticesAdj.get(i).anterior = atual;
							}
						}						
					}else {
						if(atual.adj.get(j).destino.equals(atual.verticesAdj.get(i))) {
							if(atual.verticesAdj.get(i).distancia > atual.distancia + atual.adj.get(j).valor) {
								atual.verticesAdj.get(i).distancia = atual.distancia + atual.adj.get(j).valor;
								atual.verticesAdj.get(i).anterior = atual;
							}
						}
					}					
				}
			}		
			
			Collections.sort(naoVisitados);
		}	
		
		for (int i = 0; i < g.vertices.size(); i++) {
			if(!g.vertices.get(i).equals(s)) {
				List<String> path = getShortestPathTo(g.vertices.get(i));
				djik += "Distância da origem até vértice " + g.vertices.get(i).nome + ": " + g.vertices.get(i).distancia+"  --  Caminho mínimo: " + path+"\n";
			}
		}
		
		return djik;
		
    }
    
    public String toString() {
    	String r = "";
        
    	//LISTA DE ADJACÊNCIA
    	listaDeAdj();
    	
        //LISTA DE ARESTAS
        listaDeAresta();
        
		//MATRIZ DE ADJACÊNCIA
        matrizDeAdj();
        
		//MATRIZ DE INCIDÊNCIA
        matrizDeIncid();
        
        return r;
    }
    
}

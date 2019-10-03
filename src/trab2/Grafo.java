package trab2;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

	List<Vertice> vertices;
    List<Aresta> arestas;
    ArrayList<String> arestasString = new ArrayList<String>();
	public boolean orientado;
	public boolean valorado;
	
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

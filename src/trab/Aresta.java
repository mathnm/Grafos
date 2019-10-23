package trab;

public class Aresta implements Comparable<Aresta>{
    Vertice origem;
    Vertice destino;
    int valor;

    Aresta(Vertice origem, Vertice destino, int valor) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
    }

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(Aresta pAresta) {
		return (this.valor - pAresta.getValor());
	}
}

package trab2;

import javax.swing.JOptionPane;

public class Main {
	
	
	public static void main(String[] args) {
        Grafo g = new Grafo();
        
        if(JOptionPane.showInputDialog("Grafo orientado? (s/n)").equals("s")) {
			if(JOptionPane.showInputDialog("Grafo possui arestas valoradas? (s/n)").equals("s")){
				System.out.println("Grafo valorado e direcionado");
				
				g.orientado = true;
				g.valorado = true;
				
				int nrV = Integer.parseInt(JOptionPane.showInputDialog("Quantos v�rtices ter� o grafo?"));
				for (int i = 0; i < nrV; i++) {
					g.adicionaVertice(JOptionPane.showInputDialog("Insira o v�rtice (valor num�rico): "));
				}
				int nrE = Integer.parseInt(JOptionPane.showInputDialog("Quantas arestas ter� o grafo?"));
				for (int i = 0; i < nrE; i++) {
					g.adicionaAresta(g.vertices.get(Integer.parseInt(JOptionPane.showInputDialog("N�mero do v�rtice de origem:"))-1), g.vertices.get(Integer.parseInt(JOptionPane.showInputDialog("N�mero do v�rtice de destino:"))-1), Integer.parseInt(JOptionPane.showInputDialog("Valor da aresta")));
				}
				
				System.out.println(g);
				
			}else {
				System.out.println("Grafo n�o valorado e direcionado");
				
				g.orientado = true;
				
				int nrV = Integer.parseInt(JOptionPane.showInputDialog("Quantos v�rtices ter� o grafo?"));
				for (int i = 0; i < nrV; i++) {
					g.adicionaVertice(JOptionPane.showInputDialog("Insira o v�rtice (valor num�rico): "));
				}
				int nrE = Integer.parseInt(JOptionPane.showInputDialog("Quantas arestas ter� o grafo?"));
				for (int i = 0; i < nrE; i++) {
					g.adicionaAresta(g.vertices.get(Integer.parseInt(JOptionPane.showInputDialog("N�mero do v�rtice de origem:"))-1), g.vertices.get(Integer.parseInt(JOptionPane.showInputDialog("N�mero do v�rtice de destino:"))-1), 0);
				}
				
				System.out.println(g);
				
			}
		}else {
			if(JOptionPane.showInputDialog("Grafo possui arestas valoradas? (s/n)").equals("s")){
				System.out.println("Grafo valorado e n�o direcionado");
				
				g.valorado = true;
				
				int nrV = Integer.parseInt(JOptionPane.showInputDialog("Quantos v�rtices ter� o grafo?"));
				for (int i = 0; i < nrV; i++) {
					g.adicionaVertice(JOptionPane.showInputDialog("Insira o v�rtice (valor num�rico): "));
				}
				int nrE = Integer.parseInt(JOptionPane.showInputDialog("Quantas arestas ter� o grafo?"));
				for (int i = 0; i < nrE; i++) {
					g.adicionaAresta(g.vertices.get(Integer.parseInt(JOptionPane.showInputDialog("N�mero do v�rtice de origem:"))-1), g.vertices.get(Integer.parseInt(JOptionPane.showInputDialog("N�mero do v�rtice de destino:"))-1), Integer.parseInt(JOptionPane.showInputDialog("Valor da aresta")));
				}
				
				System.out.println(g);
				
			}else {
				System.out.println("Grafo n�o valorado e n�o direcionado");
			
				int nrV = Integer.parseInt(JOptionPane.showInputDialog("Quantos v�rtices ter� o grafo?"));
				for (int i = 0; i < nrV; i++) {
					g.adicionaVertice(JOptionPane.showInputDialog("Insira o v�rtice (valor num�rico): "));
				}
				int nrE = Integer.parseInt(JOptionPane.showInputDialog("Quantas arestas ter� o grafo?"));
				for (int i = 0; i < nrE; i++) {
					g.adicionaAresta(g.vertices.get(Integer.parseInt(JOptionPane.showInputDialog("N�mero do v�rtice de origem:"))-1), g.vertices.get(Integer.parseInt(JOptionPane.showInputDialog("N�mero do v�rtice de destino:"))-1), 0);
				}
				
				System.out.println(g);
			}         
		}
	}
}
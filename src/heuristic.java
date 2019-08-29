
public class heuristic {
	
	private instanceProblems instance;
	private int [][] costMatrix;
	private int [] routes;
	
	
	
	public heuristic(instanceProblems instance) {
		this.instance = instance;
	}
	
	
	
	//Algoritimo do vizinho mais proximo
	
	public void nearestNeighbor(int dep) {
		costMatrix = instance.getCostMatrix();
		int tamanho = instance.getNumberofClients();
		int menorcusto = 0, totalcusto = 0;
		int caminho = dep;
		int i = 0;
		routes = new int[instance.getNumberofClients()];
		System.out.print("caminho mais proximo: ");
		while(caminho < tamanho) {
			
			
			//posição inicial
			i = caminho;
			routes[i] = i; 
			System.out.print( routes[i] + " ->> ");
			
			//se posição for a final
			if(caminho == tamanho-1) {
				 //então o menor custo vai ser o custo da posição final até o deposito.
				 menorcusto= costMatrix[i][dep];
				 routes[i] = dep;
				 caminho++;
				 System.out.print( routes[i] );
				 
				 
			} else {
				
				// se não, inicialmente o menor custo vai ser o custo da nova posição até o nó
				// e o caminho vai ser a nova posicao+1 até que seja encontrado o novo menor custo no for
				// e o novo caminho 
				
				menorcusto = costMatrix[i][i+1];	
				caminho = i+1;
				
				for(int j = i+1; j<tamanho; j++) {
					
					
					if (costMatrix[i][j] < menorcusto ) {
						 
						 caminho = j;
						 menorcusto = costMatrix[i][j];
						 
					 } 
		 		 
					
				}
			}		
			
			//calcula o custo total
			totalcusto = totalcusto + menorcusto;	
		}
		
	    System.out.println("\ncusto total: " + totalcusto);
	
	}
	
		
		
	
	
}

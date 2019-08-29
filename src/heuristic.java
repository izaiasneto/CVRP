
public class heuristic {
	
	private instanceProblems instance;
	private int [][] costMatrix;
	private int [] routes;
	
	public heuristic(instanceProblems instance) {
		this.instance = instance;
	}
	
	//Algoritimo do vizinho mais proximo.
	public void nearestNeighbor(int dep) {
		
		int tam = instance.getNumberofClients();
		int menorcusto = 0, totalcusto = 0, i,  r = 1, caminho = 1;
		
		costMatrix = instance.getCostMatrix();
		routes = new int[instance.getNumberofClients()];
		
		System.out.print("caminho mais proximo: ");
		
		routes[r] = dep;
		r++;
		
		while(r < tam) {
			i = caminho;
			menorcusto = Integer.MAX_VALUE;
			
			for(int j = 1; j<tam; j++) {
				
				if( i == j) {	
					continue;
				} 
				
				if( j == dep) {
					continue;
				}
				
				
				if(costMatrix[i][j] < menorcusto) {
					if(routes.equals(null) ||  busca(j) == false) {
						caminho = j;
						menorcusto = costMatrix[i][j];
					} 
					
				} 
				
				
				
			}
			
			routes[r] = caminho;
			r++;
			
			totalcusto = totalcusto + menorcusto;
			
			
		}
		
		totalcusto += costMatrix[routes[15]][dep];
		
		
		for(int k=1; k<tam; k++) {
			System.out.print(routes[k] + ", ");
		}
		System.out.println("");
		System.out.println("custo: " + totalcusto);
	
	}
	
	public boolean busca(int elemento){ 
		for (int num : routes) { 
			if(num==elemento){ 
				return true; 
			} 
			
		} return false; 
		
	} 
}

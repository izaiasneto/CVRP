
public class heuristic {
	
	private instanceProblems instance;
	private int [][] costMatrix;
	private int [] routes;
	private int totalcusto;
	
	public heuristic() {
		
	}
	
	public heuristic(instanceProblems instance) {
		this.instance = instance;
	}
	
	public int[][] getCostMatrix() {
		return costMatrix;
	}

	public void setCostMatrix(int[][] costMatrix) {
		this.costMatrix = costMatrix;
	}

	public int getTotalcusto() {
		return totalcusto;
	}

	public void setTotalcusto(int totalcusto) {
		this.totalcusto = totalcusto;
	} 
	
	public int [] getRoutes() {
		return routes;
		
	}
	
	public void setRoutes(int [] routes) {
		this.routes = routes;
	}
	
	//Algoritimo do vizinho mais proximo.
	public void nearestNeighbor(int dep) {
		
		int tam = instance.getNumberofClients();
		int menorcusto = 0, totalcusto = 0, i,  r = 1, caminho = 1;
		
		costMatrix = instance.getCostMatrix();
		routes = new int[instance.getNumberofClients()];
		
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
					
					//Se rola é vazia ou o elemento nao foi visitado
					if(routes.equals(null) ||  busca(j) == false) {
						caminho = j;
						menorcusto = costMatrix[i][j];
					} 
					
				} 
							
				
			}
			//atribui o menor custo na rota
			routes[r] = caminho;
			r++;
			
			totalcusto = totalcusto + menorcusto;
			
			
		}
		
		totalcusto += costMatrix[routes[15]][dep];
		
		setTotalcusto(totalcusto);
		
		
	}
	
	//busca  se o elemento está no rout, se sim
	//retorna true ou retorna false
	public boolean busca(int elemento){ 
		for (int num : routes) { 
			if(num==elemento){ 
				return true; 
			} 
			
		} return false; 
		
	}


}

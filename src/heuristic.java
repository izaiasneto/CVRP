import java.util.ArrayList;

public class heuristic {
	private instanceProblems instance;
	private int [][] costMatrix;
	private Vehicle[] vehicles;
	private Solution[] solution;
	public static int solucaoTotal = 0;
	
	

	public Vehicle[] getVehicles() {
		return vehicles;
	}

	public void setVehicles(Vehicle[] vehicles) {
		this.vehicles = vehicles;
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

	

	
	//Algoritimo do vizinho mais proximo.
	public void nearestNeighbor() {
		int dep = instance.getDep();
		
		int shortestDistance = 0, i = 0,  r , l = 0, cliente = 0, totalcusto = 0;
		Client [] clients = instance.getClients();
		
		//copia os custos das distancias entre nós
		costMatrix = instance.getCostMatrix();
		//define o tamanho do array de veiculos.
		vehicles = new Vehicle[instance.getNumberOfVehicles()];
		
		
		//for para cada veiculo
		for(int v = 0; v < instance.getNumberOfVehicles(); v++) {
			
			//zera tudo para o proximo veiculo iniciar do deposito
			r=0;
			cliente = 0;
			totalcusto = 0;
			
			vehicles[v] = new Vehicle();
			
			//adiciona o deposito no inicio da rota do veiculo
			vehicles[v].AddClient(clients[cliente]);
			
			while(r < instance.getNumberofClients()) {
				
				i = cliente;		
				
				shortestDistance = Integer.MAX_VALUE;
				
				for(int j = 1; j<instance.getNumberofClients(); j++) {
						
						if(costMatrix[i][j] < shortestDistance) {
							//Se o elemento nao foi visitado
							if(clients[j].isInRoute() == false) {
								//atualiza o cliente
								cliente = j;
								//atualiza o menor caminho
								shortestDistance = costMatrix[i][j];						
							}
						} 
						
				} 
				
				//se o cliente não estiver na rota de algum veiculo
				if(clients[cliente].isInRoute() == false) {
					//Se a demanda do cliente nao ultrapassar a capacidade do veiculo 
					if(vehicles[v].CheckIfFits(clients[cliente].getDemand())) {				
						clients[cliente].setInRoute(true); //troca o InRoute do cliente para true		
						vehicles[v].AddClient(clients[cliente]); //adiciona o cliente na rota do veiculo
					}
				}
				r++;
					
		    }
					
		}
		
		//adiciona o deposito no final
		solution = new Solution[instance.getNumberOfVehicles()];
		for(int s= 0; s < solution.length; s++ ) {
			solution[s] = new Solution(s, vehicles[s].getRoute(), instance);
			solucaoTotal += solution[s].CalculateTotalCoust();
			solution[s].CalculateDemandaAtendida();
		}
		
		System.out.println("SOLUÇÃO TOTAL:" + solucaoTotal);
	}

	public Solution[] getSolution() {
		return solution;
	}

	public void setSolution(Solution[] solution) {
		this.solution = solution;
	}		

}

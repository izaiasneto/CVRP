import java.util.ArrayList;
import java.util.Random;

public class heuristic implements Cloneable{
	
	public static instanceProblems instance;
	private int [][] costMatrix;
	private Vehicle[] vehicles;
	public int vehicleCapacity;
	private Solution[] solution;
	public static int solucaoTotal = 0;
	private ArrayList<ElementTabuList> listaTabu = new ArrayList<ElementTabuList>();
	private int tamanhoListaTabu = 500;
	
	
	//Getters and Setters
	public heuristic clone()throws CloneNotSupportedException{  
		return (heuristic) super.clone();  
		} 

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
	
	public Solution[] getSolution() {
		return solution;
	}

	public void setSolution(Solution[] solution) {
		this.solution = solution;
	}	
	
	//Construção Gulosa.
	public void nearestNeighbor() {
			
		int shortestDistance = 0, i = 0,  r , cliente = 0;
		
		Client [] clients = instance.getClients();
		
		
		costMatrix = instance.getCostMatrix(); //copia os custos das distancias entre nós
		
		vehicles = new Vehicle[instance.getNumberOfVehicles()];//define o tamanho do array de veiculos.
		
		
				//for para cada veiculo
				for(int v = 0; v < instance.getNumberOfVehicles(); v++) {
					
					//zera tudo para o proximo veiculo iniciar do deposito
					r=0;
					cliente = 0;
					Random ran = new Random();
					int rand;
					
					
					vehicles[v] = new Vehicle();
					
					vehicleCapacity = vehicles[v].capacity;  //capacidade do veiculo
					
					vehicles[0].AddClient(clients[0]); //adiciona o deposito no inicio da rota do veiculo
					
					
					//enquanto nao visitar todos os clientes
					while(r < instance.getNumberofClients()) {
						
						i = cliente;		
						
						shortestDistance = Integer.MAX_VALUE; 
						
						for(int j = 1; j<instance.getNumberofClients(); j++) {
								
								rand = ran.nextInt(instance.getNumberofClients()+1); //pega um cliente aleatorio
								
								if(rand < instance.getNumberofClients() && rand != 0) {
									
									//Se a distancia do client para i for menor que a menor distancia
									if(costMatrix[i][rand] < shortestDistance) {
										
										//Se o elemento nao foi visitado
										if(clients[rand].isInRoute() == false) {
											
											
											cliente = rand; //atualiza o cliente
											
											shortestDistance = costMatrix[i][rand]; //atualiza o menor caminho						
										}
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
				
			//Nova verificação para completar a demanda com clientes que ainda nao foram adicionados
			for(int j = 1; j<instance.getNumberofClients(); j++) {
				
				rand = ran.nextInt(instance.getNumberofClients()+1); //pega o cliente aleatoriamente
				
				if(rand < instance.getNumberofClients() && rand != 0) {
				//Se o cliente ainda não está em Rota
					if(clients[rand].isInRoute() == false) {
						//Se a demanda do cliente nao ultrapassar a capacidade do veiculo 
						if(vehicles[v].CheckIfFits(clients[rand].getDemand())) {
								
							clients[rand].setInRoute(true); //troca o InRoute do cliente para true		
							vehicles[v].AddClient(clients[rand]); //adiciona o cliente na rota do veiculo
							}
						}
					}
			    }
		}
		
		
		
		solution = new Solution[instance.getNumberOfVehicles()];
		
		//imprimir
		System.out.println("::: Número de Clientes: " + instance.getNumberofClients()+ " :::");
		System.out.println("::: Número de Veiculos: " + instance.getNumberOfVehicles() + " :::");
		System.out.println("::: Capacidade de cada veiculo: " + Vehicle.capacity + " :::");
		System.out.println("");
		System.out.println("-----------------");
		System.out.println("");
		System.out.println("SOLUÇÃO INICIAL");
		System.out.println("");
		
		//adiciona o deposito no final em todas as rotas e adiciona todas em uma solucao
		for(int s= 0; s < solution.length; s++ ) {
			
			solution[s] = new Solution(s, vehicles[s].getRoute(), instance);
			solution[s].imprimirRota();
			solucaoTotal += solution[s].getTotalCost();
		}
		
		System.out.println("");
		System.out.println("SOLUÇÃO COM BUSCA LOCAL VND");
		
		int solucaoTotalMelhorada = 0;
		
		//coloca todos veiculos e rotas no Busca Local VND para encontrar uma melhor rota.
		for(int s= 0; s < solution.length; s++ ) {
			
			solution[s].VariableNeighborhoodDescent();
			solution[s].imprimirRota();
			solucaoTotalMelhorada += solution[s].getTotalCost();
		}
		
		
		System.out.println("::: SOLUÇÃO TOTAL:" + solucaoTotal);
		System.out.println("::: SOLUÇÃO TOTAL COM BUSCA LOCAL VND: " + solucaoTotalMelhorada);
	}



}

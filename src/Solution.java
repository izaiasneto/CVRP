import java.util.ArrayList;


public class Solution {
		private int id = 0;
		private instanceProblems instance;
		private int [][] costMatrix;
		private  ArrayList<Client> Route;
		private  int TotalCost;
		private  int DemandaAtendida;
		
		public Solution() {
			
		}
		
		public Solution (Solution solucao)
		{
		     this.id = solucao.id;
		     this.Route = (ArrayList<Client>) solucao.Route.clone();
		     this.instance = solucao.instance;
		    
		}
		
		public Solution(ArrayList<Client> Route, int id, instanceProblems instance) {
			this.setId(id);
			this.setRoute(Route);
			this.instance = instance;
			setDemandaAtendida(CalculateDemandaAtendida());
			setTotalCost(CalculateTotalCost());
			//imprimirRota();
			
		}
		
		public Solution(int id, ArrayList<Client> Route, instanceProblems instance) {
			Client cliente = new Client(0, 0);
			this.setId(id);
			this.setRoute(Route);
			Route.add(cliente);
			this.instance = instance;
			setDemandaAtendida(CalculateDemandaAtendida());
			setTotalCost(CalculateTotalCost());
			//imprimirRota();
			
		}
		
		public int getDemandaAtendida() {
			return DemandaAtendida;
		}

		public void setDemandaAtendida(int demandaAtendida) {
			DemandaAtendida = demandaAtendida;
		}
		public int getTotalCost() {
			return TotalCost;
		}

		public void setTotalCost(int totalCoust) {
			TotalCost = totalCoust;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public ArrayList<Client> getRoute() {
			return Route;
		}

		public void setRoute(ArrayList<Client> route) {
			Route = route;
		}
	

		public instanceProblems getInstance() {
			return instance;
		}

		public void setInstance(instanceProblems instance) {
			this.instance = instance;
		}
		
		public int CalculateTotalCost() {
			costMatrix = instance.getCostMatrix();
			int Cost = 0;
			
			
			for(int f = 0; f < Route.size()-1; f++) {
			
				Cost = Cost + costMatrix[Route.get(f).getId()][Route.get(f+1).getId()];
			}
				
		   		
		return Cost;
	  }
	
		public int CalculateDemandaAtendida() {
			int Cost = 0;
			
			for(int f = 0; f < Route.size(); f++) {
			
				Cost = Cost + Route.get(f).getDemand();
			}
			
		return Cost;
	  }
	
	

	
		
		public void imprimirRota() {
			System.out.println("-----------------");
			System.out.println("VEICULO: " + (id+1) + " :: Demanda atendida: " + DemandaAtendida);
			System.out.print("[");
			for(int teste = 0; teste <Route.size(); teste++) {
				System.out.print(Route.get(teste).getId());
				
				if(teste == Route.size()-1) {
					System.out.print("]");
				} else {
					System.out.print(", ");
				}
				
			}
			
			System.out.println("");
			
			System.out.println("SOLUÇÃO:" + TotalCost);
			
			System.out.println("");
		}
		
		
		public void VariableNeighborhoodDescent() {
			
			Client client1;
			Client client2;
			int custo = 0;
			
			//inicia de 1 pois o 0 é o deposito
			for(int i = 1; i < Route.size(); i++) {
				
				//if para nao deixar trocar com deposito
				if(i<(Route.size()-2)) {
					
					
					client1 = Route.get(i); //atribui ao client1 a posição i da rota
					client2 = Route.get(i+1); //atribui ao client2 a posição i+1 da rota
					
					//troca as posicoes i e i+1
					Route.set(i, client2);  
					Route.set(i+1, client1);
					
					//calcula o novo custo
					custo = CalculateTotalCost();
					
					//se o novo custo for menor que o custo anterior
					if(custo <  TotalCost) {
						 setTotalCost(custo); //atribui o novo custo no totalCost
					} else {
						//senao troca para a rota de antes
						 Route.set(i, client1);
						 Route.set(i+1, client2);
					}
					
					
				}
			}
			
			imprimirRota();
				
		}
		
		
		

}

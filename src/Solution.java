import java.util.ArrayList;


public class Solution {
		private int id;
		private instanceProblems instance;
		private int [][] costMatrix;
		private  ArrayList<Client> Route;
		private  int TotalCoust;
		

		public Solution(int id, ArrayList<Client> Route, instanceProblems instance) {
			Client cliente = new Client(0, 0);
			this.setId(id);
			this.setRoute(Route);
			Route.add(cliente);
			this.instance = instance;
			
			
			System.out.println("VEICULO: " + (id+1));
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
		}

		public int getTotalCoust() {
			return TotalCoust;
		}

		public void setTotalCoust(int totalCoust) {
			TotalCoust = totalCoust;
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
		
		public int CalculateTotalCoust() {
			costMatrix = instance.getCostMatrix();
			int Coust = 0;
			
			
			for(int f = 0; f < Route.size()-1; f++) {
			
				Coust = Coust + costMatrix[Route.get(f).getId()][Route.get(f+1).getId()];
			}
				
		    System.out.println("Solução  : " + Coust);
		    
			
		return Coust;
	  }
	
		public int CalculateDemandaAtendida() {
			int Coust = 0;
			
			for(int f = 0; f < Route.size()-1; f++) {
			
				Coust = Coust + Route.get(f).getDemand();
			}
				
		    System.out.print("Demanda Atendida  : " + Coust);
		    System.out.println(" ");
			
		return Coust;
	  }
}

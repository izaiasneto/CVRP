
public class solution {
	private heuristic heur;
	private int [] routes;
	private int custototal;
	private int [][] costMatrix; 
	
	public solution(heuristic heur) {
		this.heur = heur;
	}
	
	public void sol() {
		
		int aux;
		
		int [] routes = heur.getRoutes(); 
		int [] routes2 = new int[routes.length];
		int [] routes3 = new int[routes.length];
		
		costMatrix = heur.getCostMatrix();
		
		int custototal2 = 0;
		
		for(int i = 1; i < routes.length ; i++) {
			routes2[i] = routes[i];
			routes3[i] = routes[i];
		}
		
		aux = routes2[2];
		routes2[2] = routes2[3];
		routes2[3] = aux;
		
		aux = routes3[3];
		routes3[3] = routes3[4];
		routes3[4] = aux;
		
		for(int i = 1; i < routes2.length ; i++) {
			if(i == routes2.length-1) {
				 custototal = custototal + costMatrix[routes2[i]][routes2[1]];
				 custototal2 = custototal2 + costMatrix[routes3[i]][routes3[1]];
				 
			} else {
				
				custototal = custototal + costMatrix[routes2[i]][routes2[i+1]];
				custototal2 = custototal2 + costMatrix[routes3[i]][routes3[i+1]];
			
			}
	
		}
		
		System.out.print("caminho 1: ");
		for(int i = 1; i < routes.length; i++) {
			System.out.print( routes[i] + ", ");
		}
		System.out.print("   :::  custo 1: " + heur.getTotalcusto());
		System.out.println(" ");
		
		System.out.print("caminho 2: ");
		for(int i = 1; i < routes.length; i++) {
			System.out.print( routes2[i] + ", ");
		}
		System.out.println("   :::  custo 2: " + custototal);
		System.out.print("caminho 3: ");
		for(int i = 1; i < routes.length; i++) {
			System.out.print( routes3[i] + ", ");
		}
		System.out.print("   :::  custo 3: " + custototal2);
		
	}
}

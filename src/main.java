
public class main {

	public static void main(String[] args) {
					
				int deposito = 1; //posição do deposito
				
				instanceProblems instance = new instanceProblems();
				
				instance.InstanceFromFile("/Users/Izaias/eclipse-workspace/CVRP/instances/P-n16-k8.txt");
				
				heuristic h = new heuristic(instance);
				h.nearestNeighbor(deposito); 
				
				solution s = new solution(h);
				s.sol();
				
				
		
			  
	}

}


public class main {

	public static void main(String[] args) {					
				
				instanceProblems instance = new instanceProblems();
				
				instance.InstanceFromFile("/Users/Izaias/eclipse-workspace/CVRP/instances/P-n45-k5.txt");
				
				heuristic h = new heuristic(instance);
				h.nearestNeighbor();
				
					
			  
	}

}

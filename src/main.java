
public class main {

	public static void main(String[] args) {
		
			long tempoconstFinal = 0, tempometaFinal = 0;
			
			for( int i = 1; i<=1; i++ ) {
				
				
				
				instanceProblems instance = new instanceProblems();
				
				instance.InstanceFromFile("/Users/Izaias/eclipse-workspace/CVRP/instances/P-n19-k2.txt");
				
				//heuristica de construção
				heuristic h = new heuristic(instance);
				
				long tempoInicio = System.currentTimeMillis(); //calcular tempo
				h.nearestNeighbor();
				
				tempoconstFinal += (System.currentTimeMillis()-tempoInicio);
				

				//Busca Tabu
				
				long tempoInicio2 = System.currentTimeMillis(); //calcular tempo
		
				metaheuristic mt = new metaheuristic(h);
				mt.BuscaTabu();

				tempometaFinal += (System.currentTimeMillis()-tempoInicio2);
				
				
			}
			
			//tempoconstFinal = tempoconstFinal/10;
			//tempometaFinal = tempometaFinal/10;
			
			System.out.println("");
			System.out.println(" ------------------------------ ");
			
			System.out.println("TEMPO DE EXECUÇÃO DA HEURISTICA: " + tempoconstFinal + " ms");
			System.out.println("TEMPO DE EXECUÇÃO DA BUSCA TABU: " + tempometaFinal + " ms");
	}
}

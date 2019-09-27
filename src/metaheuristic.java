import java.util.ArrayList;

public class metaheuristic {
	
	private heuristic heuristic;
	private Solution[] solution;
	private ArrayList<ElementTabuList> listaTabu = new ArrayList<ElementTabuList>();
	private int tamanhoListaTabu = 45;
	
	
	//Getters and Setters
	public int getTamanhoListaTabu() {
		return tamanhoListaTabu;
	}


	public void setTamanhoListaTabu(int tamanhoListaTabu) {
		this.tamanhoListaTabu = tamanhoListaTabu;
	}
	
	public Solution[] getSolution() {
		return solution;
	}


	public void setSolution(Solution[] solution) {
		this.solution = solution;
	}
	
	
	
	public metaheuristic(heuristic heuristic) {
		this.heuristic = heuristic;
		this.solution = heuristic.getSolution();
		
		
		
	}


	public void BuscaTabu() {
		
		int Iter = 0;
		int melhorIter =0;
		int btMax = 50000; //numero de interações maxima
		int solucaoTotal = 0;
		
		Solution[] solucaoTemp = null;
		
		while((Iter-melhorIter)<= btMax) {
			
			Iter++;
			
			solucaoTemp = criaSolucaotroca(solution);
			
			
			//Se a função objetivo de solucaoTemp for menor que a do solution
			if(funcaoObjetivo(solucaoTemp) < funcaoObjetivo(solution)) {
				
				//solution igual a solution temp
				for(int k = 0; k < solution.length; k++) {
					solution[k] = new Solution(solucaoTemp[k]);
					solution[k].setDemandaAtendida(solution[k].CalculateDemandaAtendida());
				}
				
				melhorIter = Iter; //define a menlhor iteração ate o momento
				
			} 	
			
		}
		
		
		//imprimir a nova rota após aplicação da busca tabu
		
		System.out.println(" ");
		System.out.println("---------BUSCA TABU --------");
		
		for(int s= 0; s < solution.length; s++ ) {	
			solution[s].setTotalCost(solution[s].CalculateTotalCost());
			solucaoTotal += solution[s].getTotalCost();
			
			solution[s].imprimirRota();
		}
		
		System.out.println("SOLUCAO FINAL -  BUSCA TABU: " + solucaoTotal);
		
	}
	
	

	
	public Solution[] criaSolucaotroca(Solution[] solucao) {
		
		Solution[] solucaocriada  =  new Solution[solucao.length];
		
		for(int k = 0; k < solucao.length; k++) {
			solucaocriada[k] = new Solution(solucao[k]);
			solucaocriada[k].setDemandaAtendida(solucaocriada[k].CalculateDemandaAtendida());
			solucaocriada[k].setTotalCost(solucaocriada[k].CalculateTotalCost());
		}
		
		
		ArrayList<Client> roteBase = new ArrayList<Client>();
		ArrayList<Client> rotaParaTroca = new ArrayList<Client>();
		
		ArrayList<Client> clone1 = null;
		ArrayList<Client> clone2 = null;
		
		int custorotabase, custorotatroca;
		int indexrotaBase = -1;
		int indexrotaTroca = -1;
		int indexElemento1 = 0;
		int indexElemento2 = 0;
		int custoTempSolucao = Integer.MAX_VALUE;

		for(int i = 0; i < solucao.length; i ++) {
			
			roteBase = solucao[i].getRoute();    //define a primeira rota para troca
			indexrotaBase = i;                
			custorotabase = solucao[i].getTotalCost(); 
			
			
			
			//seleciona elemento da rota base
			for(int j = 1; j < roteBase.size() - 1 ; j++) {  //comeca de 1 porque nao pode ser o deposito
			
				Client clientRotaBase = roteBase.get(j); //pega o cliente da rota base.
				
				ArrayList<Client> rotabaseTemp = (ArrayList<Client>) roteBase.clone();
				
				if(clientRotaBase.getId() != 0){
					
					//pega a proxima rota para a troca
					for(int f = 0; f < solucao.length; f++) {
						
						if(f != indexrotaBase) { //para pegar uma rota diferente da primeira
							
							rotaParaTroca = solucao[f].getRoute(); 
							indexrotaTroca = f;
							custorotatroca = solucao[f].getTotalCost();
							
							
							for(int g = 1; g < rotaParaTroca.size() - 1; g++) { //comeca de 1 porque nao pode ser o deposito
								
								    Client clientRotaTroca = rotaParaTroca.get(g);
								    
									if(clientRotaTroca.getId() != 0) {
										
									
										//verificar se ultrapassa a capacidade do veiculo
										if(((custoDemanda(rotabaseTemp) - clientRotaBase.getDemand() + clientRotaTroca.getDemand()) > heuristic.vehicleCapacity) 
												|| ((custoDemanda(rotaParaTroca) + clientRotaBase.getDemand() - clientRotaTroca.getDemand()) > heuristic.vehicleCapacity)){
											  
											continue;
									    }
										
										//validar a Lista Tabu
										if(validaListaTabu(clientRotaBase.getId(), clientRotaTroca.getId())){
											continue;
										}
										
										clone1 = (ArrayList<Client>) solucao[indexrotaBase].getRoute().clone();
										clone2 = (ArrayList<Client>) solucao[indexrotaTroca].getRoute().clone();
										
										//troca temporaria
									
										Solution[] solucaoTemp2 = new Solution[solucao.length];
										
										for(int k = 0; k < solucao.length; k++) {
											solucaoTemp2[k] = new Solution(solucao[k]);
											solucaoTemp2[k].setDemandaAtendida(solucaoTemp2[k].CalculateDemandaAtendida());
										}
										
										
										//troca o item da rota base com outra rota
										solucaoTemp2[i].getRoute().set(j, clone2.get(g));
										
										//recalcula a demanda
										solucaoTemp2[indexrotaBase].setDemandaAtendida(solucaoTemp2[indexrotaBase].CalculateDemandaAtendida());
										//recalcula o custo total 
										solucaoTemp2[indexrotaBase].setTotalCost(solucaoTemp2[indexrotaBase].CalculateTotalCost());
										//armazena o custo em uma variavel chamda custoRotaTempBase
										int custoRotaTempBase = solucaoTemp2[indexrotaBase].getTotalCost();
										
										
										//troca o cliente de outra rota com a rota base
										solucaoTemp2[indexrotaTroca].getRoute().set(g, clone1.get(j));
								
										//recalcula a demanda
										solucaoTemp2[indexrotaTroca].setDemandaAtendida(solucaoTemp2[indexrotaTroca].CalculateDemandaAtendida());
										//recalcula o custo total
										solucaoTemp2[indexrotaTroca].setTotalCost(solucaoTemp2[indexrotaTroca].CalculateTotalCost());
										//armazena o custo total em uma variavel chamada custoRotaparaTrocaTemp
										int custoRotaParaTrocaTemp = solucaoTemp2[indexrotaTroca].getTotalCost();
										
										
										//calcula o custo total da nova solucao com a troca
										int custoSolucaoCriada = custoRotaTempBase + custoRotaParaTrocaTemp;
										
									
										
										//Teste de melhoria
										if(custoRotaTempBase < custorotabase || custoRotaParaTrocaTemp < custorotatroca || custoSolucaoCriada < custoTempSolucao){
											custoTempSolucao = custoSolucaoCriada;

											for(int k = 0; k < solucao.length; k++) {
												solucaocriada[k] = new Solution(solucaoTemp2[k]);
											}
											
											indexElemento1 = clientRotaBase.getId();
											indexElemento2 = clientRotaTroca.getId();
											
											
										}
										
										
		
								}
							}
						}
				   }
				}
			}
		}
		
		
		ElementTabuList elemento = new ElementTabuList(indexElemento1, indexElemento2);
		
		//adiciona o cliente na lista Tabu
		adicionaListaTabu(elemento);
	
		return solucaocriada;	
	
	}
	
	
	public boolean validaListaTabu(int elemento1, int elemento2){
		
		for (ElementTabuList elem1 : this.listaTabu) {
			
			if((elem1.getElemento1() == elemento1 || elem1.getElemento1() == elemento2) 
					&& (elem1.getElemento2() == elemento1 || elem1.getElemento2() == elemento2)){				
				return true;
			}
		}
		
		return false;
	}
	
	public int funcaoObjetivo(Solution[] sol){
		int valor = 0;
		
		for (int i = 0; i< sol.length; i++) {
			sol[i].setTotalCost(sol[i].CalculateTotalCost());
			valor += sol[i].getTotalCost();
		}
	
		return valor;
	}

	public int custoDemanda(ArrayList<Client> rota){
		int valor = 0;
		
		for (int i = 0; i< rota.size() ; i++) {
			valor += rota.get(i).getDemand();
		}
		return valor;
	}

public void adicionaListaTabu(ElementTabuList elem){
		
		if(this.listaTabu.size() + 1 > this.tamanhoListaTabu){
			this.listaTabu.add(0, elem);
			this.listaTabu.remove(this.listaTabu.size()-1);
		} else {
			this.listaTabu.add(0, elem);
		}
	}
	
}

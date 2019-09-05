import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Essa classe tem o numero de clientes, o numero de veiculos,
 * a capacidade de cada veiculo, a matriz de custos e demanda de cada cliente
 *
 * @author Izaias
 *
 */

public class instanceProblems {
	
	private int numberofClients;
	private int demandofClient;
	private int numberOfVehicles;
	private int dep = 0;
	private  Client [] clients;
	private int [][] costMatrix;
	private ArrayList <Client> visitedClients;
	private BufferedReader br;
	
	public instanceProblems() {
		this.setVisitedClients(new ArrayList <Client> ());
	}
	
	public int [][] getCostMatrix() {
		return costMatrix;
	}
	public void setCostMatrix(int [][] costMatrix) {
		this.costMatrix = costMatrix;
	}
	public int getNumberofClients() {
		return numberofClients;
	}
	public void setNumberofClients(int numberofClients) {
		this.numberofClients = numberofClients;
	}
	public int getDemandofClient() {
		return demandofClient;
	}
	public void setDemandofClient(int demandofClient) {
		this.demandofClient = demandofClient;
	}
	
	public int getNumberOfVehicles() {
		return numberOfVehicles;
	}

	public void setNumberOfVehicles(int numberOfVehicles) {
		this.numberOfVehicles = numberOfVehicles;
	}
	
	public Client [] getClients() {
		return clients;
	}
	public void setClients(Client [] clients) {
		this.clients = clients;
	}
	
	public ArrayList <Client> getVisitedClients() {
		return visitedClients;
	}

	public void setVisitedClients(ArrayList <Client> visitedClients) {
		this.visitedClients = visitedClients;
	}
	
	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}
	
	//tratar o arquivo de instancias
	public void InstanceFromFile(String file) {
		
		InputStream is;
		
		try {
			
			is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			br.readLine();
			
			String dimension = br.readLine();
			
			dimension = dimension.replace("DIMENSION: ", "");
			numberofClients = Integer.parseInt(dimension);
			
			//cria um vetor de clientes de tamanho numberofClients
			clients = new Client [numberofClients];
			
			
			String number = br.readLine();
			number = number.replace("VEHICLES: ", "");
			
			//capacidade dos veiculos
			numberOfVehicles = Integer.parseInt(number);
			
			
			String capacity = br.readLine();
			capacity = capacity.replace("CAPACITY: ", "");
			
			
			//capacidade dos veiculos
			Vehicle.capacity = Integer.parseInt(capacity);
			
			br.readLine();
			
			//para cada cliente lê-se seu ID e sua demanda
			
			int demand;
			int id;
			String line;
			
			
			
			//atribui os valores da demanda dos clientes
			for(int i=0; i<numberofClients; i++) {
				
				line = br.readLine();		
				Scanner sc = new Scanner(line);
				
				id = sc.nextInt();
				demand = sc.nextInt();
				
				Client newNode = new Client(id, demand);
				clients[i] = newNode;
				
				
				sc.close();
				
			}
			
			br.readLine();
			br.readLine();
			
			costMatrix = new int [numberofClients][numberofClients];
			int distance;
			Scanner sc;
			
			//distancias entre os nós
			for(int i=0; i<numberofClients; i++) {
				
				line = br.readLine();
				sc = new Scanner(line);
				
				
				for(int j=0; j<numberofClients; j++) {
					distance = sc.nextInt();
					costMatrix[i][j] = distance;
				}
				
				
				sc.close();
			}
			
	
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			System.out.println("Arquivo em formato inapropriado");
		}
		catch(NumberFormatException e) {
			System.out.println("Arquivo em formato inapropriado");
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		
	}

	

}

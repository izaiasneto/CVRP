import java.util.ArrayList;

public class Vehicle {

	
	private ArrayList<Client> Route = new ArrayList<Client>();
	public static int capacity;
	private int load;
	private int CurLoc;
	private boolean Closed;
	
	public ArrayList<Client> getRoute() {
		return Route;
	}


	public void setRoute(ArrayList<Client> route) {
		Route = route;
	}


	public int getLoad() {
		return load;
	}


	public void setLoad(int load) {
		this.load = load;
	}


	public int getCurLoc() {
		return CurLoc;
	}


	public void setCurLoc(int curLoc) {
		CurLoc = curLoc;
	}


	public boolean isClosed() {
		return Closed;
	}


	public void setClosed(boolean closed) {
		Closed = closed;
	}
	

	public Vehicle() {
		this.setLoad(0);
        this.setCurLoc(0); //Inicialmente no deposito;
        this.setClosed(false);
        this.Route.clear();

	}

	//adicionar cliente na rota do veículo
	public void AddClient(Client client) {
		Route.add(client);
		this.load += client.getDemand();
		this.CurLoc = client.getId();
	}
	
	//Checar a violação da capacidade
	public boolean CheckIfFits(int dem) {
		return (((load + dem) <= capacity));
	}
}

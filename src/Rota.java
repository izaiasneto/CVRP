import java.util.ArrayList;

public class Rota {
	private ArrayList<Client> listaCliente = new ArrayList<Client>();
	private Double custoTotal = 0.0;
	
	public Rota() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Client> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(ArrayList<Client> listaCliente) {
		
		for (Client cliente : listaCliente) {
			this.listaCliente.add(cliente);
		}				
	}
	
	public void setItemListaCliente(Client cliente) {
		this.listaCliente.add(cliente);
	}

	public Double getCustoTotal() {
		return custoTotal;
	}

	public void setCustoTotal(Double custoTotal) {
		this.custoTotal = custoTotal;
	}


}

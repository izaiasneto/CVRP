
public class Client {

		private int id;
		private int demand;
		private boolean inRoute;
		
		public Client(int id, int demand) {
			this.id = id;
			this.demand = demand;
			this.setInRoute(false);
			
			
		}
		
		public int getDemand() {
			return demand;
		}
		public void setDemand(int demand) {
			this.demand = demand;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public boolean isInRoute() {
			return inRoute;
		}

		public void setInRoute(boolean inRoute) {
			this.inRoute = inRoute;
		}

		
		

}

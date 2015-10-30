package happycube;

public class Edge {
   
	private boolean [][] edge; 
	
	public Edge(boolean [][] edge) {
		this.setEdge(edge); 
	}

	/**
	 * @return the edge
	 */
	public boolean [][] getEdge() {
		return edge;
	}

	/**
	 * @param edge the edge to set
	 */
	public void setEdge(boolean [][] edge) {
		this.edge = edge;
	}

	
	public void printEdge() {
     
	  for (int i=0; i<2; i++) {
		for (int j=0; j< this.getEdge()[0].length;j++) {
				if (this.getEdge()[i][j])
				    System.out.print("[]");
				else System.out.print("  ");
		}
		System.out.println();
	  }
	  System.out.println("------------");
	}
}

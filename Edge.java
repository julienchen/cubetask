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

		for (int j=0; j< this.getEdge()[0].length;j++) {
				if (this.getEdge()[0][j])
				    System.out.print("[]");
				else System.out.print("  ");
		}
			
	}
}

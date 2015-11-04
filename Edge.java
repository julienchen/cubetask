package happycube;

public class Edge {
   
	private boolean [][] edge; 
	private int ID;
	private boolean leftCornerMatch;
	private boolean rightCornerMatch;
	private int leftMatchPieceID;
	private int rightMatchPieceID;
	private int matchPieceID;
	
	
	public Edge(boolean [][] edge,int ID, int matchPieceID, int leftMatchPieceID,int rightMatchPieceID) {
		this.setEdge(edge);
		this.ID = ID;
		this.matchPieceID = matchPieceID;
		this.leftMatchPieceID = leftMatchPieceID;
		this.rightMatchPieceID = rightMatchPieceID;
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


	public boolean match2Edges(Edge baseEdge, Edge matchEdge){
		int N = baseEdge.getEdge()[0].length ;
		boolean [] matchResults = {false,false,false,false,false};
		boolean [] hasMatchResults = {false,false,false,false,false} ;
		boolean hasMatch=true;

		/*for(int i=0; i<N; i++) {
			matchResults[i] = baseEdge.getEdge()[0][i]^matchEdge.getEdge()[0][i];		
			
			if (i==0 || i==N-1) {	
				if (!matchResults[i] && !baseEdge.getEdge()[0][i] && !matchEdge.getEdge()[0][i]  
					&& (baseEdge.getEdge()[1][i]||matchEdge.getEdge()[1][i]) ){
						matchResults[i] = true;
				}
		    }
			
			hasMatch = hasMatch && matchResults[i];
		}*/
		
	 
		for(int i=0; i<N; i++) {
			// XOR the match pairs
			matchResults[i] = baseEdge.getEdge()[0][i]^matchEdge.getEdge()[N-1][i];	
			hasMatchResults[i] = matchResults[i];
			
			// The corner of Cube can not be (1,1), Otherwise is OK
			if (i==0) {		
				// has not matched piece
				if (baseEdge.getLeftMatchPieceID() == 0) {
					// 0 vs 0
					if (!matchResults[i] && !baseEdge.getEdge()[0][i] && !matchEdge.getEdge()[N-1][i]  
							&& (baseEdge.getEdge()[1][i]||matchEdge.getEdge()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
					
				}else { //has not matched piece   1 vs matched
					if (matchEdge.getEdge()[N-1][i] && baseEdge.isLeftCornerMatch())
						hasMatchResults[i]=false;
					
					   // 0 vs 0
					if (!matchResults[i] && !baseEdge.getEdge()[0][i] && !matchEdge.getEdge()[N-1][i]  
							&& (baseEdge.getEdge()[1][i]||matchEdge.getEdge()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				
				}
			}

			if (i==N-1) {
				// has not matched piece
				if (baseEdge.getRightMatchPieceID()== 0) {
					// 0 vs 0
					if (!matchResults[i] && !baseEdge.getEdge()[0][i] && !matchEdge.getEdge()[N-1][i]  
							&& (baseEdge.getEdge()[1][i]||matchEdge.getEdge()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				}else { //has not matched piece   1 vs matched
					if (matchEdge.getEdge()[N-1][i] && baseEdge.isRightCornerMatch())
						hasMatchResults[i]=false;
					
					   // 0 vs 0
					if (!matchResults[i] && !baseEdge.getEdge()[0][i] && !matchEdge.getEdge()[N-1][i]  
							&& (baseEdge.getEdge()[1][i]||matchEdge.getEdge()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				
				}
			}	
		
		
			hasMatch = hasMatch && matchResults[i];
		}
		
		/*Show Edge match results*/
		baseEdge.printEdge();
		matchEdge.printEdge();
		System.out.println("hasMatch=" + hasMatch);
		
		if (hasMatch) baseEdge.setMatchPieceID(matchEdge.getID());
		return hasMatch;
		
	}
	
	public boolean matchWithEdge(Edge matchEdge) {
		int N = this.getEdge()[0].length ;
		boolean [] matchResults = {false,false,false,false,false};
		boolean [] hasMatchResults = {false,false,false,false,false} ;
		boolean hasMatch=true;
        
		if (this.getMatchPieceID() > 0) return false;
	 
		for(int i=0; i<N; i++) {
			// XOR the match pairs
			matchResults[i] = this.getEdge()[0][i]^matchEdge.getEdge()[N-1][i];	
			hasMatchResults[i] = matchResults[i];
			
			// The corner of Cube can not be (1,1), Otherwise is OK
			if (i==0) {		
				// has not matched piece
				if (this.getLeftMatchPieceID() == 0) {
					// 0 vs 0
					if (!matchResults[i] && !this.getEdge()[0][i] && !matchEdge.getEdge()[N-1][i]  
							&& (this.getEdge()[1][i]||matchEdge.getEdge()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
					
				}else { //has not matched piece   1 vs matched
					if (matchEdge.getEdge()[N-1][i] && this.isLeftCornerMatch())
						hasMatchResults[i]=false;
					
					   // 0 vs 0
					if (!matchResults[i] && !this.getEdge()[0][i] && !matchEdge.getEdge()[N-1][i]  
							&& (this.getEdge()[1][i]||matchEdge.getEdge()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				
				}
			}

			if (i==N-1) {
				// has not matched piece
				if (this.getRightMatchPieceID()== 0) {
					// 0 vs 0
					if (!matchResults[i] && !this.getEdge()[0][i] && !matchEdge.getEdge()[N-1][i]  
							&& (this.getEdge()[1][i]||matchEdge.getEdge()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				}else { //has not matched piece   1 vs matched
					if (matchEdge.getEdge()[N-1][i] && this.isRightCornerMatch())
						hasMatchResults[i]=false;
					
					   // 0 vs 0
					if (!matchResults[i] && !this.getEdge()[0][i] && !matchEdge.getEdge()[N-1][i]  
							&& (this.getEdge()[1][i]||matchEdge.getEdge()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				
				}
			}	
		
		
			hasMatch = hasMatch && matchResults[i];
		}
		
		/*Show Edge match results*/
		this.printEdge();
		matchEdge.printEdge();
		System.out.println("hasMatch=" + hasMatch);
		
		if (hasMatch) {
			this.setMatchPieceID(matchEdge.getID());
		    this.setLeftCornerMatch(matchResults[0]);
		    this.setRightCornerMatch(matchResults[N-1]);
		}
		return hasMatch;
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

	/**
	 * @return the leftCornerMatch
	 */
	public boolean isLeftCornerMatch() {
		return leftCornerMatch;
	}

	/**
	 * @param leftCornerMatch the leftCornerMatch to set
	 */
	public void setLeftCornerMatch(boolean leftCornerMatch) {
		this.leftCornerMatch = leftCornerMatch;
	}

	/**
	 * @return the rightCornerMatch
	 */
	public boolean isRightCornerMatch() {
		return rightCornerMatch;
	}

	/**
	 * @param rightCornerMatch the rightCornerMatch to set
	 */
	public void setRightCornerMatch(boolean rightCornerMatch) {
		this.rightCornerMatch = rightCornerMatch;
	}

	/**
	 * @return the leftMatchPieceID
	 */
	public int getLeftMatchPieceID() {
		return leftMatchPieceID;
	}

	/**
	 * @param leftMatchPieceID the leftMatchPieceID to set
	 */
	public void setLeftMatchPieceID(int leftMatchPieceID) {
		this.leftMatchPieceID = leftMatchPieceID;
	}

	/**
	 * @return the rightMatchPieceID
	 */
	public int getRightMatchPieceID() {
		return rightMatchPieceID;
	}

	/**
	 * @param rightMatchPieceID the rightMatchPieceID to set
	 */
	public void setRightMatchPieceID(int rightMatchPieceID) {
		this.rightMatchPieceID = rightMatchPieceID;
	}

	/**
	 * @return the matchPieceID
	 */
	public int getMatchPieceID() {
		return matchPieceID;
	}

	/**
	 * @param matchPieceID the matchPieceID to set
	 */
	public void setMatchPieceID(int matchPieceID) {
		this.matchPieceID = matchPieceID;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
}

package happycube;


import java.util.*;

public class Cube {

	private static final int UP_EDGE=1;
	private static final int RIGHT_EDGE=2;
	private static final int DOWN_EDGE=3;
	private static final int LEFT_EDGE=4;
	private static final int MAX_ROTATE_STEP=7;
	
	private Map cubePieces;
	
	
	public Cube(Map cubePieces) {
		this.cubePieces = cubePieces;
		
	}
	
	public CubePiece getCubePiece(int i){
		return (CubePiece)this.cubePieces.get(i);	
	}
	
	
	public boolean match2Pieces(CubePiece basePiece, int edgeSide, CubePiece matchPiece){
			return match2Pieces(basePiece,edgeSide,matchPiece,true);
	}
	
	
	
	public boolean match2Pieces(CubePiece basePiece, int edgeSide, CubePiece matchPiece, boolean isAbleToRotate) {
		int N = basePiece.getPiece()[0].length;
		boolean [] matchResults = {false,false,false,false,false};
		boolean [] hasMatchResults = {false,false,false,false,false} ;
		boolean hasMatch;
		int rotateeMaxTime;
		
		if ((edgeSide == UP_EDGE && basePiece.getUpMatchPieceID() != 0) ||
		    (edgeSide == RIGHT_EDGE && basePiece.getRightMatchPieceID() != 0) ||
		    (edgeSide == DOWN_EDGE && basePiece.getDownMatchPieceID() != 0) ||
		    (edgeSide == LEFT_EDGE && basePiece.getLeftMatchPieceID() != 0) )
		return false;
		
		if (isAbleToRotate)  rotateeMaxTime=MAX_ROTATE_STEP;
		else rotateeMaxTime = 1;
		
		for (int k=0; k<= rotateeMaxTime; k++) {
			
			hasMatch=true;
			 
			
			if (edgeSide == UP_EDGE) {
				for(int i=0; i<N; i++) {
					matchResults[i] = basePiece.getPiece()[0][i]^matchPiece.getPiece()[N-1][i];	
					hasMatchResults[i] = matchResults[i];
					
					if (i==0 || i==N-1) {
						
						if  ((i==0 && matchResults[i] && basePiece.isUpLeftCornerMatch()) ||
						     (i==N-1 && matchResults[i] && basePiece.isUpRightCornerMatch())) hasMatchResults[i]=false;
						
						if (!matchResults[i] && !basePiece.getPiece()[0][i] && !matchPiece.getPiece()[N-1][i]  
							&& (basePiece.getPiece()[1][i]||matchPiece.getPiece()[N-2][i]) )
							hasMatchResults[i] = true;
						
				    }
					
					hasMatch = hasMatch && hasMatchResults[i];
				}	
				
				if (hasMatch) {
					if (matchResults[0]) {
						basePiece.setUpLeftCornerMatch(matchResults[0]);
						matchPiece.setDownLeftCornerMatch(matchResults[0]);
					}
					if (matchResults[N-1]) {
						basePiece.setUpRightCornerMatch(matchResults[N-1]);
						matchPiece.setDownRightCornerMatch(matchResults[N-1]);
					}
					basePiece.setUpMatchPieceID(matchPiece.getID());
					matchPiece.setDownMatchPieceID(basePiece.getID());
				}
					
					
			}else if(edgeSide == RIGHT_EDGE) {
				    for(int i=0; i<N; i++) {
				    	matchResults[i] = basePiece.getPiece()[i][N-1]^matchPiece.getPiece()[i][0];
				    	if (i==0 || i==N-1) {
				    		if  ((i==0 && matchResults[i] && basePiece.isUpRightCornerMatch()) ||
								 (i==N-1 && matchResults[i] && basePiece.isDownRightCornerMatch()) ) hasMatchResults[i]=false;
							
				    		if (!matchResults[i] && !basePiece.getPiece()[i][N-1] && !matchPiece.getPiece()[i][0]  
				    				&& (basePiece.getPiece()[i][N-2]||matchPiece.getPiece()[i][1]) )
								matchResults[i] = true;
				    	}
				    	hasMatch = hasMatch && matchResults[i];
				    }
				    
					if (hasMatch) {
						basePiece.setUpRightCornerMatch(matchResults[0]);
						matchPiece.setUpLeftCornerMatch(matchResults[0]);
						basePiece.setDownRightCornerMatch(matchResults[N-1]);
						matchPiece.setDownLeftCornerMatch(matchResults[N-1]);
						
						basePiece.setRightMatchPieceID(matchPiece.getID());
						matchPiece.setLeftMatchPieceID(basePiece.getID());
					}

			}else if(edgeSide == DOWN_EDGE) {
					for(int i=0; i<N; i++) {					
						matchResults[i] = basePiece.getPiece()[N-1][i]^matchPiece.getPiece()[0][i];
						if (i==0 || i==N-1) {
	
							if  ((i==0 && matchResults[i] && basePiece.isDownLeftCornerMatch()) ||
							    (i==N-1 && matchResults[i] && basePiece.isDownRightCornerMatch()))  hasMatchResults[i]=false;
							
							if (!matchResults[i] && !basePiece.getPiece()[N-1][i] && !matchPiece.getPiece()[0][i]  
								&& (basePiece.getPiece()[N-2][i]||matchPiece.getPiece()[1][i]) )
									matchResults[i] = true;
						}
						hasMatch = hasMatch && matchResults[i];
					}
					if (hasMatch) {
						basePiece.setDownLeftCornerMatch(matchResults[0]);
						matchPiece.setUpLeftCornerMatch(matchResults[0]);
						basePiece.setDownRightCornerMatch(matchResults[N-1]);
						matchPiece.setUpRightCornerMatch(matchResults[N-1]);
						
						basePiece.setDownMatchPieceID(matchPiece.getID());
						matchPiece.setUpMatchPieceID(basePiece.getID());
					}
					
			}else if(edgeSide == LEFT_EDGE) {
					for(int i=0; i<N; i++) {	
						matchResults[i] = basePiece.getPiece()[i][0]^matchPiece.getPiece()[i][N-1];
						if (i==0 || i==N-1) {
						
							if  ((i==0 && matchResults[i] && basePiece.isUpLeftCornerMatch()) ||
							    (i==N-1 && matchResults[i] && basePiece.isDownLeftCornerMatch()) ) hasMatchResults[i]=false;
							
							if (!matchResults[i] && !basePiece.getPiece()[i][0] && !matchPiece.getPiece()[i][N-1]  
							&& (basePiece.getPiece()[i][1]||matchPiece.getPiece()[i][N-2]) )
								matchResults[i] = true;
						}
						hasMatch = hasMatch && matchResults[i];
					}
					
					if (hasMatch) {
						basePiece.setUpLeftCornerMatch(matchResults[0]);
						matchPiece.setUpRightCornerMatch(matchResults[0]);
						basePiece.setDownLeftCornerMatch(matchResults[N-1]);
						matchPiece.setDownRightCornerMatch(matchResults[N-1]);
						
						basePiece.setLeftMatchPieceID(matchPiece.getID());
						matchPiece.setRightMatchPieceID(basePiece.getID());
					}	
			}	
						
			
			if (hasMatch) {
				matchPiece.setRotateStep(k+1);
				basePiece.printPiece();
				matchPiece.printPiece();
				return true;
			}
			else if (k!=3) { 
				CubePiece.rotatePiece(matchPiece.getPiece());
				System.out.println(k);
				
			}
			else { // Once rotate 360°, mirror the piece.
				CubePiece.rotatePiece(matchPiece.getPiece());
				CubePiece.mirroringPiece(matchPiece.getPiece());
				matchPiece.setMirroring(true);
			}
		}
		return false;

	}
	
	
	public boolean matchAllEdges(CubePiece basePiece,CubePiece matchPiece ){
		
		if (match2Pieces(basePiece,UP_EDGE,matchPiece)) return true;
		else if (match2Pieces(basePiece,RIGHT_EDGE,matchPiece)) return true;
		else if (match2Pieces(basePiece,DOWN_EDGE,matchPiece)) return true;
		else if (match2Pieces(basePiece,LEFT_EDGE,matchPiece)) return true;
		
		return false;
	}
	
	
	public boolean match2Edges(Edge baseEdge, Edge matchEdge){
		int N = baseEdge.getEdge()[0].length ;
		boolean [] matchResults = {false,false,false,false,false};
		boolean hasMatch=true;

		for(int i=0; i<N; i++) {
			matchResults[i] = baseEdge.getEdge()[0][i]^matchEdge.getEdge()[0][i];		
			
			if (i==0 || i==N-1) {	
				if (!matchResults[i] && !baseEdge.getEdge()[0][i] && !matchEdge.getEdge()[0][i]  
					&& (baseEdge.getEdge()[1][i]||matchEdge.getEdge()[1][i]) )
						matchResults[i] = true;
				
				 
				
		    }
			
			hasMatch = hasMatch && matchResults[i];
		}	
		
		return hasMatch;
		
	}
	

	public boolean matchFacePiece(CubePiece basePiece, CubePiece matchPiece){
		
		if (match2Pieces(this.getCubePiece(basePiece.getUpMatchPieceID()),UP_EDGE,matchPiece)) {
			
			if (match2Edges(basePiece.getRightEdge(),matchPiece.getRightEdge()) &&
				match2Edges(basePiece.getDownEdge(),matchPiece.getDownEdge())  &&
				match2Edges(basePiece.getLeftEdge(),matchPiece.getLeftEdge()))
			return true;
			
		}
		
		return false;	
		
	}
	
	
	public static void main(String[] args) {
		final boolean O = false;
		final boolean I = true;
		
		boolean [][] piece1 = {{O,O,I,O,O},
							   {O,I,I,I,O},
							   {I,I,I,I,I},
							   {O,I,I,I,O},
							   {O,O,I,O,O}};

		boolean [][] piece2 = {{I,O,I,O,I},
				   			   {I,I,I,I,I},
				   			   {O,I,I,I,O},
				   			   {I,I,I,I,I},
				   			   {I,O,I,O,I}};

		boolean [][] piece3 = {{O,O,I,O,O},
	   			   			   {O,I,I,I,I},
	   			   			   {I,I,I,I,O},
	   			   			   {O,I,I,I,I},
	   			   			   {O,O,I,O,O}};

		boolean [][] piece4 = {{O,I,O,I,O},
	   			   			   {I,I,I,I,O},
	   			   			   {O,I,I,I,I},
	   			   			   {I,I,I,I,O},
	   			   			   {I,I,O,I,O}};
		
		boolean [][] piece5 = {{O,I,O,I,O},
	   			   			   {I,I,I,I,I},
	   			   			   {O,I,I,I,O},
	   			   			   {I,I,I,I,I},
	   			   			   {I,O,I,O,O}};
		
		boolean [][] piece6 = {{O,I,O,I,O},
	   			   			   {O,I,I,I,I},
	   			   			   {I,I,I,I,O},
	   			   			   {O,I,I,I,I},
	   			   			   {I,I,O,I,I}};
	
	
	
	  CubePiece cubePiece1 = new CubePiece(1,piece1);
	  CubePiece cubePiece2 = new CubePiece(2,piece2);
	  CubePiece cubePiece3 = new CubePiece(3,piece3);
	  CubePiece cubePiece4 = new CubePiece(4,piece4);
	  CubePiece cubePiece5 = new CubePiece(5,piece5);
	  CubePiece cubePiece6 = new CubePiece(6,piece6);
	  
	  Map testCubePieces = new HashMap();
	  
	  testCubePieces.put(cubePiece1.getID(), cubePiece1);
	  testCubePieces.put(cubePiece2.getID(), cubePiece2);
	  testCubePieces.put(cubePiece3.getID(), cubePiece3);
	  testCubePieces.put(cubePiece4.getID(), cubePiece4);
	  testCubePieces.put(cubePiece5.getID(), cubePiece5);
	  testCubePieces.put(cubePiece6.getID(), cubePiece6);
	  
	  
	  Cube testCube = new Cube(testCubePieces);
	  testCube.matchAllEdges(cubePiece1, cubePiece2);
	  
	  UnfoldedSolution.putCubePiece(testCube);
	  UnfoldedSolution.printUnfoldedSolution();
	  
	  
	  
	  System.out.println(cubePiece1.getLeftMatchPieceID());
	  System.out.println(cubePiece1.getDownMatchPieceID());
	  System.out.println(cubePiece1.getRightMatchPieceID());
	  System.out.println(cubePiece1.getUpMatchPieceID());

	  System.out.println(cubePiece2.getLeftMatchPieceID());
	  System.out.println(cubePiece2.getDownMatchPieceID());
	  System.out.println(cubePiece2.getRightMatchPieceID());
	  System.out.println(cubePiece2.getUpMatchPieceID());
	  
	}

}

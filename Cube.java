package happycube;


import java.util.*;

public class Cube {

	private static final int UP_EDGE=1;
	private static final int RIGHT_EDGE=2;
	private static final int DOWN_EDGE=3;
	private static final int LEFT_EDGE=4;
	private static final int MAX_ROTATE_STEP=7;
	
	private Map<Integer, CubePiece> cubePieces;
	
	private CubeStateStack<Cube> stack;
	
	public CubeStateStack<Cube> getStack(){
		return stack;	
	}
	
	public void setStack(CubeStateStack<Cube> stack){
		this.stack = stack;
	}
	
	
	public String getEdgeName(int i) {
		String edgeString = null;
	 
		switch (i) {
        case UP_EDGE:  	 edgeString = "UP_EDGE";
                 break;
        case RIGHT_EDGE: edgeString = "RIGHT_EDGE";
                 break;
        case DOWN_EDGE:  edgeString = "DOWN_EDGE";
        		break;
        case LEFT_EDGE:  edgeString = "LEFT_EDGE";
				break; 		
		}
		return edgeString;
	}
	
	public Cube(Map<Integer, CubePiece> cubePieces) {
		this.cubePieces = cubePieces;
		this.stack = new CubeStateStack<Cube>();
		
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
		
		for (int k=matchPiece.getRotateStep(); k<= rotateeMaxTime; k++) {
			
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
				System.out.println( getEdgeName(edgeSide) );
				
				//basePiece.printPiece();
				matchPiece.printPiece();				
				stack.push(this);
				
				return true;
			}
			else if (k!=3) { 
				CubePiece.rotatePiece(matchPiece.getPiece());
				//System.out.println(k);
				
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
					&& (baseEdge.getEdge()[1][i]||matchEdge.getEdge()[1][i]) ){
						matchResults[i] = true;
				}
		    }
			
			hasMatch = hasMatch && matchResults[i];
		}	
		
		/*Show Edge match results*/
		baseEdge.printEdge();
		matchEdge.printEdge();
		System.out.println("hasMatch=" + hasMatch);
		
		return hasMatch;
		
	}
	

public boolean matchFacePiece(CubePiece basePiece, CubePiece matchPiece){
		
    while (matchPiece.getRotateStep() < 7) {
		if (matchAllEdges(this.getCubePiece(basePiece.getUpMatchPieceID()),matchPiece)) {
			
			if (match2Edges(basePiece.getRightEdge(),matchPiece.getRightEdge()) &&
				match2Edges(basePiece.getDownEdge(),matchPiece.getDownEdge())  &&
				match2Edges(basePiece.getLeftEdge(),matchPiece.getLeftEdge()))
			{
				System.out.println( "AT FACE" );
				matchPiece.printPiece();
				return true;
			}
		}
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
	  
	  Map<Integer, CubePiece> testCubePieces = new HashMap<Integer, CubePiece>();
	  
	  testCubePieces.put(new Integer(cubePiece1.getID()), cubePiece1);
	  testCubePieces.put(new Integer(cubePiece2.getID()), cubePiece2);
	  testCubePieces.put(new Integer(cubePiece3.getID()), cubePiece3);
	  testCubePieces.put(new Integer(cubePiece4.getID()), cubePiece4);
	  testCubePieces.put(new Integer(cubePiece5.getID()), cubePiece5);
	  testCubePieces.put(new Integer(cubePiece6.getID()), cubePiece6);
	  
	  
	  Cube testCube = new Cube(testCubePieces);
	  
	  UnfoldedSolution.setInitialCubePieces(testCube);
	  UnfoldedSolution.printUnfoldedDisplaying();

	  SolutionEngine sEngine = new SolutionEngine();
			  
	  sEngine.searchSolution(testCube, false);
	  
	  /*
	  int noMatchID=0;
	  int noMatchNum=0;

	  if(!testCube.matchAllEdges(cubePiece1, cubePiece2)) {  
		  noMatchID = 2;
		  noMatchNum += 1;
		  if (!testCube.matchAllEdges(cubePiece1, cubePiece3))	System.out.println("Error, No match!");
		  else if (!testCube.matchAllEdges(cubePiece1, cubePiece4))	System.out.println("Error, No match!");
		  else if (!testCube.matchAllEdges(cubePiece1, cubePiece5))	System.out.println("Error, No match!");
		  else if (!testCube.matchAllEdges(cubePiece1, cubePiece6))	System.out.println("Error, No match!");
	  } else 
		  if (!testCube.matchAllEdges(cubePiece1, cubePiece3)) {
			  noMatchID = 3;
			  noMatchNum += 1;
			  if (!testCube.matchAllEdges(cubePiece1, cubePiece4))	System.out.println("Error, No match!");
			  else if (!testCube.matchAllEdges(cubePiece1, cubePiece5))	System.out.println("Error, No match!");
			  else if (!testCube.matchAllEdges(cubePiece1, cubePiece6))	System.out.println("Error, No match!");
		  } else 	  	
			  if (!testCube.matchAllEdges(cubePiece1, cubePiece4) ) {
			  noMatchID = 4;
			  noMatchNum += 1;
				  if (!testCube.matchAllEdges(cubePiece1, cubePiece5))	System.out.println("Error, No match!");
				  else if (!testCube.matchAllEdges(cubePiece1, cubePiece6))	System.out.println("Error, No match!");		  
		  } else {
	
			  if (!testCube.matchAllEdges(cubePiece1, cubePiece5))  {		  
				  noMatchID = 5;
				  noMatchNum += 1;
				  if (!testCube.matchAllEdges(cubePiece1, cubePiece6))	System.out.println("Error, No match!");	
			  } else if (!testCube.matchAllEdges(cubePiece1, cubePiece6))  {
				  noMatchID = 6;
				  noMatchNum += 1;
			  }
		  }
	  
	  System.out.println(noMatchID);
	  
	  if (noMatchNum == 1) {
		  if( testCube.matchFacePiece(cubePiece1, testCube.getCubePiece(noMatchID))) 
		    System.out.println("test OK");
		  
		  
		  
	  } else { 
		  System.out.println("test KO");
		  System.out.println("noMatchNum =" + noMatchNum);
	  }
	  
	  cubePiece1.setFaceMatchPieceID(noMatchID);
	  UnfoldedSolution.setSolutionCubePieces(testCube);
	  UnfoldedSolution.printUnfoldedDisplaying();

	  System.out.println(cubePiece1.getUpMatchPieceID());
	  System.out.println(cubePiece1.getRightMatchPieceID());  
	  System.out.println(cubePiece1.getDownMatchPieceID());
	  System.out.println(cubePiece1.getLeftMatchPieceID());
	  System.out.println(cubePiece1.getFaceMatchPieceID());
	  */

	}

}

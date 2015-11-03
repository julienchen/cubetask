package happycube;


import java.util.*;

public class Cube {

	private static final int UP_EDGE=1;
	private static final int RIGHT_EDGE=2;
	private static final int DOWN_EDGE=3;
	private static final int LEFT_EDGE=4;
	private static final int MAX_ROTATE_STEP=7;
	
	private int noMatchID;
	private int noMatchNum;
	private int basePieceID;
	
	private Map<Integer, CubePiece> cubePieces;	
	private CubeStateStack<Integer> candidatePiecesID;
	private CubeStateStack<Cube> stateStack;
		
	
	public Cube(Map<Integer, CubePiece> cubePieces) {
		this.noMatchID = 0;
		this.noMatchNum = 0;
		this.cubePieces = cubePieces;
		this.stateStack = new CubeStateStack<Cube>();
		this.candidatePiecesID = new CubeStateStack<Integer>();
		// initialize candidate stack
		for (int i=cubePieces.size(); i<2; i--) {
			this.candidatePiecesID.push(Integer.valueOf(i));
		}	
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
		
		// Once the edge side has been occupied, then stop the matching process
		if ((edgeSide == UP_EDGE && basePiece.getUpMatchPieceID() != 0) ||
		    (edgeSide == RIGHT_EDGE && basePiece.getRightMatchPieceID() != 0) ||
		    (edgeSide == DOWN_EDGE && basePiece.getDownMatchPieceID() != 0) ||
		    (edgeSide == LEFT_EDGE && basePiece.getLeftMatchPieceID() != 0) )
		return false;
		
		// All matching pieces can rotate 7 steps, except the last Piece
		if (isAbleToRotate)  rotateeMaxTime=MAX_ROTATE_STEP;
		else rotateeMaxTime = 1;
				
		for (int k=matchPiece.getRotateStep(); k<= rotateeMaxTime; k++) {

			if (edgeSide == UP_EDGE) {
				basePiece.matchAtUp(matchPiece);
				if (basePiece.getUpMatchPieceID() == matchPiece.getID())  return true;
			}else if(edgeSide == RIGHT_EDGE) {
				basePiece.matchAtRight(matchPiece);
				if (basePiece.getRightMatchPieceID() == matchPiece.getID())  return true;
			}else if(edgeSide == DOWN_EDGE) {
				basePiece.matchAtDown(matchPiece);
				if (basePiece.getDownMatchPieceID() == matchPiece.getID())  return true;
			}else if(edgeSide == LEFT_EDGE) {
				basePiece.matchAtLeft(matchPiece);
				if (basePiece.getLeftMatchPieceID() == matchPiece.getID())  return true;
			}			
		
		}
		return false;
	}
	
		
	
	public boolean matchAllEdges(CubePiece basePiece,CubePiece matchPiece ){
		
		if (match2Pieces(basePiece,UP_EDGE,matchPiece)) return true;
		else if (match2Pieces(basePiece,RIGHT_EDGE,matchPiece)) return true;
		else if (match2Pieces(basePiece,DOWN_EDGE,matchPiece)) return true;
		else if (match2Pieces(basePiece,LEFT_EDGE,matchPiece)) return true;
		
		this.setNoMatchID(matchPiece.getID());
		this.increaseNoMatchNum();
		return false;
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

	/**
	 * @return the candidatePiecesID
	 */
	public CubeStateStack<Integer> getcandidatePiecesID() {
		return candidatePiecesID;
	}

	/**
	 * @param candidatePiecesID the candidatePiecesID to set
	 */
	public void setcandidatePiecesID(CubeStateStack<Integer> candidatePiecesID) {
		this.candidatePiecesID = candidatePiecesID;
	}

	/**
	 * @return the noMatchNum
	 */
	public int getNoMatchNum() {
		return noMatchNum;
	}

	/**
	 * @param noMatchNum the noMatchNum to set
	 */
	public void setNoMatchNum(int noMatchNum) {
		this.noMatchNum = noMatchNum;
	}
	
	/**
	 * @param increase noMatchNum by 1
	 */
	
	public void increaseNoMatchNum() {
		this.noMatchNum += 1;
	}

	/**
	 * @return the basePieceID
	 */
	public int getBasePieceID() {
		return basePieceID;
	}

	/**
	 * @param basePieceID the basePieceID to set
	 */
	public void setBasePieceID(int basePieceID) {
		this.basePieceID = basePieceID;
	}

	/**
	 * @return the noMatchID
	 */
	public int getNoMatchID() {
		return noMatchID;
	}

	/**
	 * @param noMatchID the noMatchID to set
	 */
	public void setNoMatchID(int noMatchID) {
		this.noMatchID = noMatchID;
	}

	
	public CubeStateStack<Cube> getStack(){
		return stateStack;	
	}
	
	public void setStack(CubeStateStack<Cube> stateStack){
		this.stateStack = stateStack;
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
	
	
	
	public CubePiece getCubePiece(int i){
		return (CubePiece)this.cubePieces.get(i);	
	}
}

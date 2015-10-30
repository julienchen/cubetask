package happycube;


public class SolutionEngine {

	private Cube cube;
	
	public SolutionEngine() {
		// TODO Auto-generated constructor stub
	}

	
	public void searchSolution (Cube cube, boolean next) {
				
		  int noMatchID=0;
		  int noMatchNum=0;

		  for (int i=2; i< 6; i++) {
			  
			  if (cube.getCubePiece(1).getUpMatchPieceID() == i  ) {
				  
			  }
			  
		  }
		  
		  
		  
		  
		  if(!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(2))) {  
			  noMatchID = 2;
			  noMatchNum += 1;
			  if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(3)))	System.out.println("Error, No match!");
			  else if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(4)))	System.out.println("Error, No match!");
			  else if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(5)))	System.out.println("Error, No match!");
			  else if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(6)))	System.out.println("Error, No match!");
		  } else 
			  if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(3))) {
				  noMatchID = 3;
				  noMatchNum += 1;
				  if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(4)))	System.out.println("Error, No match!");
				  else if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(5)))	System.out.println("Error, No match!");
				  else if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(6)))	System.out.println("Error, No match!");
			  } else 	  	
				  if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(4)) ) {
				  noMatchID = 4;
				  noMatchNum += 1;
					  if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(5)))	System.out.println("Error, No match!");
					  else if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(6)))	System.out.println("Error, No match!");		  
			  } else {
		
				  if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(5)))  {		  
					  noMatchID = 5;
					  noMatchNum += 1;
					  if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(6)))	System.out.println("Error, No match!");	
				  } else if (!cube.matchAllEdges(cube.getCubePiece(1), cube.getCubePiece(6)))  {
					  noMatchID = 6;
					  noMatchNum += 1;
				  }
			  }
		  
		  System.out.println(noMatchID);
		  
		  if (noMatchNum == 1) {
			  if( cube.matchFacePiece(cube.getCubePiece(1), cube.getCubePiece(noMatchID))) 
			    System.out.println("test OK");
			  else {
				  cube = cube.getStack().pop();
				  
			  }
 
		  } else { 
			  System.out.println("test KO");
			  System.out.println("noMatchNum =" + noMatchNum);

		  }
		  
		  cube.getCubePiece(1).setFaceMatchPieceID(noMatchID);
		  UnfoldedSolution.setSolutionCubePieces(cube);
		  UnfoldedSolution.printUnfoldedDisplaying();

		  System.out.println(cube.getCubePiece(1).getUpMatchPieceID());
		  System.out.println(cube.getCubePiece(1).getRightMatchPieceID());  
		  System.out.println(cube.getCubePiece(1).getDownMatchPieceID());
		  System.out.println(cube.getCubePiece(1).getLeftMatchPieceID());
		  System.out.println(cube.getCubePiece(1).getFaceMatchPieceID());
		
	}


	/**
	 * @return the cube
	 */
	public Cube getCube() {
		return cube;
	}


	/**
	 * @param cube the cube to set
	 */
	public void setCube(Cube cube) {
		this.cube = cube;
	}
	
	
	
	
}

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
	
	public boolean match2Pieces(CubePiece basePiece, int edgeSide, CubePiece matchPiece) {
		int N = basePiece.getPiece()[0].length;
		boolean [] matchResults = {false,false,false,false,false};
		boolean hasMatch;
		
		if ((edgeSide == UP_EDGE && basePiece.getUpMatchPieceID() != 0) ||
		    (edgeSide == RIGHT_EDGE && basePiece.getRightMatchPieceID() != 0) ||
		    (edgeSide == DOWN_EDGE && basePiece.getDownMatchPieceID() != 0) ||
		    (edgeSide == LEFT_EDGE && basePiece.getLeftMatchPieceID() != 0) )
		return false;
		
		for (int k=0; k<= MAX_ROTATE_STEP; k++) {
			
			hasMatch=true;
			
			for(int i=0; i<N; i++) {
				if (edgeSide == UP_EDGE) {
					matchResults[i] = basePiece.getPiece()[0][i]^matchPiece.getPiece()[N-1][i];
				}else if(edgeSide == RIGHT_EDGE) {
					matchResults[i] = basePiece.getPiece()[i][N-1]^matchPiece.getPiece()[i][0];
				}else if(edgeSide == DOWN_EDGE) {
					matchResults[i] = basePiece.getPiece()[N-1][i]^matchPiece.getPiece()[0][i];
				}else if(edgeSide == LEFT_EDGE) {
					matchResults[i] = basePiece.getPiece()[i][0]^matchPiece.getPiece()[i][N-1];
				}
			}	
			
			for (int j=0; j<N; j++) {
				hasMatch = hasMatch && matchResults[j];
				//System.out.println(matchResults[j]);
			}
			
			if (hasMatch) {
				if (edgeSide == UP_EDGE) {
					basePiece.setUpMatchPieceID(matchPiece.getID());
					matchPiece.setDownMatchPieceID(basePiece.getID());
				}else if(edgeSide == RIGHT_EDGE) {
					basePiece.setRightMatchPieceID(matchPiece.getID());
					matchPiece.setLeftMatchPieceID(basePiece.getID());
				}else if(edgeSide == DOWN_EDGE) {
					basePiece.setDownMatchPieceID(matchPiece.getID());
					matchPiece.setUpMatchPieceID(basePiece.getID());
				}else if(edgeSide == LEFT_EDGE) {
					basePiece.setLeftMatchPieceID(matchPiece.getID());
					matchPiece.setRightMatchPieceID(basePiece.getID());
				}
				matchPiece.setRotateStep(k+1);
				return true;
			}
			else if (k!=3) { 
				CubePiece.rotatePiece(matchPiece.getPiece());
				System.out.println(k);
				matchPiece.printPiece();
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

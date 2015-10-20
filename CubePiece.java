package happycube;

public class CubePiece {

	private int ID;
	private int upMatchPieceID;
	private int rightMatchPieceID;
	private int downMatchPieceID;
	private int leftMatchPieceID;
	private boolean [][] piece;
	private boolean mirroring;
	private int rotateStep;
	
	public CubePiece(int ID, boolean[][] piece){
		this.ID = ID;
		this.piece = piece;
		this.upMatchPieceID = 0;
		this.rightMatchPieceID = 0;
		this.downMatchPieceID = 0;
		this.leftMatchPieceID = 0;
		this.setMirroring(false);
		this.setRotateStep(0);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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


	/**
	 * @return the upMatchPieceID
	 */
	public int getUpMatchPieceID() {
		return upMatchPieceID;
	}


	/**
	 * @param upMatchPieceID the upMatchPieceID to set
	 */
	public void setUpMatchPieceID(int upMatchPieceID) {
		this.upMatchPieceID = upMatchPieceID;
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
	 * @return the downMatchPieceID
	 */
	public int getDownMatchPieceID() {
		return downMatchPieceID;
	}


	/**
	 * @param downMatchPieceID the downMatchPieceID to set
	 */
	public void setDownMatchPieceID(int downMatchPieceID) {
		this.downMatchPieceID = downMatchPieceID;
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
	 * @return the piece
	 */
	public boolean [][] getPiece() {
		return piece;
	}


	/**
	 * @param piece the piece to set
	 */
	public void setPiece(boolean [][] piece) {
		this.piece = piece;
	}

	
	 static boolean[][] rotatePiece(boolean[][] piece){
	    int N = piece[0].length;
	    for(int i=0; i< N; i++){
	        for(int j=i; j< N; j++){
	            diagonalSwap(piece, i, j);
	        }
	    }

	    for(int k=0; k<(N/2); k++){
	        horizontalSwap(piece, k, N-k-1);
	    }
	   return piece;
	}
	 
	 
	 
	 static boolean[][] mirroringPiece(boolean[][] piece) {		
		 int N = piece[0].length;
		 for(int k=0; k<(N/2); k++){
		        horizontalSwap(piece, k, N-k-1);
		    }
		   return piece;
	 }
	
	
	 static void diagonalSwap(boolean[][] piece, int i, int j) {
	    boolean swap;
	    swap = piece[i][j];
	    piece[i][j] = piece[j][i];
	    piece[j][i] = swap;
	}
	
	 static void horizontalSwap(boolean[][] piece, int i, int j) {
	    boolean[] swap;
	    swap = piece[i];
	    piece[i] = piece[j];
	    piece[j] = swap;
	}
	
	public void printPiece() {

		for(int i=0;i<this.getPiece()[0].length; i++) {
			for (int j=0; j< this.getPiece()[0].length;j++) {
				if (this.getPiece()[i][j])
				    System.out.print("[]");
				else System.out.print("  ");
			}
			System.out.println();
		}
			
	}


	/**
	 * @return the rotateStep
	 */
	public int getRotateStep() {
		return rotateStep;
	}


	/**
	 * @param rotateStep the rotateStep to set
	 */
	public void setRotateStep(int rotateStep) {
		this.rotateStep = rotateStep;
	}


	/**
	 * @return the mirroring
	 */
	public boolean isMirroring() {
		return mirroring;
	}


	/**
	 * @param mirroring the mirroring to set
	 */
	public void setMirroring(boolean mirroring) {
		this.mirroring = mirroring;
	}
		
	
}
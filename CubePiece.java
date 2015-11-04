package happycube;

public class CubePiece {
	
	private int ID;
	private boolean [][] piece;
	
	private int upMatchPieceID;
	private int rightMatchPieceID;
	private int downMatchPieceID;
	private int leftMatchPieceID;
	private int faceMatchPieceID;
	
	private Edge upEdge;
	private Edge rightEdge;
	private Edge downEdge;
	private Edge leftEdge;
	
	private boolean mirroring;
	private int rotateStep;
	
	private boolean upLeftCornerMatch;
	private boolean upRightCornerMatch;
	private boolean downLeftCornerMatch;
	private boolean downRightCornerMatch;
	
	public CubePiece(int ID, boolean[][] piece){
		this.ID = ID;
		this.piece = piece;
		this.upMatchPieceID = 0;
		this.rightMatchPieceID = 0;
		this.downMatchPieceID = 0;
		this.leftMatchPieceID = 0;
		this.setMirroring(false);
		this.setRotateStep(0);

		this.upEdge = new Edge(piece,ID,0,0,0);
		
		CubePiece.rotatePiece(piece);
		this.rightEdge = new Edge(piece,ID,0,0,0);
		
		CubePiece.rotatePiece(piece);
		this.downEdge = new Edge(piece,ID,0,0,0);
		
		CubePiece.rotatePiece(piece);
		this.leftEdge = new Edge(piece,ID,0,0,0);
		
		CubePiece.rotatePiece(piece);
		
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


	/**
	 * @return the upLeftCornerMatch
	 */
	public boolean isUpLeftCornerMatch() {
		return upLeftCornerMatch;
	}


	/**
	 * @param upLeftCornerMatch the upLeftCornerMatch to set
	 */
	public void setUpLeftCornerMatch(boolean upLeftCornerMatch) {
		this.upLeftCornerMatch = upLeftCornerMatch;
	}


	/**
	 * @return the upRightCornerMatch
	 */
	public boolean isUpRightCornerMatch() {
		return upRightCornerMatch;
	}


	/**
	 * @param upRightCornerMatch the upRightCornerMatch to set
	 */
	public void setUpRightCornerMatch(boolean upRightCornerMatch) {
		this.upRightCornerMatch = upRightCornerMatch;
	}


	/**
	 * @return the downLeftCornerMatch
	 */
	public boolean isDownLeftCornerMatch() {
		return downLeftCornerMatch;
	}


	/**
	 * @param downLeftCornerMatch the downLeftCornerMatch to set
	 */
	public void setDownLeftCornerMatch(boolean downLeftCornerMatch) {
		this.downLeftCornerMatch = downLeftCornerMatch;
	}


	/**
	 * @return the downRightCornerMatch
	 */
	public boolean isDownRightCornerMatch() {
		return downRightCornerMatch;
	}


	/**
	 * @param downRightCornerMatch the downRightCornerMatch to set
	 */
	public void setDownRightCornerMatch(boolean downRightCornerMatch) {
		this.downRightCornerMatch = downRightCornerMatch;
	}


	/**
	 * @return the upEdge
	 */
	public Edge getUpEdge() {
		return upEdge;
	}


	/**
	 * @param upEdge the upEdge to set
	 */
	public void setUpEdge(Edge upEdge) {
		this.upEdge = upEdge;
	}


	/**
	 * @return the rightEdge
	 */
	public Edge getRightEdge() {
		return rightEdge;
	}


	/**
	 * @param rightEdge the rightEdge to set
	 */
	public void setRightEdge(Edge rightEdge) {
		this.rightEdge = rightEdge;
	}


	/**
	 * @return the downEdge
	 */
	public Edge getDownEdge() {
		return downEdge;
	}


	/**
	 * @param downEdge the downEdge to set
	 */
	public void setDownEdge(Edge downEdge) {
		this.downEdge = downEdge;
	}


	/**
	 * @return the leftEdge
	 */
	public Edge getLeftEdge() {
		return leftEdge;
	}


	/**
	 * @param leftEdge the leftEdge to set
	 */
	public void setLeftEdge(Edge leftEdge) {
		this.leftEdge = leftEdge;
	}


	/**
	 * @return the faceMatchPieceID
	 */
	public int getFaceMatchPieceID() {
		return faceMatchPieceID;
	}


	/**
	 * @param faceMatchPieceID the faceMatchPieceID to set
	 */
	public void setFaceMatchPieceID(int faceMatchPieceID) {
		this.faceMatchPieceID = faceMatchPieceID;
	}

	public boolean matchAtUp(CubePiece matchPiece){
		return true;
	}

	public boolean matchAtRight(CubePiece matchPiece){
		return true;
	}
	
	public boolean matchAtDown(CubePiece matchPiece){
		return true;
	}
	
	public boolean matchAtLeft(CubePiece matchPiece){
		return true;
	}
}
		

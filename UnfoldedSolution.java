package happycube;

public class UnfoldedSolution {
	
	private static final int HEIGHT=20;
	private static final int WEIGHT=15;
	private static final int BASEPIECE_ID=1;

	private static boolean [][] outputForm;
	
	public UnfoldedSolution() {
		// TODO Auto-generated constructor stub

		outputForm = new boolean[HEIGHT][WEIGHT];
	}


	
	public static void putCubePiece(Cube cube) {
		 
		int N = 5 ;
			
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				outputForm[i][j+5] = cube.getCubePiece(BASEPIECE_ID).getPiece()[i][j];		
				outputForm[i][j]=cube.getCubePiece(cube.getCubePiece(BASEPIECE_ID).getLeftMatchPieceID()).getPiece()[i][j];
				outputForm[i][j+10] = cube.getCubePiece(cube.getCubePiece(BASEPIECE_ID).getRightMatchPieceID()).getPiece()[i][j];
				outputForm[i+5][j+5] = cube.getCubePiece(cube.getCubePiece(BASEPIECE_ID).getDownMatchPieceID()).getPiece()[i][j];
				outputForm[i+15][j+5] = cube.getCubePiece(cube.getCubePiece(BASEPIECE_ID).getUpMatchPieceID()).getPiece()[i][j];
				outputForm[i+10][j+5] = CubePiece.mirroringPiece(cube.getCubePiece(cube.getCubePiece(BASEPIECE_ID).getUpMatchPieceID()).getPiece())[i][j];
			}
		}
	}
	
	
	public static void printUnfoldedSolution() {

		for(int i=0;i<HEIGHT; i++) {
			for (int j=0; j< WEIGHT;j++) {
				if (outputForm[i][j])
				    System.out.print("[]");
				else System.out.print("  ");
			}
			System.out.println();
		}
			
	}
	
	
}

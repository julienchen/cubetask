package cubetask.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class CubeTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}


		hasMatch=true;
				
		for(int i=0; i<N; i++) {
			// XOR the match pairs
			matchResults[i] = basePiece.getPiece()[0][i]^matchPiece.getPiece()[N-1][i];	
			hasMatchResults[i] = matchResults[i];
			
			// The corner of Cube can not be (1,1), Otherwise is OK
			if (i==0) {		
				// has not matched piece
				if (basePiece.getLeftMatchPieceID()== 0) {
					// 0 vs 0
					if (!matchResults[i] && !basePiece.getPiece()[0][i] && !matchPiece.getPiece()[N-1][i]  
							&& (basePiece.getPiece()[1][i]||matchPiece.getPiece()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
					
				}else { //has not matched piece   1 vs matched
					if (matchPiece.getPiece()[N-1][i] && basePiece.isUpLeftCornerMatch())
						hasMatchResults[i]=false;
					
					   // 0 vs 0
					if (!matchResults[i] && !basePiece.getPiece()[0][i] && !matchPiece.getPiece()[N-1][i]  
							&& (basePiece.getPiece()[1][i]||matchPiece.getPiece()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				
				}
			}
			
			
			
			if (i==N-1) {
				// has not matched piece
				if (basePiece.getRightMatchPieceID()== 0) {
					// 0 vs 0
					if (!matchResults[i] && !basePiece.getPiece()[0][i] && !matchPiece.getPiece()[N-1][i]  
							&& (basePiece.getPiece()[1][i]||matchPiece.getPiece()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				}else { //has not matched piece   1 vs matched
					if (matchPiece.getPiece()[N-1][i] && basePiece.isUpRightCornerMatch())
						hasMatchResults[i]=false;
					
					   // 0 vs 0
					if (!matchResults[i] && !basePiece.getPiece()[0][i] && !matchPiece.getPiece()[N-1][i]  
							&& (basePiece.getPiece()[1][i]||matchPiece.getPiece()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				
				}
			}
		
			
			
			
	/*		if (i==0 || i==N-1) {
				
				// For the corner point, if base piece has already matched with another piece, the 3rd piece should not be 1
				if  ((i==0 &&  matchPiece.getPiece()[N-1][i] && basePiece.isUpLeftCornerMatch()) ||
				     (i==N-1 && matchPiece.getPiece()[N-1][i] && basePiece.isUpRightCornerMatch())) hasMatchResults[i]=false;
				
				// For the corner point, if never matched before, even both piece are 0,  we can accept if the points after them are not 1.
				if  ( (i==0 && basePiece.getLeftMatchPieceID()== 0) || 
						  (i==N-1 && basePiece.getRightMatchPieceID() == 0) ) {
					if (!matchResults[i] && !basePiece.getPiece()[0][i] && !matchPiece.getPiece()[N-1][i]  
							&& (basePiece.getPiece()[1][i]||matchPiece.getPiece()[N-2][i]) ) {
							hasMatchResults[i] = true;
					}
				}
					
				
		    } */
			
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
		stateStack.push(this);
		
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
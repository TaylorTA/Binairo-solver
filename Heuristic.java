import java.util.*;

public class Heuristic{
	
	public Heuristic() {
		
	}
	
	public int[] getMove(int heuristic, Board curr) {
		if(heuristic==1) {
			return heuristic1(curr);
		}else if(heuristic==2) {
			return heuristic2(curr);
		}else {
			return heuristic3(curr);
		}
	}
	
	public int[] heuristic1(Board curr) {
		int[] result = new int[2];
		int max = curr.getSize();
		Random rand = new Random();
		int row = rand.nextInt(max);
		int col = rand.nextInt(max);
		while (curr.getBoard()[row][col].getValue() != '_') {
			row = rand.nextInt(max);
			col = rand.nextInt(max);
		}
		result[0] = row;
		result[1] = col;
		return result;
	}
	
	public int[] heuristic2(Board curr) {
		int[] result = new int[2];
		PriorityQueue allMoves = new PriorityQueue();
		for (int i = 0; i < curr.getSize(); i++) {
			for (int j = 0; j < curr.getSize(); j++) {
				Node currNode = curr.getBoard()[i][j];
				if (currNode.getValue() == '_') {
					Board check0 = curr.clone();
					check0.setBoard('0', i, j);
					Board check1 = curr.clone();
					check1.setBoard('1', i, j);
					if (check0.Constraint3() && check1.Constraint3()) {
						currNode.setPriority(2);
					} else {
						currNode.setPriority(1);
					}
					allMoves.enQueue(currNode);
				}
			}
		}
		Node first = allMoves.deQueue();
		if (first != null) {
			result[0] = first.getRow();
			result[1] = first.getCol();
		}
		return result;
	}
	
	public int[] heuristic3(Board curr) {
		int[] result = new int[2];
		Node maxNode = null;
		int maxCount = 0;
		for(int i=0;i<curr.getSize();i++) {
			for(int j=0;j<curr.getSize();j++) {
				Node currNode = curr.getBoard()[i][j];
				if(currNode.getValue()=='_') {
					Board check0 = curr.clone();
					check0.setBoard('0',i,j);
					Board check1 = curr.clone();
					check1.setBoard('1',i,j);
					int count0 = constraintNodeNumber(check0);
					int count1 = constraintNodeNumber(check1);
					if(count0+count1>=maxCount) {
						maxNode = currNode;
						maxCount=count0+count1;
					}
				}
			}
		}
		if(maxNode!=null) {
			result[0] = maxNode.getRow();
			result[1] = maxNode.getCol();
		}
		return result;
	}


	public int constraintNodeNumber(Board curr) {
		int count = 0;
		for(int i=0;i<curr.getSize();i++) {
			for(int j=0;j<curr.getSize();j++) {
				Node currNode = curr.getBoard()[i][j];
				if(currNode.getValue()=='_') {
					Board check0 = curr.clone();
					check0.setBoard('0',i,j);
					Board check1 = curr.clone();
					check1.setBoard('1',i,j);
					if(!(check0.Constraint3()&&check1.Constraint3())) {
						count++;
					}
				}
			}
		}
		return count;
	}

}
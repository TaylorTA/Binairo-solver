import java.util.*;

public class Backtrack {

	private Stack<Board> stack;
	private Board board;
	private GUI draw;

	public Backtrack(Board initial) {
		stack = new Stack<Board>();
		board = initial;
		//draw = new GUI(board);
	}

	public void Simulation(int heuristic) throws InterruptedException {
		Heuristic h = new Heuristic();
		stack.push(board);
		int[] nextMove = null;
		int count = 0;
		while (!stack.empty()) {
			Board curr = stack.pop();
			count++;
//			draw.update(curr);
//			draw.validate();
//			draw.repaint();
//			Thread.sleep(10);
			if (!curr.isFull()) {
				nextMove = h.getMove(heuristic,curr);
				curr.setBoard('1', nextMove[0], nextMove[1]);
				if (curr.Constraint3()) {
					stack.push(curr.clone());
				}
				curr.setBoard('0', nextMove[0], nextMove[1]);
				if (curr.Constraint3()) {
					stack.push(curr.clone());
				}

			} else {
				if (curr.valid()) {
					System.out.println("************");
					curr.print();
					System.out.println("number of nodes: "+count);
					break;
				}
			}
		}
	}
	
}
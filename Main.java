import java.io.*;

public class Main{
	
	private static int size;
	protected static Board board;
	
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        
        BufferedReader input = new BufferedReader(new FileReader("test.txt"));
        try {
            String line = input.readLine();
            while (line != null) {

                line = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        board.print();
//        Board b = board.clone();
////        Backtrack backtrack = new Backtrack(board);
////        backtrack.Simulation(3);
//        forward_checking fc = new forward_checking(b);
//        fc.simulation(3);
    }
 
    
}

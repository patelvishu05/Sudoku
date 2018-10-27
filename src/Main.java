import java.util.ArrayList;
import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        Sudoku sudoku = new Sudoku();
        sudoku.parseFile();
        sudoku.printGrid();
        ArrayList<Integer> op = sudoku.checkPlace();
        for(int i: op)
            System.out.print(i);

    }
}

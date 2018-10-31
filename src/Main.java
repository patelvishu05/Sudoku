import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Purpose: To create a sudoku solver that solves the sudoku
 * puzzles, using strategy and not search or brute force.
 * To depict human like solving of Sudoku.
 *
 * The sudokuSolver method helps initializes a Sudoku and sends the input sudoku file
 * for parsing to the Sudoku class to make it a playable sudoku.
 *
 * @author  Vishalkumar Patel
 * Course:  Artificial Intelligence
 * HW:      5
 */
public class Main
{

    public static void sudokuSolver(String fileName, String outputFile)
    {
        //To send the output to specified output files instead of the Console
        PrintStream out = null;

        try
        {
            out = new PrintStream(new FileOutputStream(outputFile));
            System.setOut(out);

            Sudoku s = new Sudoku(fileName);
            int loopCounter = 1;

            while (s.emptyCells.size() != 0)
            {
                MultipleReturn m1 = new MultipleReturn();   //to support multiple return values
                ArrayList<ArrayList<Integer>> answerList = new ArrayList<ArrayList<Integer>>();
                ArrayList<Integer> eliminate = new ArrayList<Integer>();
                String emptyBox = s.pickEmptyCells();
                m1 = s.getAnswer(emptyBox);

                for (int i : m1.possibleAnswers)
                    eliminate.add(i);

                if (m1.possibleAnswers.size() == 1)
                {
                    s.insertValues(m1.possibleAnswers.get(0), m1.row, m1.col);
                    s.printGrid();
                    System.out.println("The best possible value for the below cell after\n" +
                            "eliminating the not possible ones is as follows: ");
                    System.out.println("Row [" + m1.row +"] Col [" +m1.col + "] = "+ m1.possibleAnswers.get(0));
                }

                if(m1.possibleAnswers.size()>1)
                {

                    MultipleReturn m2 = new MultipleReturn();

                    for (int i = 0; i < s.emptyCells.size() - 1; i++)
                    {
                        if (emptyBox.charAt(0) == s.emptyCells.get(i).charAt(0) )
                        {
                            m2 = s.getAnswer(s.emptyCells.get(i));
                            for (int q : m2.possibleAnswers)
                                if(eliminate.contains(q))
                                    eliminate.remove(eliminate.indexOf(q));
                        }
                    }
                    if (eliminate.size() == 1)
                    {
                        s.insertValues(eliminate.get(0), m1.row, m1.col);
                        s.printGrid();
                        System.out.println("The best possible value for the below cell after\n" +
                                "eliminating the not possible ones is as follows: ");
                        System.out.println("Row [" + m1.row +"] Col [" +m1.col + "] = "+ eliminate.get(0));
                    }
                }



            }   //end of while loop
            System.out.println("\n\nCongratulations !! The puzzle is Solved");
            out.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(out!=null)
                out = null;
        }


    }   //end method sudokuSolver



    //Main driver method that drives the program
    public static void main(String[] args)
    {
        sudokuSolver("./src/input/Grid1.txt","./src/output/output1.txt");
        sudokuSolver("./src/input/Grid2.txt","./src/output/output2.txt");
        sudokuSolver("./src/input/Grid3.txt","./src/output/output3.txt");
        sudokuSolver("./src/input/Grid4.txt","./src/output/output4.txt");
        sudokuSolver("./src/input/Grid5.txt","./src/output/output5.txt");
        sudokuSolver("./src/input/Grid6.txt","./src/output/output6.txt");
        sudokuSolver("./src/input/Grid7.txt","./src/output/output7.txt");
        sudokuSolver("./src/input/Grid8.txt","./src/output/output8.txt");
        sudokuSolver("./src/input/Grid9.txt","./src/output/output9.txt");
        sudokuSolver("./src/input/Grid10.txt","./src/output/output10.txt");

    }

}




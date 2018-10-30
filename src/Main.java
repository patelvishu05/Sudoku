import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main
{

    public static void sudokuSolver()
    {
        Sudoku s = new Sudoku("./grid1.txt");
        int loopCounter = 1;

        while (s.emptyCells.size() != 0)
        {
            MultipleReturn m1 = new MultipleReturn();
            ArrayList<ArrayList<Integer>> answerList = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> elims = new ArrayList<Integer>();
            String emptyBox = s.pickEmptyCells();
            m1 = s.getAnswer(emptyBox);
            //s.printGrid();
            //System.out.print("Empty Cells: Row["+m1.row + "]  Col[" + m1.col + "]");
            //System.out.print("\tPossible Answers: ");
            for (int i : m1.possibleAnswers)
            {
            //   System.out.print(i);
                elims.add(i);
            }
            //System.out.println();

            if (m1.possibleAnswers.size() == 1)
            {
                System.out.println("1--->>>>>>>>>>>>>>>>>>>"+m1.possibleAnswers.get(0)+m1.row+m1.col);
                s.insertValues(m1.possibleAnswers.get(0), m1.row, m1.col);
                s.printGrid();
            }

            if(m1.possibleAnswers.size()>1)
            {

                MultipleReturn m2 = new MultipleReturn();

                for (int i = 0; i < s.emptyCells.size() - 1; i++)
                {
                    if (emptyBox.charAt(0) == s.emptyCells.get(i).charAt(0) )
                    {
                        m2 = s.getAnswer(s.emptyCells.get(i));
                        //System.out.print("--->Possible Answers: " + m2.row + m2.col+"\t");
                        for (int q : m2.possibleAnswers)
                        {
                            if(m1.possibleAnswers.contains(q))
                            {
                                //System.out.print(q);
                            }

                            if(elims.contains(q))
                            {
                                elims.remove(elims.indexOf(q));
                            }
                        }

                        //System.out.println();
                    }

                }   //end of for loop

            }

            //System.out.println("Loop Counter: " + loopCounter);
            //loopCounter++;

            if (elims.size() == 1)
            {
                System.out.println("2--->>>>>>>>>>>>>>>>>>>"+elims.get(0)+m1.row+m1.col);
                s.insertValues(elims.get(0), m1.row, m1.col);
                s.printGrid();
            }

            //System.out.println(s.emptyCells.size());
        }   //end of while loop

        s.printGrid();
    }   //end method sudokuSolver



    public static void main(String[] args)
    {
        sudokuSolver();
    }
}




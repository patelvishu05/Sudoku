import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void sudokuSolver()
    {
        Sudoku s = new Sudoku();
        int loopCounter = 1;

        while (s.emptyCells.size() != 0)
        //for(int e =0 ; e <2;e++)
        {
            MultipleReturn m1 = new MultipleReturn();
            ArrayList<ArrayList<Integer>> answerList = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> elims = new ArrayList<Integer>();
            String emptyBox = s.pickEmptyCells();
            m1 = s.getAnswer(emptyBox);
            s.printGrid();
            System.out.print("Empty Cells: Row["+m1.row + "]  Col[" + m1.col + "]");
            System.out.print("\tPossible Answers: ");
            for (int i : m1.possibleAnswers)
            {
                System.out.print(i);
                elims.add(i);
            }
            System.out.println();

            if (m1.possibleAnswers.size() == 1)
            {
                s.insertValues(m1.possibleAnswers.get(0), m1.row, m1.col);
            }

            //if (m1.possibleAnswers.size() == 0) break;

            if(m1.possibleAnswers.size()>1)
            {

                MultipleReturn m2 = new MultipleReturn();

                for (int i = 0; i < s.emptyCells.size() - 1; i++)
                {
                    if (emptyBox.charAt(0) == s.emptyCells.get(i).charAt(0) )
                    {
                        m2 = s.getAnswer(s.emptyCells.get(i));
                        System.out.print("--->Possible Answers: " + m2.row + m2.col+"\t");
                        for (int q : m2.possibleAnswers)
                        {
                            if(m1.possibleAnswers.contains(q))
                            {
                                System.out.print(q);
                            }

                            if(elims.contains(q))
                            {
                                elims.remove(elims.indexOf(q));
                            }
                        }

                        System.out.println();

                        if (m2.possibleAnswers.size() == 1)
                        {
                            s.insertValues(m2.possibleAnswers.get(0), m2.row, m2.col);
                        }
                    }

                }   //end of for loop

            }

            System.out.println("Loop Counter: " + loopCounter);
            loopCounter++;

            if (elims.size() == 1)
            {
                System.out.println("***"+elims.get(0));
                s.insertValues(elims.get(0), m1.row, m1.col);
            }

            System.out.println(s.emptyCells.size());
        }   //end of while loop

        s.printGrid();
    }   //end method sudokuSolver



    public static void main(String[] args)
    {
        sudokuSolver();
    }
}


//        Sudoku sudoku = new Sudoku();
//        int iter=0;
//
//        //for (int y = 0; y < 20; y++)
//        while(sudoku.emptyCells.size()!=0)
//        {
//            iter++;
//            ArrayList<Integer> options = new ArrayList<Integer>();
//            HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
//
//            String emptyBox = sudoku.pickEmptyCells();
//            int row = Character.getNumericValue(emptyBox.charAt(1));
//            int col = Character.getNumericValue(emptyBox.charAt(2));
//
//            sudoku.printGrid();
//
//            for (int i = 1; i <= 9; i++) options.add(i);
//            System.out.println("Empty Cells: (Box)(Row)(Col) " + emptyBox);
//            ArrayList<Integer> possibleAnswers = sudoku.checkPlace(row, col, options);
//
//            System.out.println("Possible Answers: "); for (int i : possibleAnswers) System.out.print(i); System.out.println("");
//
//            if (possibleAnswers.size() == 1)
//            {
//                sudoku.insertValues(possibleAnswers.get(0), row, col);
//                sudoku.printGrid();
//            }
//            if(possibleAnswers.size() ==0) break;
//            else
//            {
//                for (int h = 0; h < possibleAnswers.size(); h++) hmap.put(possibleAnswers.get(h), 1);
//
//                for (int i = 0; i <= sudoku.emptyCells.size() - 1; i++)
//                {
//                    if (emptyBox.charAt(0) == sudoku.emptyCells.get(i).charAt(0))
//                    {
//                        ArrayList<Integer> oneToNine = new ArrayList<Integer>(); for (int k = 1; k <= 9; k++) oneToNine.add(k);
//
//                        int r = Character.getNumericValue(sudoku.emptyCells.get(i).charAt(1));
//                        int c = Character.getNumericValue(sudoku.emptyCells.get(i).charAt(2));
//
//                        ArrayList<Integer> otherCellAnswers = sudoku.checkPlace(r, c, oneToNine);
//                        System.out.print("Possible Answers: ");
//
//                        for (int in : otherCellAnswers) System.out.print(in); System.out.println("");
//                        for(int u : hmap.keySet()) hmap.put(u,hmap.get(u)+1);
//
//                        if (otherCellAnswers.size() == 1)
//                        {
//                            sudoku.insertValues(otherCellAnswers.get(0), r, c);
//                            sudoku.printGrid();
//                        }
//
//                    }
//                }
//            }
//
//            System.out.println("---->"+sudoku.emptyCells.size() + "\titeration:" + iter);
//        }   //end of while loop
//    }   //end of main method
//}   //end of Main Class

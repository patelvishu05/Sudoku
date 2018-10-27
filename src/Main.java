import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main
{
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();

        //for (int y = 0; y < 20; y++) {
        while(sudoku.emptyCells.size()!=0)
        {
            ArrayList<Integer> options = new ArrayList<Integer>();
            HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

            String emptyBox = sudoku.pickEmptyCells();
            int row = Character.getNumericValue(emptyBox.charAt(1));
            int col = Character.getNumericValue(emptyBox.charAt(2));

            sudoku.printGrid();


            for (int i = 1; i <= 9; i++) options.add(i);
            System.out.println("Empty Cells: (Box)(Row)(Col) " + emptyBox);
            ArrayList<Integer> possibleAnswers = sudoku.checkPlace(row, col, options);

            System.out.println("Possible Answers: ");
            for (int i : possibleAnswers) System.out.print(i);
            System.out.println("");

            if (possibleAnswers.size() == 1)
            {
                sudoku.insertValues(possibleAnswers.get(0), row, col);
                sudoku.printGrid();
            }
            else
            {
                for (int h = 0; h < possibleAnswers.size(); h++) hmap.put(possibleAnswers.get(h), 1);

                for (int i = 0; i <= sudoku.emptyCells.size() - 1; i++)
                {
                    if (emptyBox.charAt(0) == sudoku.emptyCells.get(i).charAt(0))
                    {
                        ArrayList<Integer> oneToNine = new ArrayList<Integer>();
                        for (int k = 1; k <= 9; k++) oneToNine.add(k);

                        int r = Character.getNumericValue(sudoku.emptyCells.get(i).charAt(1));
                        int c = Character.getNumericValue(sudoku.emptyCells.get(i).charAt(2));

                        ArrayList<Integer> otherCellAnswers = sudoku.checkPlace(r, c, oneToNine);

                        //System.out.print("Possible Answers: " + r + c + "\t");
//                        for (int j : otherCellAnswers)
//                        {
//                            for (int keys : hmap.keySet())
//                            {
//                                if (j == keys) {
//                                    int val = hmap.get(keys) + 1;
//                                    hmap.put(j, val);
//                                }
//                            }
//                            System.out.print(j);
//                        }
//                        System.out.println("");
                    }
                }
                //for (int key : hmap.keySet()) System.out.println(key + "\t" + hmap.get(key));
            }
        }
    }
}

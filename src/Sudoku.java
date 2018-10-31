import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Sudoku class has various methods that helps in sudoku solving.
 *(i: Row(ACROSS), j: Col(DOWN))
 *
 * @author  Vishalkumar Patel
 */
public class Sudoku
{
    //Class variables
    public static int[][] table;
    public static ArrayList<String> emptyCells;
    public static ArrayList<ArrayList<Integer>> gridBoxes;

    //Constructor which upon initializing class object will automatically set
    //defaults and send the received input file for parsing
    Sudoku(String fileName)
    {
        table = new int[9][9];
        gridBoxes = new ArrayList<ArrayList<Integer>>();
        this.parseFile(fileName);
        this.fillGridBoxes();
    }

    //fillGridBoxes() method helps in putting cells in the box that a
    //particular cell may be in. It can also helps maintain
    //a list of all STILL empty cells
    public void fillGridBoxes()
    {
        gridBoxes = new ArrayList<ArrayList<Integer>>();
        emptyCells= new ArrayList<String>();
        ArrayList<Integer> box= new ArrayList<>();
        for(int i=0; i < 3; i++)
            for(int j=0; j < 3; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("0"+i+j);
            }
        gridBoxes.add(box);

        box = new ArrayList<>();
        for(int i=0; i < 3; i++)
            for(int j=3; j < 6; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("1"+i+j);
            }
        gridBoxes.add(box);

        box = new ArrayList<>();
        for(int i=0; i < 3; i++)
            for(int j=6; j < 9; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("2"+i+j);
            }
        gridBoxes.add(box);

        box= new ArrayList<>();
        for(int i=3; i < 6; i++)
            for(int j=0; j < 3; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("3"+i+j);
            }
        gridBoxes.add(box);

        box = new ArrayList<>();
        for(int i=3; i < 6; i++)
            for(int j=3; j < 6; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("4"+i+j);
            }
        gridBoxes.add(box);

        box = new ArrayList<>();
        for(int i=3; i < 6; i++)
            for(int j=6; j < 9; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("5"+i+j);
            }
        gridBoxes.add(box);

        box= new ArrayList<>();
        for(int i=6; i < 9; i++)
            for(int j=0; j < 3; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("6"+i+j);
            }
        gridBoxes.add(box);

        box = new ArrayList<>();
        for(int i=6; i < 9; i++)
            for(int j=3; j < 6; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("7"+i+j);
            }
        gridBoxes.add(box);

        box = new ArrayList<>();
        for(int i=6; i < 9; i++)
            for(int j=6; j < 9; j++)
            {
                box.add(table[i][j]);
                if(table[i][j]==0)
                    emptyCells.add("8"+i+j);
            }
        gridBoxes.add(box);

    }

    //findBoxNumber() when sent its location will tell us
    //which box the cell will belong in
    public int findBoxNumber(int i, int j)
    {
        if( i>=0  && i < 3 && j>=0 && j < 3)     return 0;
        else if (i>=0 && i < 3 && j>=3 && j < 6) return 1;
        else if (i>=0 && i < 3 && j>=6 && j < 9) return 2;
        else if (i>=3 && i < 6 && j>=0 && j < 3) return 3;
        else if (i>=3 && i < 6 && j>=3 && j < 6) return 4;
        else if (i>=3 && i < 6 && j>=6 && j < 9) return 5;
        else if (i>=6 && i < 9 && j>=0 && j < 3) return 6;
        else if (i>=6 && i < 9 && j>=3 && j < 6) return 7;
        else                                     return 8;
    }

    //checkPlace() is the function which receives location of empty cell.
    //It also receives an arraylist of numbers with 1 to 9 in them.
    //The below function will check values that cannot be placed in empty cell
    //and remove them from the arraylist until just 1 value if left.
    //If we have more than 1 value for a particular cell, we skip that cell, doing nothing
    //WE are not guessing or searching here.
    public ArrayList<Integer> checkPlace(int row, int col,ArrayList<Integer> options)
    {
        int box = findBoxNumber(row,col);

        for(int i=0; i < 9; i++)
        {
            if(gridBoxes.get(box).get(i) != 0)
                if(options.contains(gridBoxes.get(box).get(i)))
                    options.remove(options.indexOf(gridBoxes.get(box).get(i)));
        }

        for(int i=0; i < 9 ; i++)
        {
            if(table[i][col]!=0)
                if(options.contains(table[i][col]))
                    options.remove(options.indexOf(table[i][col]));

            if(table[row][i]!=0)
                if(options.contains(table[row][i]))
                    options.remove(options.indexOf(table[row][i]));
        }

        return options;
    }

    public void insertValues(int value, int r, int c)
    {
        table[r][c]=value;
        fillGridBoxes();
    }

    //pickEmptyCells() help us pick a random emptycell location from the table.
    //This is exactly how humans do it..
    //N.B.: Humans do not do it sequentially
    public String pickEmptyCells()
    {
        Collections.shuffle(emptyCells);
        return emptyCells.get(emptyCells.size()-1);
    }

    //getAnswers() functions helps wrap return values into an object and send
    //it to the callee.
    public MultipleReturn getAnswer(String emptyBox)
    {
        MultipleReturn multipleReturn = new MultipleReturn();
        ArrayList<Integer> oneToNine = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) oneToNine.add(i);

        int row = Character.getNumericValue(emptyBox.charAt(1));
        int col = Character.getNumericValue(emptyBox.charAt(2));

        ArrayList<Integer> possibleAnswers = checkPlace(row, col, oneToNine);

        multipleReturn.possibleAnswers = possibleAnswers;
        multipleReturn.row = row;
        multipleReturn.col = col;
        return multipleReturn;
    }

    //parseFile() helps in parsing the file that was sent
    //to it. Parses into object and catch any exceptions if occurs
    public void parseFile(String fileName)
    {
        Scanner scan = null;
        try
        {
            scan = new Scanner(new File(fileName));
            int i = 0;
            while(scan.hasNextLine())
            {
                char[] tokens = scan.nextLine().toCharArray();
                for(int j = 0; j < 9; j++ )
                {
                    table[i][j]=Character.getNumericValue(tokens[j]);
                }
                i++;
            }
            scan.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(scan!=null)
                scan.close();
        }
    }   //end method parseFile

    //printGrid() function helps in printing grid and the values contained in
    //them visually appealing way.
    public void printGrid()
    {
        System.out.println("\n    0   1   2   3   4   5   6   7   8  ");
        for(int i=0; i < 9; i++)
        {
            System.out.println("  +---+---+---+---+---+---+---+---+---+");
            System.out.print(i);
            for(int j=0; j < 9; j++)
            {
                if(table[i][j]==0)
                    System.out.print(" |  ");
                else
                    System.out.print(" | " +table[i][j] );
            }
            System.out.print(" |\n");
        }
        System.out.println("  +---+---+---+---+---+---+---+---+---+\n\n");
    }   //end method printGrid()

}   //end class Sudoku

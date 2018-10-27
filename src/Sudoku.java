import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

//(i: Row(UP), j: Col(DOWN))
public class Sudoku
{
    private static int[][] table;
    private static ArrayList<String> emptyCells;


    Sudoku()
    {
        table = new int[9][9];
        emptyCells= new ArrayList<String>();
    }

    public ArrayList<Integer> checkPlace()
    {
        String emptyBox = pickEmptyCells();
        System.out.println("Empty Cells: " + emptyBox);
        int row = Character.getNumericValue(emptyBox.charAt(0));
        int col = Character.getNumericValue(emptyBox.charAt(1));
        ArrayList<Integer> options = new ArrayList<Integer>();
        for(int i=1; i <= 9; i++)
            options.add(i);

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

    public String pickEmptyCells()
    {
        Collections.shuffle(emptyCells);
        return emptyCells.get(emptyCells.size()-1);
    }

    public void parseFile()
    {
        Scanner scan = null;
        try
        {
            scan = new Scanner(new File("./grid1.txt"));
            int i = 0;
            while(scan.hasNextLine())
            {
                char[] tokens = scan.nextLine().toCharArray();
                for(int j = 0; j < 9; j++ )
                {
                    table[i][j]=Character.getNumericValue(tokens[j]);
                    if(table[i][j] == 0)
                        emptyCells.add(""+i+j);
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

    public void printGrid()
    {
        System.out.println("    0   1   2   3   4   5   6   7   8  ");
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
        System.out.println("  +---+---+---+---+---+---+---+---+---+");
    }

}   //end class Sudoku

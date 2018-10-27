import java.io.File;
import java.io.FileNotFoundException;
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

    public int checkPlace()
    {
        return 0;
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

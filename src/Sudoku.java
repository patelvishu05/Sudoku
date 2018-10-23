import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku
{
    private int[][] table;

    Sudoku()
    {
        this.table = new int[8][8];
    }

    public static void parseFile()
    {
        Scanner scan = null;
        try
        {
             scan = new Scanner(new File("./sudoku.txt"));
             while(scan.hasNextLine())
             {
                String line = scan.nextLine();
                System.out.println(line);
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
    }   //end method finally


}

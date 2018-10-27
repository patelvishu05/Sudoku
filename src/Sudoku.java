import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//(i: Row(ACROSS), j: Col(DOWN))
public class Sudoku
{
    public static int[][] table;
    public static ArrayList<String> emptyCells;
    public static ArrayList<ArrayList<Integer>> gridBoxes;

    Sudoku()
    {
        table = new int[9][9];
        emptyCells= new ArrayList<String>();
        gridBoxes = new ArrayList<ArrayList<Integer>>();
        this.parseFile();
        this.fillGridBoxes();
    }

    public void fillGridBoxes()
    {
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

    public ArrayList<Integer> checkPlace(int row, int col,ArrayList<Integer> options)
    {
        int box = findBoxNumber(row,col);

        //System.out.println("Box Grid number: "+box);

        for(int i=0; i < 9 ; i++)
        {
            if(table[i][col]!=0)
                if(options.contains(table[i][col]))
                    options.remove(options.indexOf(table[i][col]));

            if(table[row][i]!=0)
                if(options.contains(table[row][i]))
                    options.remove(options.indexOf(table[row][i]));
        }

        for(int i=0; i < 9; i++)
        {
            if(gridBoxes.get(box).get(i) != 0)
                if(options.contains(gridBoxes.get(box).get(i)))
                    options.remove(options.indexOf(gridBoxes.get(box).get(i)));
        }

        return options;
    }

    public void insertValues(int value, int r, int c)
    {
        table[r][c]=value;
        emptyCells.remove(""+findBoxNumber(r,c)+r+c);
        fillGridBoxes();
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
                    //if(table[i][j] == 0)
                    //    emptyCells.add(""+i+j);
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

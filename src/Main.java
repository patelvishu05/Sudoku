public class Main
{
    public static void main(String[] args)
    {
        Sudoku sudoku = new Sudoku();
        sudoku.parseFile();
        sudoku.printGrid();
        System.out.print(sudoku.pickEmptyCells());


    }
}

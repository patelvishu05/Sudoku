import java.util.ArrayList;

/**
 * This is just a class that allows objects to be wrapped
 * and support multiple returns like in python
 *
 * @author Vishalkumar Patel
 */
public class MultipleReturn
{
    public ArrayList<Integer> possibleAnswers;
    public int row;
    public int col;

    String hereInfo;
    String rowInfo  = "No other place this row";
    String colInfo  = "No other place this column";
    String blockInfo  = "No other place this block";

    //Constructor
    MultipleReturn()
    {
        this.possibleAnswers = new ArrayList<Integer>();
    }
}   //end of class MultipleReturn

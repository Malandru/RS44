package edu.itq.security.rs44;

import edu.itq.security.rs44.digraph.Digraph;

import java.util.List;
import java.util.Random;

public class Matrix
{
    public static final int ROWS = 24;
    public static final int COLUMNS = 25;
    private int size;

    private String[] array_msg;
    private int start;

    public Matrix()
    {
        this.size = ROWS * COLUMNS;
        array_msg = new String[size];
        addEmptyCells();
    }

    public void clearMatrix()
    {
        for(int i = 0; i < size; i++)
            if(canWrite(i))
                array_msg[i] = "_";
    }

    public int getStart()
    { return start; }

    public void insertMessage(String msg)
    {
        //La primer casilla no puede ser utilizada 1 + [0, 599) = [1, 600);
        start = 1 + new Random().nextInt(size - 1);
        int i = 0, p = start;
        while(i < msg.length())
        {
            if(isBlackCell(p))
            {
                p = (p + 1) % size;
                continue;
            }
            array_msg[p++] = "" + msg.charAt(i++);
        }
    }

    public String getDigraphText(int digraph)
    {
        StringBuilder text = new StringBuilder();
        for(int i = digraph; i < size; i += COLUMNS)
            if(canWrite(i) && !array_msg[i].matches("_"))
                text.append(array_msg[i]);
        return text.toString();
    }

    public void printMatrix(List<Digraph> columns, List<Digraph> rows)
    {
        for(Digraph digraph: columns)
            System.out.print(digraph.getValue() + "\t");
        System.out.println();
        for(Digraph digraph: columns)
            System.out.print(digraph.getID() + "\t");
        System.out.println();
        for(int i = 0; i < size; i++)
        {
            String letter = array_msg[i];
            if(canWrite(i))
                System.out.print(letter);
            else
                System.out.print("#");
            System.out.print("\t");
            if((i + 1) % COLUMNS == 0)
                System.out.println(rows.get(i / COLUMNS));
        }
        System.out.println();
    }

    private boolean canWrite(int index)
    {
        return array_msg[index] != null;
    }

    private boolean isBlackCell(int index)
    {
        return array_msg[index] == null;
    }

    private void addEmptyCells()
    {
        int[] positions = RandomGenerator.newCellPositions();
        for(int i: positions)
            array_msg[i] = "";
    }
}

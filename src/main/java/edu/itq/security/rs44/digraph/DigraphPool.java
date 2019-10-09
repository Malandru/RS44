package edu.itq.security.rs44.digraph;

import edu.itq.security.rs44.Matrix;
import edu.itq.security.rs44.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class DigraphPool
{
    private String[] values = { "aa", "ba", "ca", "da", "ea",
                                "ab", "bb", "cb", "db", "eb",
                                "ac", "bc", "cc", "dc", "ec",
                                "ad", "bd", "cd", "dd", "ed",
                                "ae", "be", "ce", "de", "ee" };
    private List<Digraph> columnDigraphs;
    private List<Digraph> rowDigraphs;
    private Matrix matrix;

    public DigraphPool()
    {
        columnDigraphs = new ArrayList<>();
        rowDigraphs = new ArrayList<>();
        matrix = new Matrix();
        createDigraphs();
    }

    private void createDigraphs()
    {
        Digraph digraph;

        int length = Matrix.COLUMNS;
        int[] indices = RandomGenerator.getNumbers();
        int[] ids = RandomGenerator.getNumbers();

        for(int i = 0; i < length; i++)
        {
            digraph = new Digraph(values[indices[i]], ids[i]);
            columnDigraphs.add(digraph);
        }

        length = Matrix.COLUMNS - 1;
        indices = RandomGenerator.getNumbers();
        ids = RandomGenerator.getNumbers();
        for(int i = 0; i < length; i++)
        {
            digraph = new Digraph(values[indices[i]], ids[i]);
            rowDigraphs.add(digraph);
        }
    }

    public List<Digraph> getColumnDigraphs()
    { return columnDigraphs; }

    public List<Digraph> getRowDigraphs()
    { return rowDigraphs; }

    public Matrix getMatrix()
    {
        matrix.clearMatrix();
        return matrix;
    }
}

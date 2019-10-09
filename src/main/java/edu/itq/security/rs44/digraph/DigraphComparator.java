package edu.itq.security.rs44.digraph;

import java.util.Comparator;

public class DigraphComparator implements Comparator<Digraph>
{
    private int reference;

    public DigraphComparator(int reference)
    {
        this.reference = reference;
    }

    @Override
    public int compare(Digraph ad, Digraph bd)
    {
        int a = ad.getID();
        int b = bd.getID();

        if(a >= reference)
            a = -a;
        if(b >= reference)
            b = -b;

        if(a < 0 && b < 0)
        {
            int temp = a;
            a = b;
            b = temp;
        }

        if(a < b) return -1;
        if(a > b) return 1;
        return 0;
    }
}

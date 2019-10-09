package edu.itq.security.rs44.digraph;

public class Digraph
{
    private String value;
    private Integer id;

    public Digraph(String value, Integer id)
    {
        this.value = value;
        this.id = id;
    }

    public int getID()
    {
        return this.id;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Digraph)
        {
            Digraph toCompare = (Digraph) obj;
            return this.id.equals(toCompare.id);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode()
    {
        return this.id.hashCode();
    }

    @Override
    public String toString()
    {
        return String.format("%s\t%s", id, value);
    }
}

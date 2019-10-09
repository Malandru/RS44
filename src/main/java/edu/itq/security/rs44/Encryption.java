package edu.itq.security.rs44;

import edu.itq.security.rs44.digraph.Digraph;
import edu.itq.security.rs44.digraph.DigraphComparator;
import edu.itq.security.rs44.digraph.DigraphPool;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Encryption
{
    private Matrix matrix;

    private String header;
    private String cipherText;

    private int hour;
    private int minute;
    private int letters;

    private List<Digraph> columnDigraphs;
    private List<Digraph> rowDigraphs;

    public Encryption(String msg, DigraphPool digraphPool)
    {
        columnDigraphs = digraphPool.getColumnDigraphs();
        rowDigraphs = digraphPool.getRowDigraphs();
        msg = msg.replaceAll("\\s", "x");
        initTime(msg);
        matrix = digraphPool.getMatrix();
        matrix.insertMessage(msg);
        cipherText = "NO CIPHER TEXT";
    }

    private void initTime(String msg)
    {
        LocalDateTime now = LocalDateTime.now();
        hour = now.getHour();
        minute = now.getMinute();
        letters = msg.length();
    }

    private int getOffset()
    {
        String digits = minute + "" + letters;
        return digits.chars().map(Character::getNumericValue).sum();
    }

    public void cipher()
    {
        matrix.printMatrix(columnDigraphs, rowDigraphs);

        //coordenadas donde el mensaje se empezo a escribir
        int start = matrix.getStart();
        Digraph column = columnDigraphs.get(start % Matrix.COLUMNS);
        Digraph row = rowDigraphs.get(start / Matrix.COLUMNS);
        String key = String.format("%s%02d - %s - %s%s -\n", hour, minute, letters, column.getValue(), row.getValue());

        //columna en la que se empieza a cifrar
        start += getOffset();
        Digraph cipherColumn = columnDigraphs.get(start % Matrix.COLUMNS);
        //columnas en el orden en el que se van a cifrar
        List<Digraph> digraphsOrder = new ArrayList<>(columnDigraphs);
        digraphsOrder.sort(new DigraphComparator(cipherColumn.getID()));

//        System.out.println("Offset: " + getOffset());
//        System.out.println("Cipher column: " + cipherColumn);

        //encriptacion
        StringBuilder cipherText = new StringBuilder();
        for(int i = 0; i < Matrix.COLUMNS; i++)
        {
            cipherColumn = digraphsOrder.get(i);
            cipherText.append(matrix.getDigraphText(columnDigraphs.indexOf(cipherColumn)));
        }

        this.cipherText = key + cipherText.toString().replaceAll("(.{5})", "$1 ");
    }

    public String getCipherText()
    { return cipherText; }
}

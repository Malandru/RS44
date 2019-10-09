package edu.itq.security.rs44;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator
{
    public static int[] newCellPositions()
    {
        List<Integer> positions = new ArrayList<>();
        int begin = 0, bound = 10;
        int columns = 0, row = 0;
        int random;
        while (true)
        {
            random = begin + new Random().nextInt(Matrix.COLUMNS);
            if(positions.contains(random)) continue;

            positions.add(random);
            if(++columns >= bound)
            {
                columns = 0;
                begin += Matrix.COLUMNS;
                if(++row >= Matrix.ROWS)
                    break;
            }
        }
        return positions.stream().mapToInt(i -> i).toArray();
    }

    public static int[] getNumbers()
    {
        List<Integer> positions = new ArrayList<>();
        int random;
        while (true)
        {
            random = new Random().nextInt(Matrix.COLUMNS);
            if(positions.contains(random)) continue;

            positions.add(random);
            if(positions.size() == Matrix.COLUMNS)
                break;
        }
        return positions.stream().mapToInt(i -> i).toArray();
    }

    private static void delay(long millis)
    {
        try
        { Thread.sleep(millis); }
        catch (InterruptedException e){}
    }
}

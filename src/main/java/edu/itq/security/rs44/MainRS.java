package edu.itq.security.rs44;

import edu.itq.security.rs44.digraph.DigraphPool;

import java.time.LocalDate;
import java.util.Scanner;

public class MainRS
{
    private DigraphPool digraphPool;
    private int today;

    private MainRS()
    {
        digraphPool = new DigraphPool();
        today = LocalDate.now().getDayOfYear();
    }

    private String encrypt(String message)
    {
        int day = LocalDate.now().getDayOfYear();
        if(day != today)
        {
            digraphPool = new DigraphPool();
            today = day;
        }
        Encryption encryption = new Encryption(message, digraphPool);
        encryption.cipher();
        return encryption.getCipherText();
    }

    public static void main(String[] argv)
    {
        Scanner scanner = new Scanner(System.in);
        MainRS rs44 = new MainRS();
        String message;
        while(true)
        {
            System.out.print("Mensaje: ");
            message = scanner.nextLine();
            System.out.println(rs44.encrypt(message));
        }
    }
}

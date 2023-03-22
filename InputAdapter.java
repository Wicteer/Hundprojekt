//Victor Magnusson, vima1339


import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public final class InputAdapter {


    private static ArrayList<InputStream> stream = new ArrayList<InputStream>();
    private Scanner input;


    public InputAdapter() {
        this(System.in);
    }


    public InputAdapter(InputStream sysIn) {
        if (stream.contains(sysIn)) {
            throw new IllegalStateException("Scanner already exists");
        }


        input = new Scanner(sysIn);
        stream.add(sysIn);
    }


    public String readString(String ledtext) {


        System.out.print(ledtext + " ?>");
        String first = input.nextLine();


        while (true) {
            if (first.isBlank() || first == null) {
                System.out.println("Error: input cannot be blank. \n ");
                first = input.nextLine();
            } else {
                return first.trim();
            }
        }
    }


    public int readInt(String ledtext) {
        System.out.print(ledtext + " ?>");
        int first = input.nextInt();
        input.nextLine();


        return first;
    }


    public double readDouble(String ledtext) {
        System.out.print(ledtext + " ?>");
        double first = input.nextDouble();
        input.nextLine();


        return first;
    }
}


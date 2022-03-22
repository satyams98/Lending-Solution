package com.java.io;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileInputDemo {
    public static void main(String args[]) {



        try {
            InputStream input = new FileInputStream("D:\\nucleus\\Java\\Assignments\\com\\java\\io\\input.txt");

            System.out.println("Available bytes in the file: " + input.available());


            int i =input.read();
            String s = new String();
            while (i!=-1){
                s+=(char)i;
                i= input.read();
            }

            System.out.println(s);
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}


package com.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileOutputDemo {
    public static void main(String[] args) {

        String data = "This is a line of text inside the file.";

        try {
            FileOutputStream output = new FileOutputStream("D:\\nucleus\\Java\\Assignments\\com\\java\\io\\output.txt",true);

            byte[] array = data.getBytes();
            output.write(array);
            output.close();
        }

        catch(Exception e) {
            e.getStackTrace();
        }
    }
}

import java.io.*;


public class Day4 {
    void bar() throws IOException {
        throw new IOException();
    }

    void foo() throws IOException {
        bar();
    }

    public static void main(String[] args) {
        Day4 obj = new Day4();
        try {
            obj.foo();
        } catch (IOException e) {
            System.out.println("exception handled");
        }
    }

}
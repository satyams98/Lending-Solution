class A extends Thread{
    @Override
    public void run() {
        System.out.println("A");
    }
}

public class test2 {

    A a = new A();

}

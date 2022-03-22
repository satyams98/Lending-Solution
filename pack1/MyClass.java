package pack1;

public class MyClass {
    public int add(int a, int b){
        return a+b;
    }
    protected int sub(int a, int b){
        return a-b;
    }
    int mul(int a, int b){
        return a*b;
    }

    private int div(int a, int b){
        return a/b;
    }
}

class subclass extends MyClass{
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        System.out.println(obj.add(4,5));
        System.out.println(obj.sub(9,5));
        System.out.println(obj.mul(10,20));
        //System.out.println(obj.div(6,3));
    }

}

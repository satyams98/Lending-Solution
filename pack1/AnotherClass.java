package pack1;


public class AnotherClass {
    public static void main(String[] args) {
        packSubclasss obj = new packSubclasss();

        System.out.println(obj.add(4,5));
        System.out.println(obj.sub(9,5));
        System.out.println(obj.mul(10,20));
        // System.out.println(obj.div(6,3));
    }
}
class packSubclasss extends MyClass{

}

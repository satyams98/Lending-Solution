package DataStructure;

import Bank.MyIterator;
import Customer.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Comparator;

public class LinkedList {
    Node head;
    int size=0;

    static class Node{
        Object value;
        Node next;

        Node(Object d){
            this.value = d;
            next = null;

        }
    }

    public Node insert(Object data){
        Node new_node = new Node(data);

        if(head == null){
            head = new_node;
        }
        else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new_node;
        }
        size++;
        return head;
    }

    public Node insertArray(ArrayList<? extends Object> objArray){

        objArray.forEach(obj-> head = this.insert(obj));
        return head;
    }

    public Node insertAtIndex(Object data, int index){

        if (head==null && index !=0 || index >size){
            throw new RuntimeException("Invalid Index!");
        }

        Node temp = head;
        Node new_node = new Node(data);

        if (index == 0){
            new_node.next = head;
            head = new_node;
        }
        else {

            while (index != 1) {
                temp = temp.next;
                index--;
            }
                new_node.next = temp.next;
                temp.next = new_node;

        }
        size++;
        return head;
    }

    public Node replaceAtIndex(Object data, int index){
        if (head == null || size<index){
            throw new RuntimeException("Invalid Operation");
        }
        Node temp = head;
        Node new_node = new Node(data);

        if (index== 0){
            new_node.next=head.next;
            head = new_node;
        }

        else{
            index--;
            while (index != 0){
                temp = temp.next;
                index--;
            }
            new_node.next = temp.next.next;
            temp.next = new_node;
        }

        return head;
    }

    public Node deleteObject(Object data){
        if (head == null){
            throw new RuntimeException("UnderFlow!");
        }

        if(head.value == data){
            head = head.next;
        }
        else {
            Node temp = head;
             while(temp.next!=null && temp.next.value != data){
                 temp = temp.next;
             }
             if(temp.next!=null) {
                 temp.next = temp.next.next;
                 size--;

             }
        }

        return head;
    }

    public Node deleteAtIndex(int index){

        if (head == null && index==0 || index>size){
            throw new RuntimeException("UnderFlow!");
        }
        Node temp = head;

        while (index != 0){
            temp = temp.next;
            index--;
        }

        return this.deleteObject(temp.value);
    }

    public Node deleteArray(ArrayList<? extends Object> objArray){

        objArray.forEach(obj->head = deleteObject(obj));
        return head;
    }

    public boolean contains(Object obj){

        if (head == null){
            return false;
        }

        Node temp = head;

        while(temp != null){

            if (temp.value == obj){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public class iterateList implements MyIterator {

        Node temp = head;

        @Override
        public boolean hasNext() {

            return temp == null ? false : true;
        }

        @Override
        public Object next() {
            Object t = temp.value;
            temp = temp.next;
            return t;
        }

        @Override
        public boolean remove() {
            deleteObject(temp);
            return true;
        }
    }



    public Node sort(Comparator<? > c){
        MergeSort m = new MergeSort();
        this.head = m.mergeSort(this.head, c);
        return head;

    }

    public void printArray(Node head){
        if (head==null) return;
        Node temp = head;

        while (temp!= null){
            System.out.println(((Customer)(temp.value)).customerName);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Comparator<Customer> c = (Comparator<Customer>) (o1, o2) -> o1.monthlyIncome<o2.monthlyIncome?-1:1;

        Customer c1 = new Customer();
        Customer c2 = new Customer();
        Customer c3 = new Customer();

        c1.customerId = 101;
        c1.customerName = "Satyam Singh";
        c1.dateOfBirth = LocalDate.of(2000, 9, 07);
        c1.companyNames = "Nucleus";
        c1.contactNumber = "875167125";
        c1.monthlyIncome = 150000;

        c2.customerId = 102;
        c2.customerName = "Aditya";
        c2.dateOfBirth = LocalDate.of(1999, 9, 1);
        c2.monthlyIncome = 200000;

        c3.customerId = 103;
        c3.customerName = "Shivam";
        c3.dateOfBirth = LocalDate.of(1999, 9, 1);
        c3.monthlyIncome = 100000;

        //1. Add new object
        LinkedList list = new LinkedList();
        list.insert(c1);
        System.out.println("1. Add New Object: ");
        list.printArray(list.head);

        //2. Add array of objects
        ArrayList<Customer> cust = new ArrayList<Customer>();
        cust.add(c1);
        cust.add(c2);
        list.insertArray(cust);
        System.out.println("2. Add Array of Objects: ");
        list.printArray(list.head);

        //3. Add Object At a particular Index

        list.insertAtIndex(c3, 0);
        System.out.println("3. Add Object at particular index: ");
        list.printArray(list.head);

        //4. Replace Object at particular index
        list.replaceAtIndex(c2, 0);
        System.out.println("4. Replace Object at particular index: ");
        list.printArray(list.head);

        //5.Remove an object by providing object details
        list.deleteObject(c1);
        System.out.println("5.Remove an object by providing object details: ");
        list.printArray(list.head);

        // 6.Remove an object at a particular index
        list.deleteAtIndex(0);
        System.out.println("6.Remove an object at a particular index: ");
        list.printArray(list.head);

        //7. Given array of objects remove all the objects from the list
        list.deleteArray(cust);
        System.out.println("7. Given array of objects remove all the objects from the list: ");
        System.out.println("List is now: ");
        list.printArray(list.head);

        list.insertArray(cust);

        //8. Check whether an object exists in the list
        System.out.println("8. Check whether an object exists in the list");
        System.out.println(list.contains(c2));


        //9. Iterator functionality by implementing the Iterator as an inner class
        System.out.println("Printing list with iterator: ");
        iterateList it = list.new iterateList();
        while (it.hasNext()){
            Customer temp =(Customer) it.next();
            System.out.println(temp.customerName);
            //it.remove();
        }

        //10.Provide sorting in the linked list using merge sort. The sorting has to be done in
        //such a manner that user can choose to sort using any attribute of the object.

        Comparator<Customer> comp = new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.monthlyIncome<o2.monthlyIncome?-1:1;
            }
        };

        list.insert(c3);
        System.out.println("Before Sorting wrt monthly income: ");
        iterateList it1 = list.new iterateList();
        while (it1.hasNext()){
            Customer temp =(Customer) it1.next();
            System.out.println(temp.customerName+" "+temp.monthlyIncome);
        }


        System.out.println("After Sorting wrt monthly income:");
        list.sort(comp);
        it1 = list.new iterateList();
        while (it1.hasNext()){
            Customer temp =(Customer) it1.next();
            System.out.println(temp.customerName+" "+temp.monthlyIncome);
        }
    }
}
package DataStructure;

import java.util.Comparator;

public class MergeSort {
    public LinkedList.Node mergeSort(LinkedList.Node head, Comparator<? > c){
        if(head == null || head.next== null){
            return head;
        }

        LinkedList.Node mid = mid(head);
        LinkedList.Node nextOfMid = mid.next;
        mid.next = null;
        LinkedList.Node fh = mergeSort(head, c );
        LinkedList.Node sh = mergeSort(nextOfMid, c);
        head = merge(fh, sh, (Comparator<Object>) c);
        return head;


    }

    LinkedList.Node mid(LinkedList.Node head){
        if(head==null) return head;

        LinkedList.Node slow = head;
        LinkedList.Node fast = head;

        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    LinkedList.Node merge(LinkedList.Node fh, LinkedList.Node sh, Comparator<Object> c){
        if(fh == null) return sh;
        if (sh == null) return fh;
        LinkedList.Node res = null;

        if (c.compare(fh.value,sh.value)>0){
                res = sh;
                res.next = merge(fh, sh.next, c);

        }
        else {

            res = fh;
            res.next = merge(fh.next, sh, c);
        }
        return res;
    }

}

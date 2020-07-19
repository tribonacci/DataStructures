package Ds;

import java.util.Random;

class SkipNode<E extends Comparable<E>>{

    private E data;
    private SkipNode down;
    private SkipNode right;

    SkipNode(E data) {
        this.data = data;
        this.down = null;
        this.right = null;
    }

    public SkipNode(E data, SkipNode down, SkipNode right) {
        this.data = data;
        this.down = down;
        this.right = right;
    }

    public SkipNode(E data, Ds.SkipNode right) {
        this.data = data;
        this.right = right;
    }

    void setDown(SkipNode down) {
        this.down = down;
    }

    void setRight(SkipNode right) {
        this.right = right;
    }

    E getData() {
        return data;
    }

    void setData(E data) {
        this.data = data;
    }

    SkipNode getDown() {
        return down;
    }

    SkipNode getRight() {
        return right;
    }
}

public class SkipList<E extends Comparable<E>> {

    private SkipNode<E> head;
    private E minusInfinity;
    private Random randomNum = new Random();

    private boolean flip(){
        int result = randomNum.nextInt(2);
        return result != 0;
    }

    public SkipList(E minusInfinity) {

        this.minusInfinity = minusInfinity;
        this.head = new SkipNode<E>(minusInfinity);
//        this.bottom=this.head;
    }

    public void delete( E element){
        SkipNode ele = search(this.head, element);
        SkipNode down = ele;
        while(down != null)
        {
            down = ele.getDown();
            if( ele.getRight() == null )
                clear(ele);
            else {
                exchange(ele , ele.getRight());
                ele.setRight(ele.getRight().getRight());
                ele.setDown(ele.getRight().getDown());
                clear(ele.getRight());
            }
            ele = down;
        }
    }

    @SuppressWarnings("unchecked")
    private void exchange(SkipNode ele, SkipNode right) {
        E temp = (E) ele.getData();
        ele.setData(right.getData());
        right.setData(temp);
    }

    @SuppressWarnings("unchecked")
    private void clear(SkipNode ele){
        if(ele != null){
            ele.setRight(null);
            ele.setDown(null);
            ele.setData(null);
        }
    }

    @SuppressWarnings("unchecked")
    private SkipNode search(SkipNode head, E element){
        if(head == null)
            return null;

        if(head.getData() == element)
            return head;

        if(head.getRight() != null && head.getRight().getData().compareTo(element) <= 0)
            return search(head.getRight() , element);

        return search(head.getDown(),element);
    }

    @SuppressWarnings("unchecked")
    private SkipNode insert(SkipNode head, E element){

        if(head.getDown() == null){
            if ( head.getRight() == null || head.getRight().getData().compareTo(element) > 0) {
                SkipNode newElement = singleInsertToRight(head, element);
                if (flip())
                    return newElement;
                else
                    return null;
            }
            else if(head.getRight().getData().compareTo(element) < 0){
                return insert(head.getRight() , element);
            }
            else {
                return singleInsertToRight(head,element);
            }
        }
        else{
            SkipNode res ;
            if(head.getRight() != null && (head.getRight().getData().compareTo(element) < 0))
                res =  insert(head.getRight() ,element);
            else
                res = insert(head.getDown(), element);

            if( res != null ){
                SkipNode newElement = singleInsertToRight(head, element);
                newElement.setDown(res);

                if(flip())
                    return newElement;
                else
                    return null;
            }
            return res;
        }
    }

    @SuppressWarnings("unchecked")
    private SkipNode singleInsertToRight(SkipNode pos, E element){
        SkipNode newElement = new SkipNode(element);
        SkipNode next = pos.getRight();
        newElement.setRight(next);
        pos.setRight(newElement);
        return newElement;
    }

    @SuppressWarnings("unchecked")
    public void insert(E element){
        SkipNode res = insert(this.head, element);

        // Creating new level
        if(res != null) {
            SkipNode newElement = new SkipNode(element);
            newElement.setDown(res);
            SkipNode<E> newHead = new SkipNode<E>(this.minusInfinity);
            newHead.setRight(newElement);
            newHead.setDown(head);
            head = newHead;
        }

    }
}
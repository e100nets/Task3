package ilya.ignatov;

public class Queue_based_on_List {
    private class Node {
        private Node next;
        private String value;

        private Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add (String value) {
        if (size == 0)
            head = tail = new Node(value, null);
        else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
        size++;
    }

    public String remove () {
        isEmpty();
        Node current = head;
        String returnedValue = head.value;
        if (size == 1)
            clear();
        else {
            head = head.next;
            size--;
        }
            return returnedValue;

    }
        public String element () {
            isEmpty();
            return head.value;
        }

    public void duplicate () {
        final int count = size;
        for (int i = 0; i< count; i++){
            add(element());
            add(remove());
        }
    }

        public void fromArray (String[]array){
            for (String s : array) {
                add(s);
            }
        }

        public String[] toArray () {
            String[] array = new String[size];
            Node current = head;
            for (int i = 0; i < size; i++) {
                array[i] = current.value;
                current = current.next;
            }
            return array;
        }
    }

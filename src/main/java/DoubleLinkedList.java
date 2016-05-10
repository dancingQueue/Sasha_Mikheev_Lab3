import interfaces.TwoWayIterable;
import interfaces.TwoWayIterator;

import java.util.Iterator;

/**
 * Created by alexandermiheev on 05.05.16.
 */
public class DoubleLinkedList<Item extends Comparable<? super Item>> implements Iterable<Item>, TwoWayIterable<Item> {

    private int size;
    private Node first;


    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public DoubleLinkedList() {
        first = new Node();
    }

    public Iterator<Item> iterator() {
        Iterator<Item> tempIterator = new Iterator<Item>() {

            private int currentIndex = 0;
            private Node currentNode = first;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Item next() {
                Item returnValue = currentNode.item;
                currentNode = currentNode.next;
                currentIndex++;
                return returnValue;
            }

            @Override
            public void remove() {
                return;
            }


        };
        return tempIterator;
    }

    public TwoWayIterator<Item> getTwoWayIterator() {
        return new DoubleLinkedListIterator();
    }

    private class DoubleLinkedListIterator implements TwoWayIterator<Item> {
        private int currentIndex = 0;
        private Node currentNode = first;

        public boolean hasNext() {
            return currentIndex < size;
        }

        public boolean hasPrevious() {
            return currentIndex >= 0;
        }

        public Item next() {
            Item returnValue = currentNode.item;
            currentNode = currentNode.next;
            currentIndex++;
            return returnValue;
        }

        public Item previous() {
            Item returnValue = currentNode.item;
            currentNode = currentNode.previous;
            currentIndex--;
            return returnValue;
        }
        
        public void remove() {
            return;
        }
    }

    public void sort() {

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                Node currentNode = Entry(j);

                if (currentNode.item.compareTo(currentNode.next.item) > 0) {
                    Item tempValue = currentNode.item;
                    currentNode.item = currentNode.next.item;
                    currentNode.next.item = tempValue;
                }
            }
        }

    }

    public void addFirst(Item item) {
        if (size == 0) {
            first.item = item;
            first.next = first;
            first.previous = first;
        } else {
            Node oldFirstNode = first;
            Node lastNode = first.previous;

            Node newNode = new Node();

            lastNode.next = newNode;
            oldFirstNode.previous = newNode;

            newNode.next = oldFirstNode;
            newNode.previous = lastNode;
            newNode.item = item;

            first = newNode;
        }
        size++;

    }

    private Node Entry(int index) {
        Node resultNode = first;

        for (int i = 0; i < index; i++) {
            resultNode = resultNode.next;
        }

        return resultNode;

    }

    public Item get(int index) {
        return Entry(index).item;
    }

    public void add(int index, Item item) {
        if (index == 0) {
            addFirst(item);
        } else if (index < 0 || index > size) {
            return;
        } else {
            Node oldNode = Entry(index);
            Node beforeOldNode = oldNode.previous;

            Node newNode = new Node();
            newNode.item = item;
            newNode.next = oldNode;
            newNode.previous = beforeOldNode;

            oldNode.previous = newNode;
            beforeOldNode.next = newNode;
            size++;

        }
    }

    public void testPrint() {
        Node test = first;
        for (int i = 0; i < size; i++) {
            System.out.println(test.item);
            test = test.next;
        }
    }

    public void removeFirst() {
        if (size == 0) {
            return;
        } else if (size == 1) {
            size = 0;
            first = null;
        } else {
            Node lastNode = first.previous;
            Node secondNode = first.next;

            lastNode.next = secondNode;
            secondNode.previous = lastNode;

            first = secondNode;
            size--;
        }
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            return;
        } else if (index == 0) {
            removeFirst();
        } else {
            Node nodeToDelete = Entry(index);

            Node previousNode = nodeToDelete.previous;
            Node nextNode = nodeToDelete.next;

            previousNode.next = nextNode;
            nextNode.previous = previousNode;
            size--;
        }
    }

    public void add(Item item) {
        if (size == 0) {
            addFirst(item);
            return;
        } else {
            Node tempNode = first.previous;
            Node newNode = new Node();

            first.previous = newNode;
            tempNode.next = newNode;

            newNode.item = item;
            newNode.next = first;
            newNode.previous = tempNode;
            size++;
        }
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }



}

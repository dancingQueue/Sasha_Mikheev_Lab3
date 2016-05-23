import interfaces.TwoWayIterable;
import interfaces.TwoWayIterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by alexandermiheev on 05.05.16.
 */
public class DoubleLinkedList<Item extends Comparable<? super Item>> implements Iterable<Item>, TwoWayIterable<Item> {

    private int size;
    private Node first;
    private int modCount;

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
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Item next() {
                checkForComodification();
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                Item returnValue = currentNode.item;
                currentNode = currentNode.next;
                currentIndex++;
                return returnValue;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            final void checkForComodification() {
                if (modCount != expectedModCount)
                    throw new ConcurrentModificationException();
            }


        };
        return tempIterator;
    }

    public <OUT extends Comparable<? super OUT>> DoubleLinkedList<OUT> map(Function<Item, OUT> currentFunctionObject) {
        DoubleLinkedList<OUT> newList = new DoubleLinkedList<>();

        Node tempNode = first;
        for (int index = 0; index < size; index++) {
            OUT tempValue = currentFunctionObject.apply(tempNode.item);
            tempNode = tempNode.next;
            newList.add(tempValue);
        }
        return newList;
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
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            Item returnValue = currentNode.item;
            currentNode = currentNode.next;
            currentIndex++;
            return returnValue;
        }

        public Item previous() {
            if (!hasPrevious()) {
                throw new IndexOutOfBoundsException();
            }
            Item returnValue = currentNode.item;
            currentNode = currentNode.previous;
            currentIndex--;
            return returnValue;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public void sort() {
        modCount++;
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
        modCount++;
    }

    private Node Entry(int index) {
        Node resultNode = first;

        for (int i = 0; i < index; i++) {
            resultNode = resultNode.next;
        }

        return resultNode;

    }

    private boolean isIndexInRange(int index) {
        return (index >= 0 && index < size);
    }
    private boolean isIndexInRangeForAdding(int index) {return (index >= 0 && index <= size);}

    public Item get(int index) {
        if (!isIndexInRange(index)) {
            throw new IndexOutOfBoundsException();
        }
        return Entry(index).item;
    }

    public void add(int index, Item item) {
        if (!isIndexInRangeForAdding(index)) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(item);

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
            modCount++;
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
        if (!isIndexInRange(0)) {
            throw new IndexOutOfBoundsException();
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
        modCount++;
    }

    public void delete(int index) {
        if (!isIndexInRange(index)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            removeFirst();
        } else {
            Node nodeToDelete = Entry(index);

            Node previousNode = nodeToDelete.previous;
            Node nextNode = nodeToDelete.next;

            previousNode.next = nextNode;
            nextNode.previous = previousNode;
            size--;
            modCount++;
        }
    }

    public void add(Item item) {
        if (size == 0) {
            addFirst(item);
        } else {
            Node tempNode = first.previous;
            Node newNode = new Node();

            first.previous = newNode;
            tempNode.next = newNode;

            newNode.item = item;
            newNode.next = first;
            newNode.previous = tempNode;
            size++;
            modCount++;
        }
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }



}

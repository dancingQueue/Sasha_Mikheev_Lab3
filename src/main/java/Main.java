import interfaces.TwoWayIterator;

import java.util.*;

/**
 * Created by alexandermiheev on 06.05.16.
 */
public class Main {


    public static void testRoutine() {
        DoubleLinkedList<Integer> testList = new DoubleLinkedList<>();


        System.out.println("Adding 0 to 4 elements in the beginning of the list");
        for (int i = 0; i < 5; i++) {
            testList.addFirst(i);

            testList.testPrint();
            System.out.println("Added " + (i + 1) + " elements");
        }

        System.out.println("Adding 5 to 9 elements in the list");
        for (int i = 5; i < 10; i++) {
            testList.add(i);

            testList.testPrint();
            System.out.println("Added " + (i + 1) + " elements");
        }

        System.out.println("Add element 100500 on the position 2 (index = 1)");
        testList.add(1, 100500);
        testList.testPrint();

        System.out.println("Add element -100500 in the beginning (index = 0)");
        testList.add(0, -100500);
        testList.testPrint();

        System.out.println("Add element 200500 before the end");
        System.out.println("Size is " + testList.getSize());
        testList.add(testList.getSize() - 1, 200500);
        testList.testPrint();

        System.out.println("Add element 300500 in the end");
        testList.add(testList.getSize(), 300500);
        testList.testPrint();
        System.out.println();
        System.out.println("Testing delete method");
        System.out.println("Deleting elements from 3 to 8 index");
        for (int i = 3; i <= 8; i++) {
            System.out.println("Deleted element at the index " + i);
            testList.delete(i);
            testList.testPrint();

        }

        System.out.println("Deleting element in the beginning");
        testList.removeFirst();
        testList.testPrint();

        System.out.println("Adding element 300500 in the beginning");
        testList.add(testList.getSize(), 300500);
        testList.testPrint();

        System.out.println("Test sorting method");
        testList.sort();
        testList.testPrint();

        System.out.println("Testing for-each iteration");

        for (Integer key: testList) {
            System.out.println(key + " iterate inside for-each");
        }

        System.out.println("Testing two way iterator");
        TwoWayIterator<Integer> a = testList.getTwoWayIterator();

        System.out.println("Run forward for 4 elements");

        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());

        System.out.println("Run backwards");
        while (a.hasPrevious()) {
            System.out.println(a.previous());
        }

    }
    public static void fillListWithElements(DoubleLinkedList<Integer> list, int sizeOfTheList) {
        for (int i = 0; i < sizeOfTheList; i++) {
            list.add(i);
        }
    }

    public static void testConcurrencyModificationException(int size) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        fillListWithElements(list, size);

        try {
            for (Integer value: list) {
                list.add(value);
            }
        } catch (ConcurrentModificationException e) {
            System.out.print("You can't modify the list during iteration");
        }

    }

    public static void testMapMethod(int size) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        fillListWithElements(list, size);

        DoubleLinkedList<String> newList = list.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return integer.toString() + " we finally map our list! GRATZ!";
            }
        });

        newList.testPrint();
    }



    public static void main(String[] args) {

     //   testRoutine();
     //   int sizeOfTheList = 10;
     //   testConcurrencyModificationException(sizeOfTheList);
     //   testMapMethod(sizeOfTheList);
        HashMap<Integer, String> a = new HashMap<>();
        a.put(null, "42");
        System.out.println("got it");
        HashSet<Integer> integerHashSet = new HashSet<>();
        integerHashSet.add(null);
        System.out.println("got it hashSet");

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(null);
        System.out.println("got it arrayList");

        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        integerLinkedList.add(null);
        System.out.println("got it linkedList");

        LinkedHashSet<Integer> integerLinkedHashSet = new LinkedHashSet<>();
        integerLinkedHashSet.add(null);
        System.out.println("got it linkedHashSet");

        //TreeSet<Integer> integerTreeSet = new TreeSet<>();
        //integerTreeSet.add(null);
        //TreeMap<Integer, Integer> integerIntegerTreeMap = new TreeMap<>();
        //integerIntegerTreeMap.put(null, 42);
        //System.out.println("got it TreeMap");



    }
}

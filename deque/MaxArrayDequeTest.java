package deque;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {


    /* Customized comparator that compares two integers. Return 1 if o1 is greater than o2.
    Return 0 if o1 is equal to o2. Return -1 if o1 is less than o2. */
    private class intComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            int compNum = 0;
            compNum = o1 < o2 ? -1 : 1;
            compNum = o1 == o2 ? 0 : compNum;
            return compNum;
        }
    }

    /* Customized comparator that compares two strings. Return 1 if o1 is greater than o2.
    Return 0 if o1 is equal to o2. Return -1 if o1 is less than o2. */
    private class strComp implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /* Customized comparator that compares two doubles. Return 1 if o1 is greater than o2.
    Return 0 if o1 is equal to o2. Return -1 if o1 is less than o2. */
    private class douComp implements Comparator<Double> {

        @Override
        public int compare(Double o1, Double o2) {
            int compNum = 0;
            compNum = o1 < o2 ? -1 : 1 ;
            compNum = o1 == o2 ? 0 : compNum;
            return compNum;
        }
    }

    /* This is a comparator that takes in a double and only returns 0*/
    private class falseDouComp implements Comparator<Double> {

        @Override
        public int compare(Double o1, Double o2) {
            return 0;
        }
    }

    /* Customized comparator that compares two booleans. True is by default the maximum value.
    Return 1 if both o1 and o2 is false, or o1 is true. Return 0 if o1 is false but o2 is true. */
    private class boolComp implements Comparator<Boolean> {

        @Override
        public int compare(Boolean o1, Boolean o2) {
            if (o1 == true)
                return 1;
            else return o1 == o2 ? 1 : 0;
        }
    }



        @Test
        /** Adds a few things to the list, checking isEmpty() and size() are LLD,
         * finally printing the results.
         *
         * && is the "and" operation. */
        public void addIsEmptySizeTest() {

            Comparator comp = new strComp();

            MaxArrayDeque<String> mad1 = new MaxArrayDeque<String>(comp);

            assertTrue("A newly initialized adeque should be empty", mad1.isEmpty());
            mad1.addFirst("front");

            // The && operator is the same as "and" in Python.
            // It's a binary operator that returns true if both arguments true, and false otherwise.
            assertEquals(1, mad1.size());
            assertFalse("mad1 should now contain 1 item", mad1.isEmpty());

            mad1.addLast("middle");
            assertEquals(2, mad1.size());

            mad1.addLast("back");
            assertEquals(3, mad1.size());

            System.out.println("Printing out deque: ");
            mad1.printDeque();
            System.out.println(mad1.max());

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        Comparator comp = new intComp();

        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<Integer>(comp);
        // should be empty
        assertTrue("mad1 should be empty upon initialization", mad1.isEmpty());

        mad1.addFirst(10);
        // should not be empty
        assertFalse("mad1 should contain 1 item", mad1.isEmpty());

        mad1.removeFirst();
        // should be empty
        assertTrue("mad1 should be empty after removal", mad1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        Comparator comp = new intComp();

        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(comp);
        mad1.addFirst(3);

        mad1.removeLast();
        mad1.removeFirst();
        mad1.removeLast();
        mad1.removeFirst();

        int size = mad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create MaxArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        Comparator comp1 = new strComp();
        Comparator comp2 = new douComp();
        Comparator comp3 = new boolComp();

        MaxArrayDeque<String>  mad1 = new MaxArrayDeque<String>(comp1);
        MaxArrayDeque<Double>  mad2 = new MaxArrayDeque<Double>(comp2);
        MaxArrayDeque<Boolean> mad3 = new MaxArrayDeque<Boolean>(comp3);

        mad1.addFirst("string");
        mad2.addFirst(3.14159);
        mad3.addFirst(true);

        String s = mad1.removeFirst();
        double d = mad2.removeFirst();
        boolean b = mad3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty MaxArrayDeque. */
    public void emptyNullReturnTest() {

        Comparator comp = new intComp();
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<Integer>(comp);

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, mad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, mad1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is LLD. */
    public void bigadequeTest() {

        Comparator comp = new intComp();

        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<Integer>(comp);
        for (int i = 0; i < 1000000; i++) {
            mad1.addLast(i);
        }

        assertEquals("Max is wrong", 999999, (int) mad1.max());


        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) mad1.removeFirst(), 0.0);
        }

        assertEquals("Max is wrong", 999999, (int) mad1.max());


        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) mad1.removeLast(), 0.0);
        }

    }

    @Test
    /* Add different items to the deque and see if the max() method would update. */
    public void maxTest() {
        Comparator comp1 = new strComp();
        Comparator comp2 = new douComp();
        Comparator comp3 = new boolComp();

        MaxArrayDeque<String>  mad1 = new MaxArrayDeque<String>(comp1);
        MaxArrayDeque<Double>  mad2 = new MaxArrayDeque<Double>(comp2);
        MaxArrayDeque<Boolean> mad3 = new MaxArrayDeque<Boolean>(comp3);

        mad1.addFirst("stringg");
        mad2.addFirst(3.14159);
        mad3.addFirst(false);

        mad1.addFirst("string");
        mad2.addFirst(2.55);
        mad3.addFirst(false);

        mad1.addFirst("apple");
        mad2.addFirst(3.14);
        mad3.addFirst(false);

        assertEquals("Max is wrong", "stringg", mad1.max());
        assertEquals("Max is wrong", 3.14159, (double) mad2.max(), 0);
        assertEquals("Max is wrong", false, mad3.max());

        mad1.addLast("zoo");
        mad2.addLast(5.55);
        mad3.addLast(true);

        assertEquals("Max is wrong", "zoo", mad1.max());
        assertEquals("Max is wrong", 5.55, (double) mad2.max(), 0);
        assertEquals("Max is wrong", true, mad3.max());

        mad1.addFirst("zwang");
        mad2.addFirst(5.556);
        mad3.addFirst(false);

        assertEquals("Max is wrong", "zwang", mad1.max());
        assertEquals("Max is wrong", 5.556, (double) mad2.max(), 0);
        assertEquals("Max is wrong", true, mad3.max());

    }

    @Test
    /*Test if the method max(Comparator c) is working, i.e. taking in the correct comparator*/
    public void comparatorInsideMaxTest() {
        Comparator comp1 = new falseDouComp();
        Comparator comp2 = new douComp();
        MaxArrayDeque<Double>  mad1 = new MaxArrayDeque<Double>(comp1);

        mad1.addFirst(3.14159);
        mad1.addFirst(2.55);
        mad1.addFirst(3.14);
        mad1.addLast(5.55);

        assertEquals("Should return the first item in the deque", 3.14, (double) mad1.max(), 0);

        assertEquals("Max(Comparator) is wrong", 5.55, (double) mad1.max(comp2), 0);

        mad1.addFirst(5.556);

        assertEquals("Max(Comparator) is wrong", 5.556, (double) mad1.max(comp2), 0);

        mad1.addFirst(-5.2);

        assertEquals("Should return the first item in the deque", -5.2, (double) mad1.max(), 0);

    }

    @Test
    /* Randomized test for the add, remove, and get methods. Compares LinkedListDeque with MaxArrayDeque.  */
    public void randomizedTest() {
        LinkedListDeque<Integer> LLD = new LinkedListDeque<>();
        
        Comparator comp = new intComp();
        MaxArrayDeque<Integer> AD = new MaxArrayDeque<>(comp);

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                LLD.addLast(randVal);
                AD.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
//                LLD.printDeque();
//                AD.printDeque();
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                LLD.addFirst(randVal);
                AD.addFirst(randVal);
//                System.out.println("addFirst(" + randVal + ")");
//                LLD.printDeque();
//                AD.printDeque();
            } else if (operationNumber == 2) {
                // size
                int size = LLD.size();
                int ADSize = AD.size();
//                System.out.println("size: " + size);
                assertEquals("Size are not the same", size, ADSize);
            } else if (operationNumber == 3 && LLD.size() > 0) {
                // removeLast
                int lastRemoved = LLD.removeLast();
                int ADLastRemoved = AD.removeLast();
//                System.out.println("LLD removeLast is: " + lastRemoved);
//                System.out.println("AD removeLast is: " + ADLastRemoved);
//                LLD.printDeque();
//                AD.printDeque();
                assertEquals("Last removed are not the same", lastRemoved, ADLastRemoved);
            } else if (operationNumber == 4 && LLD.size() > 0) {
                // removeFirst
                int firstRemoved = LLD.removeFirst();
                int ADFirstRemoved = AD.removeFirst();
//                System.out.println("LLD removeFirst is: " + firstRemoved);
//                System.out.println("AD removeFirst is: " + ADFirstRemoved);
//                LLD.printDeque();
//                AD.printDeque();
                assertEquals("First removed are not the same", firstRemoved, ADFirstRemoved);
            } else if (operationNumber == 5 && LLD.size() > 0) {
                // get
                int index = StdRandom.uniform(0, LLD.size());
                int get = LLD.get(index);
                int ADGet = AD.get(index);
//                System.out.println("LLD get is: " + get);
//                System.out.println("AD get is: " + ADGet);
                assertEquals("Get i are not the same", get, ADGet);
            }

        }
    }
    
}

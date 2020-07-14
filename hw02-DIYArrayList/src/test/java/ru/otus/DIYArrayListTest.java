package ru.otus;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DIYArrayListTest {

    @Test
    public void successfulAddingManyValuesToCollection() throws Exception {
        final List<Integer> intValues = new DIYArrayList<>();
        intValues.add(1);
        intValues.add(2);
        intValues.add(3);
        intValues.add(4);
        intValues.add(5);
        intValues.add(6);
        intValues.add(7);
        intValues.add(8);
        intValues.add(9);
        intValues.add(10);
        intValues.add(11);
        intValues.add(12);
        intValues.add(13);
        intValues.add(14);
        intValues.add(15);
        intValues.add(16);
        intValues.add(17);
        intValues.add(18);
        intValues.add(19);
        intValues.add(20);
        intValues.add(21);
        assertEquals(21, intValues.size());
    }

    @Test
    public void successfulAddingAnArrayToCollection() throws Exception {
        final List<Integer> intValues = new DIYArrayList<>();
        final Integer[] numbers = { 5, 9, 1, 8, -3, 22, 89, -35 };
        final boolean isAdded = Collections.addAll(intValues, numbers);
        assertEquals(8, intValues.size());
        assertTrue(isAdded);
    }

    @Test
    public void successfulAddingOneValueToCollection() throws Exception {
        final List<Integer> intValues = new DIYArrayList<>();
        final boolean isAdded = Collections.addAll(intValues, 37);
        assertEquals(1, intValues.size());
        assertTrue(isAdded);
    }

    @Test
    public void successfulAddingValuesToCollection() throws Exception {
        final List<Integer> intValues = new DIYArrayList<>();
        final boolean isAdded = Collections.addAll(intValues, 1, 2, 3);
        assertEquals(3, intValues.size());
        assertTrue(isAdded);
    }

    @Test
    public void successfulSortingOfCollectionInAscOrder() throws Exception {
        final List<Integer> intValues = new DIYArrayList<>();
        intValues.add(12);
        intValues.add(-6);
        intValues.add(0);
        intValues.add(6);
        intValues.add(2);
        intValues.add(-99);
        Collections.sort(intValues);
        for (int i = 1; i < intValues.size(); i++) {
            assertTrue(intValues.get(i) > intValues.get(i - 1));
        }
    }

    @Test
    public void successfulSortingOfCollectionInDescOrder() throws Exception {
        final List<Integer> intValues = new DIYArrayList<>();
        intValues.add(38);
        intValues.add(-3);
        intValues.add(7);
        intValues.add(1);
        intValues.add(4);
        intValues.add(-200);
        Collections.sort(intValues, (i1, i2) -> i2.compareTo(i1));
        for (int i = 1; i < intValues.size(); i++) {
            assertTrue(intValues.get(i) < intValues.get(i - 1));
        }
    }

    @Test
    public void successfulCopyingOfCollection() throws Exception {
        final List<Integer> values1 = new DIYArrayList<>();
        values1.add(38);
        values1.add(-3);
        values1.add(7);
        final List<Integer> values2 = new DIYArrayList<>();
        values2.add(1);
        values2.add(-9);
        values2.add(77);
        Collections.copy(values2, values1);
        for (int i = 0; i < values2.size(); i++) {
            assertEquals(values2.get(i), values1.get(i));
        }
    }

    @Test
    public void successfulAddingCollection() throws Exception {
        final List<Integer> values = new DIYArrayList<>();
        values.add(2020);
        final List<Integer> collection = Arrays.asList(2021, 2022);
        values.addAll(collection);
        assertEquals(3, values.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void gettingAnElementWithInvalidLargeIndex() {
        final List<Integer> values = new DIYArrayList<>();
        values.add(21);
        values.add(22);
        int value = values.get(values.size() + 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void gettingAnElementWithNegativeIndex() {
        final List<Integer> values = new DIYArrayList<>();
        values.add(1);
        values.add(0);
        int value = values.get(-1);
    }
}
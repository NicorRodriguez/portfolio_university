package nrodriguez;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest 
{
    private int[] sorted_array = {1, 2, 3, 4, 5, 6};

    @Test
    public void testOrdenarPorInsercion()
    {
        int[] array = {5, 2, 4, 6, 1, 3};
        Sorting.ordenarPorInsercion(array);

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], sorted_array[i]);
        }
    }

    @Test
    public void testOrdenarPorShellSort()
    {
        int[] array = {5, 2, 4, 6, 1, 3};
        Sorting.ordenarPorShellSort(array);

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], sorted_array[i]);
        }
    }

    @Test
    public void testOrdenarPorBurbuja()
    {
        int[] array = {5, 2, 4, 6, 1, 3};
        Sorting.ordenamientoBurbuja(array);

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], sorted_array[i]);
        }
    }

    @Test
    public void testOrdenarPorQuickSort()
    {
        int[] array = {5, 2, 4, 6, 1, 3};
        Sorting.ordenamientoQuickSort(array, 0, array.length-1);

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], sorted_array[i]);
        }
    }

    @Test
    public void testOrdenarPorHeapSort()
    {
        int[] array = {5, 2, 4, 6, 1, 3};
        Sorting.ordenarPorHeap(array);

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], sorted_array[i]);
        }
    }
}

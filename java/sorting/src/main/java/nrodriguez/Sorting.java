package nrodriguez;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorting implements ISorting {

    public static void ordenarPorInsercion(int[] arreglo) {
        for (int i = 1; i < arreglo.length; i++) {
            int actual = arreglo[i];
            int j = i - 1;

            while (j >= 0 && arreglo[j] > actual) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }

            arreglo[j + 1] = actual;
        }
    }

    public static void ordenarPorShellSort(int[] arreglo) {
        int n = arreglo.length;

        // Iniciar con un gap grande y reducirlo en cada iteración
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Hacer un ordenamiento por inserción para este gap
            for (int i = gap; i < n; i++) {
                int temp = arreglo[i];
                int j;

                for (j = i; j >= gap && arreglo[j - gap] > temp; j -= gap) {
                    arreglo[j] = arreglo[j - gap];
                }

                arreglo[j] = temp;
            }
        }
    }

    public static void ordenamientoBurbuja(int[] arreglo) {
        int n = arreglo.length;
        boolean intercambio;

        do {
            intercambio = false;
            for (int i = 0; i < n - 1; i++) {
                if (arreglo[i] > arreglo[i + 1]) {
                    int temp = arreglo[i];
                    arreglo[i] = arreglo[i + 1];
                    arreglo[i + 1] = temp;
                    intercambio = true;
                }
            }
        } while (intercambio);
    }


    public static void ordenamientoQuickSort(int[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particion(arreglo, inicio, fin);

            ordenamientoQuickSort(arreglo, inicio, indicePivote - 1);
            ordenamientoQuickSort(arreglo, indicePivote + 1, fin);
        }
    }

    private static int particion(int[] arreglo, int inicio, int fin) {
        int pivote = arreglo[fin];
        int i = (inicio - 1);

        for (int j = inicio; j < fin; j++) {
            if (arreglo[j] <= pivote) {
                i++;

                int temp = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = temp;
            }
        }

        int temp = arreglo[i + 1];
        arreglo[i + 1] = arreglo[fin];
        arreglo[fin] = temp;

        return i + 1;
    }

    public static void ordenarPorHeap(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            DesplazaElemento(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            DesplazaElemento(arr, i, 0);
        }
    }

    static void DesplazaElemento(int[] arr, int n, int i) {
        int mayor = i;
        int izquierda = 2 * i + 1;
        int derecha = 2 * i + 2;

        if (izquierda < n && arr[izquierda] > arr[mayor])
            mayor = izquierda;

        if (derecha < n && arr[derecha] > arr[mayor])
            mayor = derecha;

        if (mayor != i) {
            int swap = arr[i];
            arr[i] = arr[mayor];
            arr[mayor] = swap;

            DesplazaElemento(arr, n, mayor);
        }
    }
}

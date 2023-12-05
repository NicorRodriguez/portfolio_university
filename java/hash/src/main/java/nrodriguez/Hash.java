package nrodriguez;

import java.util.LinkedList;

public class Hash implements IHash {

    int TAMANIOTABLA = 223;
    LinkedList<Integer>[] tabla = new LinkedList[TAMANIOTABLA];
    int EMPTY = -1;

    public Hash() {
        for (int i = 0; i < TAMANIOTABLA; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    public int buscar(int unaClave) {
        int index = funcionHashing(unaClave);
        if (tabla[index].contains(unaClave)) {
            return index; // o el valor asociado
        }
        return -1;
    }

    public int insertar(int unaClave) {
        int index = funcionHashing(unaClave);
        if (!tabla[index].contains(unaClave)) {
            tabla[index].add(unaClave);
            return index;
        }
        return -1;
    }

    public int funcionHashing(int unaClave) {
        return unaClave % TAMANIOTABLA;
    }
}

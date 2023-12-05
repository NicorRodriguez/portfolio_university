package nrodriguez;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AppTest {
    private Hash hashTable;

    @Before
    public void setUp() {
        hashTable = new Hash();
    }

    @Test
    public void testInsertarYBuscar() {
        assertEquals(-1, hashTable.buscar(5));
        assertEquals(5, hashTable.insertar(5));
        assertNotEquals(-1, hashTable.buscar(5));
    }

    @Test
    public void testBuscarClaveNoExistente() {
        assertEquals(-1, hashTable.buscar(10));
    }

    @Test
    public void testManejarColisiones() {
        int clave1 = 5; // Asumiendo que estas dos claves colisionan
        int clave2 = 228; // Debes ajustar estos valores según tu función hash
        assertEquals(5, hashTable.insertar(clave1));
        assertEquals(5, hashTable.insertar(clave2));
        assertNotEquals(-1, hashTable.buscar(clave1));
        assertNotEquals(-1, hashTable.buscar(clave2));
    }

    @Test
    public void testInsertarClavesDuplicadas() {
        int clave = 5;
        assertEquals(5, hashTable.insertar(clave));
        assertEquals(-1, hashTable.insertar(clave)); // Asumiendo que -1 indica duplicado o error
    }

    @Test
    public void testCargarMuchasClaves() {
        for (int i = 0; i < 1000; i++) {
            assertEquals(i % 223, hashTable.insertar(i));
        }
        for (int i = 0; i < 1000; i++) {
            assertNotEquals(-1, hashTable.buscar(i));
        }
    }

    @Test
    public void testBuscarEnTablaVacia() {
        assertEquals(-1, hashTable.buscar(5));
    }
}

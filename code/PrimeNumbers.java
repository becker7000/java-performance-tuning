import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {

    // Método privado que verifica si un número dado es primo
    private Boolean isPrime(Integer testNumber) {
        // Comienza el ciclo desde i = 2 hasta testNumber - 1
        for (int i = 2; i < testNumber; i++) {
            // Si testNumber es divisible por i, no es primo, y retorna false
            if (testNumber % i == 0) return false;
        }
        // Si no encuentra divisores, testNumber es primo, por lo que retorna true
        return true;
    }

    // Método privado que devuelve el siguiente número primo mayor que el número pasado como argumento
    private Integer getNextPrimeAbove(Integer previous) {
        // Inicia la búsqueda desde el siguiente número al proporcionado
        Integer testNumber = previous + 1;

        // Mientras testNumber no sea primo, sigue incrementando testNumber
        while (!isPrime(testNumber)) {
            testNumber++; // Incrementa testNumber en 1 para probar el siguiente número
        }

        // Retorna el primer número primo encontrado
        return testNumber;
    }

    // Método público que genera una lista de números primos hasta un máximo dado
    public void generateNumbers(Integer max) {
        // Crea una lista para almacenar los números primos generados
        List<Integer> primes = new ArrayList<>();

        // Agrega el primer número primo conocido (2) a la lista
        primes.add(2);

        // Inicializa la variable 'next' con el primer número primo
        Integer next = 2;

        // Genera números primos hasta que se haya generado 'max' números primos
        while (primes.size() <= max) {
            // Obtiene el siguiente número primo mayor que 'next'
            next = getNextPrimeAbove(next);

            // Agrega este nuevo primo a la lista
            primes.add(next);
        }

        // En este punto, la lista 'primes' contiene los primeros 'max' números primos.
        // Si se desea imprimir la lista de primos, se puede descomentar la siguiente línea.
        // System.out.println(primes); // Primero probar con argumento 10 y luego con 5000
    }

}

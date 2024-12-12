import java.util.ArrayList;
import java.util.List;

public class CombinedNumbersTask implements Runnable {

	// Tareas para generar los números de Fibonacci y los números primos
	private FibonnaciNumbersTask fibonnaciNumbersTask;
	private PrimeNumbersTask primeNumbersTask;

	// Listas para almacenar los números primos, de Fibonacci y la lista combinada
	private List<Integer> primes = new ArrayList<Integer>();
	private List<Integer> fibonnacis = new ArrayList<Integer>();
	private List<Integer> combined = new ArrayList<Integer>();

	// Bandera que indica si la tarea ha finalizado
	private Boolean finished;

	// Método que marca la tarea como completada
	public void taskComplete() {
		finished = true; // Establece que la tarea ha finalizado
	}

	// Método para imprimir los números combinados
	public void printCombinedNumbers() {
		synchronized (this) {
			// Imprime la lista combinada de números primos y Fibonacci
			System.out.println(combined.toString());
		}
	}

	// Método para imprimir el estado actual de las listas de primos y Fibonacci
	public void printStatus() {
		// Imprime el tamaño de las listas de primos y Fibonacci
		System.out.println("primos : " + primes.size() + " fibonacci : " + fibonnacis.size());
	}

	// Establece la tarea encargada de generar los números de Fibonacci
	public void setFibonnaciNumbersTask(FibonnaciNumbersTask fibonnaciNumbersTask) {
		this.fibonnaciNumbersTask = fibonnaciNumbersTask;
	}

	// Establece la tarea encargada de generar los números primos
	public void setPrimeNumbersTask(PrimeNumbersTask primeNumbersTask) {
		this.primeNumbersTask = primeNumbersTask;
	}

	// Obtiene el tamaño de la lista combinada
	public int getSize() {
		synchronized (this) {
			if (combined == null) return 0; // Si la lista combinada es nula, devuelve 0
			return (combined.size()); // Devuelve el tamaño de la lista combinada
		}
	}

	// Método que ejecuta la tarea en un hilo separado
	@Override
	public void run() {
		finished = false; // Inicializa la tarea como no completada

		while (!finished) { // Mientras la tarea no esté completada
			// Obtiene el siguiente número primo de la tarea de números primos
			Integer prime = primeNumbersTask.getNextNumber();
			if (prime != null) primes.add(prime); // Si el número primo no es nulo, lo agrega a la lista de primos

			// Obtiene el siguiente número de Fibonacci de la tarea de números de Fibonacci
			Integer fib = fibonnaciNumbersTask.getNextNumber();
			if (fib != null) fibonnacis.add(fib); // Si el número de Fibonacci no es nulo, lo agrega a la lista de Fibonacci

			synchronized (this) {
				// Crea una nueva lista combinada que contiene los números primos
				combined = new ArrayList<>(primes);
				// Mantiene en la lista combinada solo los números que están en ambas listas (primos y Fibonacci)
				combined.retainAll(fibonnacis);
			}

		}

	}

}

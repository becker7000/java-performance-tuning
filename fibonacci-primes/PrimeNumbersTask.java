import java.util.ArrayList;
import java.util.List;

public class PrimeNumbersTask implements Runnable {

	// Lista para almacenar los números primos generados
	private List<Integer> primes = new ArrayList<Integer>();

	// Último número comprobado en busca de ser primo
	private Integer lastNumberChecked;

	// Instancia de la clase NumberChecker que se utiliza para verificar si un número es primo
	private NumberChecker checker;

	// Bandera que indica si la tarea ha finalizado
	private Boolean finished;

	// Método para generar el siguiente número primo
	private void generateNextPrime() {
		// Solo es necesario sincronizar la parte que modifica la lista de primos
		synchronized (this) {
			Integer testNumber = lastNumberChecked + 1; // Inicia la prueba con el siguiente número
			// Mientras el número no sea primo, sigue incrementando
			while (!checker.isPrime(testNumber)) {
				testNumber++;
			}
			// Una vez encontrado un número primo, lo guarda
			lastNumberChecked = testNumber;
			primes.add(testNumber); // Agrega el número primo a la lista de primos
		}
	}

	// Marca la tarea como completada
	public void taskComplete() {
		finished = true; // Cambia el estado a "finalizado"
	}

	// Obtiene el tamaño de la lista de primos
	public int getSize() {
		synchronized (this) {
			return (primes.size()); // Devuelve el tamaño de la lista de primos
		}
	}

	// Obtiene el siguiente número primo de la lista, si existe
	public Integer getNextNumber() {
		synchronized (this) {
			if (primes.size() > 0) {
				return primes.remove(0); // Elimina y devuelve el primer número de la lista de primos
			} else {
				return null; // Si la lista está vacía, devuelve null
			}
		}
	}

	// Método que ejecuta la tarea en un hilo separado
	@Override
	public void run() {
		finished = false; // Marca el inicio de la tarea
		checker = new NumberChecker(); // Crea una nueva instancia de NumberChecker para verificar primos
		synchronized (this) {
			primes.add(2); // Agrega el primer número primo (2) a la lista de primos
		}
		lastNumberChecked = 2; // Establece el primer número verificado como 2

		// Bucle que sigue generando números primos mientras no se haya completado la tarea
		while (!finished) {
			generateNextPrime(); // Llama al método para generar el siguiente número primo
		}
	}
}

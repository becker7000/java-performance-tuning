import java.util.ArrayList;
import java.util.List;

public class FibonnaciNumbersTask implements Runnable {

	// Lista para almacenar los números de Fibonacci generados
	private List<Integer> fibonnacis = new ArrayList<Integer>();

	// Bandera que indica si la tarea ha finalizado
	private Boolean finished;

	// Método que marca la tarea como completada
	public void taskComplete() {
		finished = true; // Establece que la tarea ha finalizado
	}

	// Obtiene el tamaño de la lista de números de Fibonacci
	public int getSize() {
		synchronized (this) {
			return (fibonnacis.size()); // Devuelve el tamaño de la lista de Fibonacci
		}
	}

	// Devuelve el siguiente número de Fibonacci en la lista, si existe
	public Integer getNextNumber() {
		synchronized (this) {
			if (fibonnacis.size() > 0) {
				return fibonnacis.remove(0); // Elimina y devuelve el primer número de la lista
			}
			else return null; // Si la lista está vacía, devuelve null
		}
	}

	// Método que ejecuta la tarea en un hilo separado
	@Override
	public void run() {
		finished = false; // Inicializa la tarea como no completada

		int a = 0; // Primer número de la secuencia Fibonacci
		int b = 1; // Segundo número de la secuencia Fibonacci

		synchronized (this) {
			fibonnacis.add(a); // Agrega el primer número (0) a la lista de Fibonacci
			fibonnacis.add(b); // Agrega el segundo número (1) a la lista de Fibonacci
		}

		while (!finished) { // Mientras la tarea no esté completada
			// Solo la adición de números necesita estar sincronizada

			synchronized (this) {
				// Pausamos si hay más de 100 números esperando ser recogidos
				if (fibonnacis.size() < 100) {
					int c = a + b; // Calcula el siguiente número en la secuencia Fibonacci
					fibonnacis.add(c); // Agrega el nuevo número a la lista de Fibonacci
					a = b; // Actualiza el valor de 'a'
					b = c; // Actualiza el valor de 'b'
				}
			}

		}

	}

}

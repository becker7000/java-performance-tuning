public class Main {

	public static void main(String[] args) throws InterruptedException {
		int target = 100; // Objetivo de números combinados que queremos encontrar

		// Crear y ejecutar el hilo para generar números primos
		PrimeNumbersTask primeNumbersTask = new PrimeNumbersTask();
		Thread primesGeneratorThread = new Thread(primeNumbersTask);
		primesGeneratorThread.start(); // Inicia el hilo para generar números primos
		primesGeneratorThread.setName("primesGeneratorThread"); // Asigna un nombre al hilo

		// Crear y ejecutar el hilo para generar números Fibonacci
		FibonnaciNumbersTask fibonnaciNumbersTask = new FibonnaciNumbersTask();
		Thread fibonnaciNumbersThread = new Thread(fibonnaciNumbersTask);
		fibonnaciNumbersThread.setName("fibonnaciNumbersThread"); // Asigna un nombre al hilo
		fibonnaciNumbersThread.start(); // Inicia el hilo para generar números Fibonacci

		// Crear la tarea combinada que combinará los números primos y Fibonacci
		CombinedNumbersTask combinedNumbersTask = new CombinedNumbersTask();
		combinedNumbersTask.setPrimeNumbersTask(primeNumbersTask); // Asigna la tarea de números primos
		combinedNumbersTask.setFibonnaciNumbersTask(fibonnaciNumbersTask); // Asigna la tarea de números Fibonacci
		Thread combinedNumbersThread = new Thread(combinedNumbersTask);
		combinedNumbersThread.start(); // Inicia el hilo para combinar los números
		combinedNumbersThread.setName("combinedNumbersThread"); // Asigna un nombre al hilo

		int combined = 0; // Número de coincidencias encontradas
		int iterations = 0; // Contador de iteraciones

		// Bucle principal que se ejecuta mientras no se haya alcanzado el objetivo
		while (combined < target) {
			iterations++; // Incrementa el número de iteraciones
			combined = combinedNumbersTask.getSize(); // Obtiene el tamaño de la lista combinada

			// Si el número combinado supera los 100, finaliza las tareas
			if (combined > 100) {
				primeNumbersTask.taskComplete(); // Marca la tarea de números primos como completada
				fibonnaciNumbersTask.taskComplete(); // Marca la tarea de Fibonacci como completada
				combinedNumbersTask.taskComplete(); // Marca la tarea combinada como completada
			}

			// Después de 200 iteraciones, muestra el estado actual y reinicia el contador
			if (iterations > 200) {
				iterations = 0; // Reinicia el contador de iteraciones
				System.out.println("Actualmente tenemos " + combined + " números coincidentes.");

				// Si hay números combinados, los imprime
				if (combined > 0) combinedNumbersTask.printCombinedNumbers();

				// Pausa la ejecución del hilo durante 1 segundo
				Thread.sleep(1000);
			}
		}

		// Una vez alcanzado el objetivo, imprime el resultado final
		System.out.println("Trabajo terminado - encontrados " + combined + ".");

		// Si se encontraron números combinados, los imprime
		if (combined > 0) combinedNumbersTask.printCombinedNumbers();
	}

}

/*

El programa genera 4 hilos diferentes
Uno genera números primos
Otro números de fibonacci
El tercero combina los resultados para ver coincidencias
El cuarto observa todo
 */

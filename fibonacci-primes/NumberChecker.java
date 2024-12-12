public class NumberChecker {

	// Método para verificar si un número es primo
	public Boolean isPrime(Integer testNumber) {
		// Si el número es menor que 2, no es primo
		if (testNumber < 2) {
			return false;
		}

		// Verifica si el número es divisible por cualquier número entre 2 y el número-1
		for (Integer i = 2; i < testNumber; i++) {
			if (testNumber % i == 0) {
				// Si el número es divisible, no es primo
				return false;
			}
		}

		// Si no se encontró ningún divisor, es un número primo
		return true;
	}

}





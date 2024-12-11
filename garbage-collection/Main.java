import java.util.ArrayList;  // Importa la clase ArrayList, que es una lista dinámica.
import java.util.List;  // Importa la interfaz List, que es una colección ordenada de elementos.

public class Main {  // Define la clase principal del programa.

    public static void main(String[] args) throws InterruptedException {  // Método principal que lanza excepciones de interrupción.

        Runtime runtime = Runtime.getRuntime();  // Obtiene la instancia del entorno de ejecución.

        long availableBytes = runtime.freeMemory();  // Obtiene la cantidad de memoria libre disponible (del Heap).
        System.out.println("Memoria disponible al inicio: " + availableBytes / 1024 + "k");  // Muestra la memoria libre al inicio (en kilobytes).

        // Vamos a crear muchos objetos....
        List<Customer> customers = new ArrayList<Customer>();  // Crea una lista de objetos Customer.

        for (int i = 0; i < 1000000; i++) {  // Bucle que itera 1 millón de veces.
            customers.add(new Customer("John"));  // Añade un nuevo objeto Customer a la lista.
        }

        availableBytes = runtime.freeMemory();  // Obtiene nuevamente la memoria libre después de crear los objetos.
        System.out.println("Memoria disponible cuando los clientes son creados: " + availableBytes / 1024 + "k");  // Muestra la memoria después de crear los clientes.

        customers = new ArrayList<>();  // Crea una nueva lista, eliminando la referencia a la lista anterior.

        availableBytes = runtime.freeMemory();  // Obtiene la memoria libre después de eliminar la referencia.
        System.out.println("Memoria disponible cuando los clientes ya no están referenciados: " + availableBytes / 1024 + "k");  // Muestra la memoria después de la eliminación de referencia.

        Thread.sleep(3000);  // Hace una pausa de 1 segundo en la ejecución del programa.

        availableBytes = runtime.freeMemory();  // Obtiene la memoria libre después de la pausa.
        System.out.println("Memoria disponible 3 segundos después: " + availableBytes / 1024 + "k");  // Muestra la memoria después de 1 segundo.

        System.gc();  // Llama al recolector de basura para liberar memoria no utilizada.
        /*
        * GC no siempre libera memoria de inmediato:
        * El comando System.gc() solicita que el recolector de basura
        * se ejecute, pero no garantiza que libere toda la memoria que
        * ya no está en uso. Java podría haber utilizado ese espacio
        * para otros fines antes de que el GC lo recoja.
        * */

        availableBytes = runtime.freeMemory();  // Obtiene la memoria libre después de ejecutar el recolector de basura.
        System.out.println("Memoria disponible después del comando GC: " + availableBytes / 1024 + "k");  // Muestra la memoria después de ejecutar el recolector de basura.

    }

}

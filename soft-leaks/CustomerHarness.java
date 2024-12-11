public class CustomerHarness {

    public static void main(String[] args)  {

        // Crea una instancia de CustomerManager para gestionar los clientes.
        CustomerManager cm = new CustomerManager();

        // Crea las tareas para generar y procesar clientes.
        GenerateCustomerTask generateTask = new GenerateCustomerTask(cm);
        ProcessCustomerTask processTask = new ProcessCustomerTask(cm);

        // Crea y ejecuta 10 hilos para generar clientes.
        for (int user = 0; user < 10; user++) {
            Thread t = new Thread(generateTask);  // Crea un nuevo hilo para generar clientes.
            t.start();  // Inicia el hilo.
        }

        // Crea y ejecuta un hilo para procesar clientes.
        Thread t = new Thread(processTask);  // Crea un hilo para procesar clientes.
        t.start();  // Inicia el hilo.

        // El hilo principal ahora actúa como el hilo de monitoreo.
        while (true) {
            try {
                // Pausa de 5 segundos en el hilo principal para monitorear el estado.
                Thread.sleep(5000);  // Pausa de 5000 milisegundos (5 segundos).
            } catch (InterruptedException e) {
                // Si ocurre una interrupción, se maneja el error.
                e.printStackTrace();
            }

            // Imprime el número de clientes en la cola y el ID del próximo cliente.
            cm.howManyCustomers();

            // Muestra la cantidad de memoria libre disponible en la JVM.
            System.out.println("Memoria disponible: " + Runtime.getRuntime().freeMemory() / 1024 + "k");

        }
    }
} // Ejecutar una vez normal y otra con -Xmx10m (observar)

/*
    CustomerHarness es el punto de entrada del programa y actúa como un "harness" o
    controlador para gestionar la creación, procesamiento y monitoreo de clientes.
    Se crean 10 hilos para generar clientes mediante la tarea GenerateCustomerTask.
    Se crea 1 hilo para procesar clientes mediante la tarea ProcessCustomerTask.
    El hilo principal actúa como un hilo de monitoreo, imprimiendo cada 5 segundos la cantidad de clientes
    en la cola y la memoria libre disponible en la JVM.
    La memoria libre se obtiene usando Runtime.getRuntime().freeMemory(), que proporciona la cantidad de
    memoria disponible en el sistema para la JVM.
 */

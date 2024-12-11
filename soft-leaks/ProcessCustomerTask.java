import java.util.Optional;

public class ProcessCustomerTask implements Runnable {

    // Instancia de CustomerManager para obtener clientes.
    private CustomerManager cm;

    // Constructor que recibe un CustomerManager.
    public ProcessCustomerTask(CustomerManager cm) {
        this.cm = cm;
    }

    // Método que se ejecuta al iniciar el hilo.
    @Override
    public void run() {
        while (true) {  // Ciclo infinito para procesar clientes continuamente.

            // Intenta obtener el siguiente cliente de la cola.
            Optional<Customer> customer = cm.getNextCustomer();

            // Si no hay clientes en la cola, se espera medio segundo.
            if (customer.isEmpty()) {
                // No hay clientes en la cola, por lo que se pausa durante medio segundo.
                try {
                    Thread.sleep(50);  // Pausa de 50 milisegundos.
                } catch (InterruptedException e) {
                    e.printStackTrace();  // Si ocurre una interrupción, se imprime el error.
                }
            } else {
                // El procesamiento del cliente tendría lugar aquí.
                // Aquí podrías agregar lógica para procesar al cliente obtenido.
            }
        }
    }
}

/*
    ProcessCustomerTask implementa Runnable y se usa para procesar clientes en un hilo separado.
    El método run() ejecuta un ciclo infinito donde constantemente intenta obtener el siguiente cliente de la
    cola utilizando getNextCustomer() de CustomerManager.
    Si no hay clientes disponibles, el hilo se duerme por 50 milisegundos para no sobrecargar la CPU,
    luego vuelve a intentar.
    Si hay un cliente disponible, se indicaría que el procesamiento tendría lugar en ese bloque
    (aquí no se ha implementado ninguna acción de procesamiento explícita).
 */
import java.util.UUID;

public class GenerateCustomerTask implements Runnable {

    // Instancia de CustomerManager para agregar clientes.
    private CustomerManager cm;

    // Constructor que recibe un CustomerManager.
    public GenerateCustomerTask(CustomerManager cm) {
        this.cm = cm;
    }

    // Método que se ejecuta al iniciar el hilo.
    @Override
    public void run() {
        while (true) {  // Ciclo infinito para generar clientes constantemente.
            try {
                // Esto es solo para ralentizar el proceso y poder ver lo que está sucediendo.
                Thread.sleep(2);  // Hace una pausa de 2 milisegundos entre cada creación de cliente.
            } catch (InterruptedException e) {
                // Si ocurre una interrupción, se maneja (aunque no se hace nada en este caso).
            }

            // Genera un nombre único usando UUID (identificador único universal).
            String name = UUID.randomUUID().toString();

            // Crea un nuevo cliente con el nombre generado.
            Customer c = new Customer(name);

            // Agrega el cliente al CustomerManager.
            cm.addCustomer(c);
        }
    }
}

/*
    GenerateCustomerTask implementa Runnable y se usa para generar clientes de manera
    continua en un hilo separado.

    > Se utiliza UUID.randomUUID() para crear nombres únicos para cada cliente.
    > El método run() crea un ciclo infinito que genera un cliente cada 2 milisegundos y
    lo agrega al CustomerManager.
    > El Thread.sleep(2) introduce un retraso para poder observar el comportamiento,
    lo que puede ser útil durante el desarrollo y depuración, pero podría eliminarse
    o ajustarse en un entorno de producción.

*/

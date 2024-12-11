import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CustomerManager {

    // Lista que almacena los clientes.
    private List<Customer> customers = new ArrayList<Customer>();

    // ID disponible para el próximo cliente.
    private int nextAvalailbleId = 0;

    // Último ID procesado.
    private int lastProcessedId = -1;

    // Método para agregar un cliente a la lista.
    public  void addCustomer(Customer customer) {
        synchronized (this) {  // Sincroniza el bloque para evitar problemas de concurrencia.
            customer.setId(nextAvalailbleId);  // Asigna el próximo ID disponible al cliente.
            synchronized(customers) {  // Sincroniza el acceso a la lista de clientes.
                customers.add(customer);  // Agrega el cliente a la lista.
            }
            nextAvalailbleId++;  // Incrementa el ID para el siguiente cliente.
        }
    }

    // Método para obtener el siguiente cliente en la lista.
    public Optional<Customer> getNextCustomer() {

        // Verifica si hay más clientes para procesar.
        if (lastProcessedId + 1 > nextAvalailbleId) {
            lastProcessedId++;  // Incrementa el ID procesado.
            return Optional.of(customers.get(lastProcessedId));  // Devuelve el siguiente cliente.
        }
        return Optional.empty();  // Si no hay más clientes, devuelve un Optional vacío.
    }

    // Método para mostrar cuántos clientes hay en la lista.
    public void howManyCustomers() {
        int size = 0;
        size = customers.size();  // Obtiene el tamaño de la lista de clientes.

        // Muestra en consola la cantidad de clientes en la cola y el ID máximo disponible.
        System.out.println("" + new Date() + " Clientes en la queue: " + size + " de " + nextAvalailbleId);
    }

}

/*
    Este código maneja una lista de clientes con IDs asignados de manera secuencial.
    Se usan bloques synchronized para asegurar que el acceso a los recursos compartidos
    (como la lista de clientes) sea seguro en un entorno concurrente.
    El método howManyCustomers() imprime la cantidad de clientes en la cola y el próximo ID disponible.
*/

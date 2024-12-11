public class Customer  {

    private String name;

    // Crear un cliente
    public Customer(String name) {
        this.name = name;
    }

    // Crear una copia de un cliente
    public Customer(Customer oldCustomer) {
        this.name = oldCustomer.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n\t Name: "+name;
    }

    /*
    Método que antes se usaba...
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
     */
}

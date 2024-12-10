import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Thread.sleep(200000); // Despues de ver JConsole
        //System.out.println("\n\t Iniciando un proceso..."); // Despues JConsole

        Date start = new Date();  // Agregar hasta ver tiempo total de ejecución

        PrimeNumbers primeNumbers = new PrimeNumbers();
        Integer max = Integer.parseInt(args[0]);
        primeNumbers.generateNumbers(max);

        Date end = new Date(); // Agregar hasta ver tiempo total de ejecución
        System.out.println(
                "\n\t Tiempo total de ejecución: "
                +(end.getTime()-start.getTime())
                +" milisegundos"
        ); // Agregar hasta ver tiempo total de ejecución

    }

}

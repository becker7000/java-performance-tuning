public class Ejemplo {

    String nombre = "Carlos";
    int edad = 25;

    public static void main(String[] args) {

        int x = 7;
        x = calcular(x);

    }

    public static int calcular(int dato){
        int valorTemporal = dato + 3;
        int nuevoValor = valorTemporal*2;
        return nuevoValor;
    }

}



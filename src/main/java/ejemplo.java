public class ejemplo {
    public static void main(String[] args) {
        String sinUso = "esto no se usa"; // Regla: variable sin usar
        System.out.println("Hola mundo"); // Regla: usar logger
    }

    public void metodoLargo() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Línea " + i);
        }
    }
}

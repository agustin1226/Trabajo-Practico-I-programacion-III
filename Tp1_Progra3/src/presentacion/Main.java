package presentacion;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        // Lanzamos la aplicación de forma segura para la interfaz gráfica
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // El único punto de inicio es el menú principal
                    VentanaInicio inicio = new VentanaInicio();
                    inicio.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

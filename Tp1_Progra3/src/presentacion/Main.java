package presentacion;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaInicio inicio = new VentanaInicio();
                inicio.setVisible(true);
            } catch (Exception e) {
                System.out.println("Hay un error al iniciar el juego" + e.getMessage());
            }
        });
        
    }
}
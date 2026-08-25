package presentacion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import logica.Direccion;
import logica.JuegoThrees;

public class ControladorTeclado extends KeyAdapter {
    
    // El controlador necesita conocer al modelo para mandarle las órdenes
    private JuegoThrees modelo;

    public ControladorTeclado(JuegoThrees modelo) {
        this.modelo = modelo;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Obtenemos el código de la tecla que el usuario acaba de presionar
        int tecla = e.getKeyCode();

        // Mapeamos las flechas del teclado a nuestro Enum Direccion y llamamos al modelo
        switch (tecla) {
            case KeyEvent.VK_UP:
                modelo.procesarMovimiento(Direccion.ARRIBA);
                break;
            case KeyEvent.VK_DOWN:
                modelo.procesarMovimiento(Direccion.ABAJO);
                break;
            case KeyEvent.VK_LEFT:
                modelo.procesarMovimiento(Direccion.IZQUIERDA);
                break;
            case KeyEvent.VK_RIGHT:
                modelo.procesarMovimiento(Direccion.DERECHA);
                break;
        }
    }
}

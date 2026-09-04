package presentacion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import logica.Direccion;
import logica.JuegoThrees;

public class ControladorTeclado extends KeyAdapter {
    private JuegoThrees modelo;
    public ControladorTeclado(JuegoThrees modelo) {
        this.modelo = modelo;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
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

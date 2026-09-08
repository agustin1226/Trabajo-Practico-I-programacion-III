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
            case KeyEvent.VK_W:	
                modelo.procesarMovimiento(Direccion.ARRIBA);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:	
                modelo.procesarMovimiento(Direccion.ABAJO);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:	
                modelo.procesarMovimiento(Direccion.IZQUIERDA);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:	
                modelo.procesarMovimiento(Direccion.DERECHA);
                break;
        }
    }
}

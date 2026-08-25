package presentacion;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logica.JuegoThrees;
import logica.ObservadorJuego;

public class VentanaJuego extends JFrame implements ObservadorJuego {

    private JPanel contentPane;
    private JuegoThrees modelo;
    private PanelTablero panelTablero;
    private JLabel lblPuntaje;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaJuego frame = new VentanaJuego();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaJuego() {
        // 1. Inicializamos el modelo de la lógica
        modelo = new JuegoThrees();
        modelo.registrarObservador(this);
        
        // 2. Configuración autogenerada compatible con WindowBuilder
        setTitle("Threes! - Programación 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 550);
        setResizable(false);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        // Panel superior para el título y el puntaje
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBorder(new EmptyBorder(10, 15, 10, 15));
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        panelSuperior.setLayout(new BorderLayout(0, 0));
        
        // Puntaje a la derecha
        lblPuntaje = new JLabel("Puntaje: 0");
        lblPuntaje.setForeground(Color.DARK_GRAY);
        lblPuntaje.setFont(new Font("Arial", Font.BOLD, 22));
        panelSuperior.add(lblPuntaje, BorderLayout.WEST);
        
        // 3. Instanciamos nuestro panel del tablero y lo agregamos al centro
        panelTablero = new PanelTablero();
        contentPane.add(panelTablero, BorderLayout.CENTER);
        
        // 4. Conectamos el teclado y le damos el foco a la ventana
        ControladorTeclado controlador = new ControladorTeclado(modelo);
        addKeyListener(controlador);
        setFocusable(true);
        
        // Cargamos la foto inicial de la grilla
        panelTablero.actualizarTablero(modelo.getTablero());
    }

    @Override
    public void notificar(JuegoThrees juego) {
        // Actualizamos los textos y la grilla cuando el modelo avisa de un cambio
        lblPuntaje.setText("Puntaje: " + juego.getPuntajeActual());
        panelTablero.actualizarTablero(juego.getTablero());
        
        // Comprobamos si el jugador perdió
        if (juego.estaTerminado()) {
            // El hilo se pausa acá hasta que el usuario hace clic en "Aceptar"
            JOptionPane.showMessageDialog(this, 
                "¡Te quedaste sin movimientos!\nTu puntaje final es: " + juego.getPuntajeActual(), 
                "Fin del Juego", 
                JOptionPane.INFORMATION_MESSAGE);
                
            // TODO: Acá guardaremos el puntaje en el GestorPuntajes más adelante
            
            // Cuando el usuario apreta "Aceptar", el código sigue y cerramos la ventana
            this.dispose(); // Destruye la ventana
            System.exit(0); // Apaga el programa por completo
        }
    }
}
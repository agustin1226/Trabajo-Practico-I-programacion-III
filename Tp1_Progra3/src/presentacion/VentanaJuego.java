package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import datos.PuntajesArchivoDAO;
import datos.PuntajesDAO;
import logica.JuegoThrees;
import logica.ObservadorJuego;
import logica.Usuario; // <-- Importamos la nueva clase

public class VentanaJuego extends JFrame implements ObservadorJuego {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JuegoThrees modelo;
    private PanelTablero panelTablero;
    private JLabel lblPuntaje;
    private JLabel lblRecord;
    
    // Cambiamos el String por el objeto Usuario
    private Usuario jugadorActual; 
    
    // El constructor ahora recibe el objeto Usuario completo
    public VentanaJuego(Usuario jugador) {
        this.jugadorActual = jugador;
        
        setBackground(new Color(64, 128, 128));
        
        // 1. Inicializamos el modelo de la lógica
        modelo = new JuegoThrees();
        modelo.registrarObservador(this);
        
        // 2. Configuración general de la ventana
        setTitle("Threes! - Programación 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 550);
        setResizable(false);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        // Panel superior para el puntaje y el récord
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(64, 128, 128));
        panelSuperior.setBorder(new EmptyBorder(10, 15, 10, 15));
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        panelSuperior.setLayout(new BorderLayout(0, 0));
        
        // Puntaje a la derecha
        lblPuntaje = new JLabel("Puntaje: 0");
        lblPuntaje.setBackground(new Color(0, 255, 255));
        lblPuntaje.setForeground(new Color(0, 0, 0));
        lblPuntaje.setFont(new Font("Arial", Font.BOLD, 22));
        panelSuperior.add(lblPuntaje, BorderLayout.EAST);
        
        // Récord a la izquierda
        lblRecord = new JLabel("Récord: 0");
        lblRecord.setForeground(new Color(0, 0, 0));
        lblRecord.setFont(new Font("Arial", Font.BOLD, 22));
        panelSuperior.add(lblRecord, BorderLayout.WEST);
        
        // 3. Instanciamos el panel del tablero y lo agregamos al centro
        panelTablero = new PanelTablero();
        panelTablero.setBackground(new Color(64, 128, 128));
        contentPane.add(panelTablero, BorderLayout.CENTER);
        
        // 4. Conectamos el teclado y le damos el foco a la ventana
        ControladorTeclado controlador = new ControladorTeclado(modelo);
        addKeyListener(controlador);
        setFocusable(true);
        
        // Cargamos la foto inicial de la grilla
        panelTablero.actualizarTablero(modelo.getTablero());
        
        // Cargamos el récord histórico usando la interfaz DAO
        PuntajesDAO gestor = new PuntajesArchivoDAO();
        int recordActual = gestor.obtenerMejorPuntaje();
        lblRecord.setText("Récord: " + recordActual);
    }

    @Override
    public void notificar(JuegoThrees juego) {
        // Actualizamos los textos y la grilla cuando el modelo avisa de un cambio
        lblPuntaje.setText("Puntaje: " + juego.getPuntajeActual());
        panelTablero.actualizarTablero(juego.getTablero());        
        
        if (juego.estaTerminado()) {
            // Usamos la interfaz DAO
            PuntajesDAO gestor = new PuntajesArchivoDAO();
            int recordHistorico = gestor.obtenerMejorPuntaje();            
            
            // Verificamos si rompió el récord antes de guardarlo
            boolean nuevoRecord = juego.getPuntajeActual() > recordHistorico;           
            
            // Guardamos el puntaje pasando el objeto Usuario (el DAO se encarga de extraer el nombre)
            gestor.guardarPuntaje(this.jugadorActual, juego.getPuntajeActual());           
            
            // Le pasamos el objeto Usuario a la ventana de fin de juego
            VentanaFinJuego ventanaFin = new VentanaFinJuego(this.jugadorActual, juego.getPuntajeActual(), recordHistorico, nuevoRecord);
            ventanaFin.setModal(true); 
            ventanaFin.setLocationRelativeTo(this); 
            ventanaFin.setVisible(true);            
            this.dispose(); 
        }
    }
}
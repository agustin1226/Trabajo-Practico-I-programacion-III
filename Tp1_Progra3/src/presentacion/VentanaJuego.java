package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.PuntajesArchivoDAO;
import datos.PuntajesDAO;
import logica.JuegoThrees;
import logica.ObservadorJuego;
import logica.Usuario; 

public class VentanaJuego extends JFrame implements ObservadorJuego {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JuegoThrees modelo;
    private PanelTablero panelTablero;
    private PanelEstadoJuego panelEstado; // <-- Nuestro nuevo componente modular
    private Usuario jugadorActual; 
    
    public VentanaJuego(Usuario jugador) {
        this.jugadorActual = jugador;
        setBackground(new Color(64, 128, 128));
        
        modelo = new JuegoThrees();
        modelo.registrarObservador(this);
        
        setTitle("Threes! - Programación 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 550);
        setResizable(false);
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        // 1. Panel de Estado (Norte)
        panelEstado = new PanelEstadoJuego();
        contentPane.add(panelEstado, BorderLayout.NORTH);
        
        // 2. Tablero (Centro)
        panelTablero = new PanelTablero();
        panelTablero.setBackground(new Color(64, 128, 128));
        contentPane.add(panelTablero, BorderLayout.CENTER);
        
        ControladorTeclado controlador = new ControladorTeclado(modelo);
        addKeyListener(controlador);
        setFocusable(true);
        
        // Carga inicial de datos visuales
        panelTablero.actualizarTablero(modelo.getTablero());
        panelEstado.actualizarProximaFicha(modelo.getValorProximaFicha());
        
        PuntajesDAO gestor = new PuntajesArchivoDAO();
        panelEstado.actualizarRecord(gestor.obtenerMejorPuntaje());
    }

    @Override
    public void notificar(JuegoThrees juego) {
        // Delegamos la actualización visual a los paneles correspondientes
        panelEstado.actualizarPuntaje(juego.getPuntajeActual());
        panelEstado.actualizarProximaFicha(juego.getValorProximaFicha());
        panelTablero.actualizarTablero(juego.getTablero());        
        
        if (juego.estaTerminado()) {
            PuntajesDAO gestor = new PuntajesArchivoDAO();
            int recordHistorico = gestor.obtenerMejorPuntaje();            
            boolean nuevoRecord = juego.getPuntajeActual() > recordHistorico;            
            
            gestor.guardarPuntaje(this.jugadorActual, juego.getPuntajeActual());            
            
            VentanaFinJuego ventanaFin = new VentanaFinJuego(this.jugadorActual, juego.getPuntajeActual(), recordHistorico, nuevoRecord);
            ventanaFin.setModal(true); 
            ventanaFin.setLocationRelativeTo(this); 
            ventanaFin.setVisible(true);            
            this.dispose(); 
        }
    }
}
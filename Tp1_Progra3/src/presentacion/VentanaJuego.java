package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import datos.PuntajesArchivo;
import datos.Puntajes;
import logica.JuegoThrees;
import logica.ObservadorJuego;
import logica.Usuario; 

public class VentanaJuego extends JFrame implements ObservadorJuego {
    private static final long serialVersionUID = 1L;
    private static final int ANCHO_VENTANA = 450;
    private static final int ALTO_VENTANA = 550;
    private static final Color COLOR_FONDO = new Color(64, 128, 128);
    private JPanel contentPane;
    private JuegoThrees modelo;
    private PanelTablero panelTablero;
    private PanelEstadoJuego panelEstado;
    private Usuario jugadorActual; 
    
    public VentanaJuego(Usuario jugador) {
        this.jugadorActual = jugador;
        setBackground(COLOR_FONDO);
        
        modelo = new JuegoThrees();
        modelo.registrarObservador(this);
        
        setTitle("Threes! - Programación 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, ANCHO_VENTANA, ALTO_VENTANA);
        setResizable(false);
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        panelEstado = new PanelEstadoJuego();
        contentPane.add(panelEstado, BorderLayout.NORTH);

        panelTablero = new PanelTablero();
        panelTablero.setBackground(COLOR_FONDO);
        contentPane.add(panelTablero, BorderLayout.CENTER);
        
        ControladorTeclado controlador = new ControladorTeclado(modelo);
        addKeyListener(controlador);
        setFocusable(true);
        panelTablero.actualizarTablero(modelo.getTablero());
        panelEstado.actualizarProximaFicha(modelo.getValorProximaFicha());       
        Puntajes gestor = new PuntajesArchivo();
        panelEstado.actualizarRecord(gestor.obtenerMejorPuntaje());
    }

    @Override
    public void notificar(JuegoThrees juego) {
        panelEstado.actualizarPuntaje(juego.getPuntajeActual());
        panelEstado.actualizarProximaFicha(juego.getValorProximaFicha());
        panelTablero.actualizarTablero(juego.getTablero());        

        if (juego.estaTerminado()) {
            Puntajes gestor = new PuntajesArchivo();
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
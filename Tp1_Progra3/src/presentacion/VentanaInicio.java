package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import logica.Usuario;

public class VentanaInicio extends JFrame {    
    private static final long serialVersionUID = 1L;
    private static final int ANCHO_VENTANA = 400;
    private static final int ALTO_VENTANA = 380;    
    private static final Color COLOR_FONDO = new Color(64, 128, 128);
    private static final Color COLOR_TEXTO = Color.WHITE;
    private static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 40);
    private static final Font FUENTE_ETIQUETA = new Font("Arial", Font.BOLD, 16);
    private static final Font FUENTE_INPUT = new Font("Arial", Font.PLAIN, 16);
    private static final Font FUENTE_BOTON_PPAL = new Font("Arial", Font.BOLD, 14);
    private static final Font FUENTE_BOTON_SEC = new Font("Arial", Font.BOLD, 12);
    private JPanel contentPane;
    private JTextField txtNombre;
    
    public VentanaInicio() {
        setResizable(false);
        setTitle("THREES - Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, ANCHO_VENTANA, ALTO_VENTANA);
        setLocationRelativeTo(null); 
        contentPane = new JPanel();
        contentPane.setBackground(COLOR_FONDO); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitulo = new JLabel("THREES");
        lblTitulo.setForeground(COLOR_TEXTO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(FUENTE_TITULO);
        lblTitulo.setBounds(10, 30, 364, 50);
        contentPane.add(lblTitulo);
        
        JLabel lblNombre = new JLabel("Ingresá tu nombre:");
        lblNombre.setForeground(COLOR_TEXTO);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(FUENTE_ETIQUETA);
        lblNombre.setBounds(10, 100, 364, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
        txtNombre.setFont(FUENTE_INPUT);
        txtNombre.setBounds(90, 130, 200, 35);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);

        JButton btnComenzar = new JButton("COMENZAR");
        btnComenzar.setFont(FUENTE_BOTON_PPAL);
        btnComenzar.setBounds(115, 190, 150, 40);
        btnComenzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreIngresado = txtNombre.getText().trim();
                if (nombreIngresado.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaInicio.this, 
                        "¡Tenés que ingresar un nombre para jugar!", 
                        "Atención", 
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    dispose();                                    
                    Usuario nuevoUsuario = new Usuario(nombreIngresado);
                    VentanaJuego juego = new VentanaJuego(nuevoUsuario);
                    juego.setVisible(true);
                }
            }
        });
        contentPane.add(btnComenzar);
        JButton btnRanking = new JButton("RANKING");
        btnRanking.setFont(FUENTE_BOTON_SEC);
        btnRanking.setBounds(115, 240, 150, 30);
        btnRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RankingPuntajes ventanaRanking = new RankingPuntajes();
                ventanaRanking.setVisible(true);
            }
        });
        contentPane.add(btnRanking);
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(FUENTE_BOTON_SEC);
        btnSalir.setBounds(115, 280, 150, 30);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(btnSalir);
    }
}
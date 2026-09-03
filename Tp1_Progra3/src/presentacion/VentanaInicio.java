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

import logica.Usuario; // <-- Importamos la clase Usuario

public class VentanaInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;

    // Ya no hay método main acá. La aplicación arranca desde la clase Main.

    public VentanaInicio() {
        setResizable(false);
        setTitle("THREES - Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 380);
        setLocationRelativeTo(null); // Centrar en pantalla
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 128, 128)); // Mismo color de fondo del juego
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Permite mover elementos libremente en WindowBuilder
        
        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("THREES");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
        lblTitulo.setBounds(10, 30, 364, 50);
        contentPane.add(lblTitulo);
        
        // --- ETIQUETA NOMBRE ---
        JLabel lblNombre = new JLabel("Ingresá tu nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setBounds(10, 100, 364, 20);
        contentPane.add(lblNombre);
        
        // --- CAMPO DE TEXTO ---
        txtNombre = new JTextField();
        txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNombre.setBounds(90, 130, 200, 35);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);
        
        // --- BOTÓN COMENZAR ---
        JButton btnComenzar = new JButton("COMENZAR");
        btnComenzar.setFont(new Font("Arial", Font.BOLD, 14));
        btnComenzar.setBounds(115, 190, 150, 40);
        btnComenzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreIngresado = txtNombre.getText().trim();
                
                // Validación para que no dejen el nombre vacío
                if (nombreIngresado.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaInicio.this, 
                        "¡Tenés que ingresar un nombre para jugar!", 
                        "Atención", 
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    dispose(); // Cierra el menú principal                    
                    
                    // 1. Creamos el objeto Usuario con el texto ingresado
                    Usuario nuevoUsuario = new Usuario(nombreIngresado);
                    
                    // 2. Le pasamos el objeto a VentanaJuego
                    VentanaJuego juego = new VentanaJuego(nuevoUsuario);
                    juego.setVisible(true);
                }
            }
        });
        contentPane.add(btnComenzar);
        
        // --- BOTÓN RANKING ---
        JButton btnRanking = new JButton("RANKING");
        btnRanking.setFont(new Font("Arial", Font.BOLD, 12));
        btnRanking.setBounds(115, 240, 150, 30);
        btnRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RankingPuntajes ventanaRanking = new RankingPuntajes();
                ventanaRanking.setVisible(true);
            }
        });
        contentPane.add(btnRanking);
        
        // --- BOTÓN SALIR ---
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalir.setBounds(115, 280, 150, 30);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(btnSalir);
    }
}
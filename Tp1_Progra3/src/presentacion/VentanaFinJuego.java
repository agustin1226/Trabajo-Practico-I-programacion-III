package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.Usuario;

public class VentanaFinJuego extends JDialog {   
    private static final long serialVersionUID = 1L;
    private static final int ANCHO_VENTANA = 400;
    private static final int ALTO_VENTANA = 250;    
    private static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 16);
    private static final Font FUENTE_TEXTO = new Font("Arial", Font.PLAIN, 14);
    private static final Font FUENTE_DESTACADO = new Font("Arial", Font.BOLD, 14);   
    private static final Color COLOR_NUEVO_RECORD = Color.BLUE;
    
    public VentanaFinJuego(Usuario jugadorActual, int puntajeFinal, int record, boolean esNuevoRecord) {
        setBounds(100, 100, ANCHO_VENTANA, ALTO_VENTANA);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        getContentPane().setLayout(new BorderLayout());        
        // PANEL MEDIO
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        JLabel lblTitulo = new JLabel("Te quedaste sin movimientos!");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(FUENTE_TITULO);
        contentPanel.add(lblTitulo);
        contentPanel.add(Box.createVerticalStrut(20)); 
        JLabel lblPuntaje = new JLabel("Tu puntaje final es: " + puntajeFinal);
        lblPuntaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPuntaje.setFont(FUENTE_DESTACADO);
        contentPanel.add(lblPuntaje);

        contentPanel.add(Box.createVerticalStrut(15)); 
        JLabel lblRecord = new JLabel();
        lblRecord.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRecord.setFont(FUENTE_DESTACADO);     
        if (esNuevoRecord) {
            lblRecord.setText("NUEVO RÉCORD HISTÓRICO!");
            lblRecord.setForeground(COLOR_NUEVO_RECORD);
        } else {
            lblRecord.setText("Récord actual: " + record);
        }
        contentPanel.add(lblRecord);
        // PANEL DE ABAJO
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);        
        JButton btnREINICIAR = new JButton("REINICIAR");
        btnREINICIAR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                VentanaJuego nuevaPartida = new VentanaJuego(jugadorActual);
                nuevaPartida.setVisible(true); 
            }
        });
        buttonPane.add(btnREINICIAR);   
        JButton btnRANKING = new JButton("RANKING");
        btnRANKING.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RankingPuntajes ventanaRanking = new RankingPuntajes();
                ventanaRanking.setVisible(true); 
            }
        });
        buttonPane.add(btnRANKING);
        JButton btnCERRAR = new JButton("CERRAR");
        btnCERRAR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        buttonPane.add(btnCERRAR);
    }
}
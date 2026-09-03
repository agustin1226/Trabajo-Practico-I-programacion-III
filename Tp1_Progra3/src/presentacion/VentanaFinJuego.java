package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.Usuario; // <-- Importamos la clase Usuario

public class VentanaFinJuego extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    //  recibe el objeto Usuario en lugar del String
    public VentanaFinJuego(Usuario jugadorActual, int puntajeFinal, int record, boolean esNuevoRecord) {
        setBounds(100, 100, 400, 250);
        getContentPane().setLayout(new BorderLayout());
        
        // --- PANEL CENTRAL (Textos) ---
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        // Armamos el texto usando HTML para poder centrarlo y poner negritas
        String textoMensaje = "<html><center>¡Te quedaste sin movimientos!<br><br>Tu puntaje final es: <b>" + puntajeFinal + "</b><br>";
        if (esNuevoRecord) {
            textoMensaje += "<br><font color='blue'>¡NUEVO RÉCORD HISTÓRICO!</font>";
        } else {
            textoMensaje += "<br>Récord actual: " + record;
        }
        textoMensaje += "</center></html>";
        
        JLabel lblMensaje = new JLabel(textoMensaje);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblMensaje, BorderLayout.NORTH);

        // --- PANEL INFERIOR (Botones) ---
        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnCERRAR = new JButton("CERRAR");
        btnCERRAR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        
        JButton btnREINICIAR = new JButton("REINICIAR");
        btnREINICIAR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                // Le pasamos el objeto Usuario intacto a la nueva partida
                VentanaJuego nuevaPartida = new VentanaJuego(jugadorActual);
                nuevaPartida.setVisible(true); 
            }
        });
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPane.add(btnREINICIAR);
        buttonPane.add(btnCERRAR);
        
        JButton btnRANKING = new JButton("RANKING");
        btnRANKING.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RankingPuntajes ventanaRanking = new RankingPuntajes();
                ventanaRanking.setVisible(true); 
            }
        });
        buttonPane.add(btnRANKING);
    }
}
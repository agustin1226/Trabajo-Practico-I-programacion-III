package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import datos.PuntajesArchivo;

public class RankingPuntajes extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final int ANCHO_VENTANA = 300;
    private static final int ALTO_VENTANA = 400;    
    private static final Color COLOR_FONDO = new Color(64, 128, 128);
    private static final Color COLOR_TEXTO_TITULO = Color.WHITE;  
    private static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 22);
    private static final Font FUENTE_TEXTO = new Font("Arial", Font.BOLD, 16);
    private static final Font FUENTE_BOTON = new Font("Arial", Font.BOLD, 14);
    private final JPanel contentPanel = new JPanel();
    
    public RankingPuntajes() {
        setTitle("Ranking de Jugadores");
        setBounds(100, 100, ANCHO_VENTANA, ALTO_VENTANA);
        setLocationRelativeTo(null); 
        setModal(true); 
        getContentPane().setLayout(new BorderLayout());       
        contentPanel.setBackground(COLOR_FONDO);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 10));      
        // TITULO
        JLabel lblTitulo = new JLabel("RANKING");
        lblTitulo.setForeground(COLOR_TEXTO_TITULO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(FUENTE_TITULO);
        contentPanel.add(lblTitulo, BorderLayout.NORTH);
        
        // TEXTO
        JTextArea txtRanking = new JTextArea();
        txtRanking.setEditable(false); 
        txtRanking.setFont(FUENTE_TEXTO);
        
        PuntajesArchivo gestor = new PuntajesArchivo();
        txtRanking.setText(gestor.obtenerRankingFormateado()); 
        
        JScrollPane scrollPane = new JScrollPane(txtRanking);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        // PANEL DE ABAJO
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(COLOR_FONDO);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnCerrar = new JButton("CERRAR");
        btnCerrar.setFont(FUENTE_BOTON);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        buttonPane.add(btnCerrar);
    }
}
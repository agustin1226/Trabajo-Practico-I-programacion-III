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

public class RankingPuntajes extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    public RankingPuntajes() {
        setTitle("Ranking de Jugadores");
        setBounds(100, 100, 300, 400);
        setLocationRelativeTo(null); // Centra la ventanita
        setModal(true); // Bloquea la ventana de atrás hasta que cierres esta
        getContentPane().setLayout(new BorderLayout());
        
        contentPanel.setBackground(new Color(64, 128, 128));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 10));
        
        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("RANKING");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        contentPanel.add(lblTitulo, BorderLayout.NORTH);
        
        // --- ÁREA DE TEXTO (Donde van a los récords) ---
        JTextArea txtRanking = new JTextArea();
        txtRanking.setEditable(false); // Para que el usuario no pueda borrar los puntajes
        txtRanking.setFont(new Font("Arial", Font.BOLD, 16));

        datos.PuntajesArchivoDAO gestor = new datos.PuntajesArchivoDAO();
        txtRanking.setText(gestor.obtenerRankingFormateado()); 
        
        // Le agregamos un scroll por si la lista de jugadores es muy larga
        JScrollPane scrollPane = new JScrollPane(txtRanking);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        // --- PANEL INFERIOR (Botón Cerrar) ---
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(new Color(64, 128, 128));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnCerrar = new JButton("CERRAR");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el ranking y te devuelve a donde estabas
            }
        });
        buttonPane.add(btnCerrar);
    }
}
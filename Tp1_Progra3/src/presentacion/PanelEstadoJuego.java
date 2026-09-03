package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelEstadoJuego extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblPuntaje;
    private JLabel lblRecord;
    private JLabel lblProximaFicha;

    public PanelEstadoJuego() {
        setBackground(new Color(64, 128, 128));
        setBorder(new EmptyBorder(10, 15, 10, 15));
        setLayout(new BorderLayout(0, 0));

        // Récord a la izquierda
        lblRecord = new JLabel("Récord: 0");
        lblRecord.setForeground(new Color(0, 0, 0));
        lblRecord.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblRecord, BorderLayout.WEST);

        // Puntaje a la derecha
        lblPuntaje = new JLabel("Puntaje: 0");
        lblPuntaje.setForeground(new Color(0, 0, 0));
        lblPuntaje.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblPuntaje, BorderLayout.EAST);

        // Contenedor central para que la mini ficha no se deforme
        JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCentro.setBackground(new Color(64, 128, 128));
        add(panelCentro, BorderLayout.CENTER);

        // Mini ficha
        lblProximaFicha = new JLabel();
        lblProximaFicha.setHorizontalAlignment(SwingConstants.CENTER);
        lblProximaFicha.setFont(new Font("Arial", Font.BOLD, 24));
        lblProximaFicha.setOpaque(true);
        lblProximaFicha.setPreferredSize(new Dimension(40, 40));
        lblProximaFicha.setBorder(new LineBorder(new Color(180, 170, 160), 2, true));
        panelCentro.add(lblProximaFicha);
    }

    public void actualizarPuntaje(int puntaje) {
        lblPuntaje.setText("Puntaje: " + puntaje);
    }

    public void actualizarRecord(int record) {
        lblRecord.setText("Récord: " + record);
    }

    public void actualizarProximaFicha(int valor) {
        lblProximaFicha.setText(String.valueOf(valor));
        
        if (valor == 1) {
            lblProximaFicha.setBackground(new Color(102, 204, 255));
            lblProximaFicha.setForeground(Color.WHITE);
        } else if (valor == 2) {
            lblProximaFicha.setBackground(new Color(255, 102, 102));
            lblProximaFicha.setForeground(Color.WHITE);
        } else {
            lblProximaFicha.setBackground(Color.WHITE);
            lblProximaFicha.setForeground(new Color(119, 110, 101));
        }
    }
}

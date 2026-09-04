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
    // COLOR
    private static final Color COLOR_FONDO = new Color(64, 128, 128);
    private static final Color COLOR_FICHA_1 = new Color(102, 204, 255);
    private static final Color COLOR_FICHA_2 = new Color(255, 102, 102);
    private static final Color COLOR_FICHA_3 = Color.WHITE;
    private static final Color COLOR_BORDE_FICHA = new Color(180, 170, 160);
    private static final Color COLOR_TEXTO_OSCURO = new Color(119, 110, 101);    
    private static final Font FUENTE_TEXTO = new Font("Arial", Font.BOLD, 22);
    private static final Font FUENTE_MINI_FICHA = new Font("Arial", Font.BOLD, 24);
    private static final int TAMANO_MINI_FICHA = 40;

    private JLabel lblPuntaje;
    private JLabel lblRecord;
    private JLabel lblProximaFicha;

    public PanelEstadoJuego() {
        setBackground(COLOR_FONDO);
        setBorder(new EmptyBorder(10, 15, 10, 15));
        setLayout(new BorderLayout(0, 0));

        // Récord a la izquierda
        lblRecord = new JLabel("Récord: 0");
        lblRecord.setForeground(Color.BLACK);
        lblRecord.setFont(FUENTE_TEXTO);
        add(lblRecord, BorderLayout.WEST);

        // Puntaje a la derecha
        lblPuntaje = new JLabel("Puntaje: 0");
        lblPuntaje.setForeground(Color.BLACK);
        lblPuntaje.setFont(FUENTE_TEXTO);
        add(lblPuntaje, BorderLayout.EAST);

        // Contenedor de la proxima ficha
        JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCentro.setBackground(COLOR_FONDO);
        add(panelCentro, BorderLayout.CENTER);

        // proxima ficha en Mini ficha
        lblProximaFicha = new JLabel();
        lblProximaFicha.setHorizontalAlignment(SwingConstants.CENTER);
        lblProximaFicha.setFont(FUENTE_MINI_FICHA);
        lblProximaFicha.setOpaque(true);
        lblProximaFicha.setPreferredSize(new Dimension(TAMANO_MINI_FICHA, TAMANO_MINI_FICHA));
        lblProximaFicha.setBorder(new LineBorder(COLOR_BORDE_FICHA, 2, true));
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
            lblProximaFicha.setBackground(COLOR_FICHA_1);
            lblProximaFicha.setForeground(Color.WHITE);
        } else if (valor == 2) {
            lblProximaFicha.setBackground(COLOR_FICHA_2);
            lblProximaFicha.setForeground(Color.WHITE);
        } else {
            lblProximaFicha.setBackground(COLOR_FICHA_3);
            lblProximaFicha.setForeground(COLOR_TEXTO_OSCURO);
        }
    }
}
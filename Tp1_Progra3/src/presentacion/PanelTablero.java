package presentacion;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import logica.Casilla;
import logica.Tablero;

public class PanelTablero extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int TAMANO_GRILLA = 4;
    private static final int ESPACIADO_GRILLA = 12;
    private static final Color COLOR_FONDO_PANEL = new Color(250, 248, 239);
    private static final Color COLOR_CASILLA_VACIA = new Color(204, 192, 179);
    private static final Color COLOR_BORDE_CASILLA = new Color(180, 170, 160);
    private static final Color COLOR_TEXTO_OSCURO = new Color(119, 110, 101);  
    private static final Font FUENTE_CASILLA = new Font("Arial", Font.BOLD, 42);
    private JLabel[][] casillasVisuales; 
    public PanelTablero() {
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(COLOR_FONDO_PANEL);        
        setLayout(new GridLayout(TAMANO_GRILLA, TAMANO_GRILLA, ESPACIADO_GRILLA, ESPACIADO_GRILLA));        
        casillasVisuales = new JLabel[TAMANO_GRILLA][TAMANO_GRILLA];

        for (int fila = 0; fila < TAMANO_GRILLA; fila++) {
            for (int col = 0; col < TAMANO_GRILLA; col++) {
                JLabel label = new JLabel("");
                label.setOpaque(true);
                label.setHorizontalAlignment(SwingConstants.CENTER); 
                label.setFont(FUENTE_CASILLA); 
                label.setBackground(COLOR_CASILLA_VACIA); 
                label.setBorder(new LineBorder(COLOR_BORDE_CASILLA, 2, true));     
                casillasVisuales[fila][col] = label;
                add(label); 
            }
        }
    }

    public void actualizarTablero(Tablero tablero) {
        if (tablero != null) {
            for (int fila = 0; fila < TAMANO_GRILLA; fila++) {
                for (int col = 0; col < TAMANO_GRILLA; col++) {
                    Casilla casillaLogica = tablero.getCasilla(fila, col);
                    JLabel labelVisual = casillasVisuales[fila][col];                                 
                    if (casillaLogica.estaVacia()) {
                        labelVisual.setText("");
                        labelVisual.setBackground(COLOR_CASILLA_VACIA);
                        labelVisual.setForeground(Color.BLACK);
                    } else {
                        int valor = casillaLogica.getFicha().getValor();
                        labelVisual.setText(String.valueOf(valor));                                      
                        labelVisual.setBackground(obtenerColorFicha(valor));
                        if (valor == 1 || valor == 2 || valor >= 12) {
                            labelVisual.setForeground(Color.WHITE);
                        } else {
                            labelVisual.setForeground(COLOR_TEXTO_OSCURO); 
                        }
                    }
                }
            }
        }
    }
    //colores de la fichas
    private Color obtenerColorFicha(int valor) {
        switch (valor) {
            case 0:  return new Color(200, 200, 200);
            case 1:  return new Color(102, 204, 255); 
            case 2:  return new Color(255, 102, 102); 
            case 3:  return new Color(255, 255, 255); 
            case 6:  return new Color(255, 222, 173); 
            case 12: return new Color(255, 165, 0);   
            case 24: return new Color(255, 69, 0);    
            default: return new Color(238, 232, 170); 
        }
    }
}
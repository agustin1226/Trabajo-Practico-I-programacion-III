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
    
    // Matriz de etiquetas visuales para WindowBuilder
    private JLabel[][] casillasVisuales; 
    public PanelTablero() {
        // Configuramos el fondo y los márgenes
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(250, 248, 239));        
        // Le decimos a WindowBuilder que use una grilla de 4x4 con separaciones de 12px
        setLayout(new GridLayout(4, 4, 12, 12));       
        casillasVisuales = new JLabel[4][4];
        // Creamos los 16 cuadraditos visuales iniciales
        for (int fila = 0; fila < 4; fila++) {
            for (int col = 0; col < 4; col++) {
                JLabel label = new JLabel("");
                label.setOpaque(true); // Fundamental para que el JLabel acepte color de fondo
                label.setHorizontalAlignment(SwingConstants.CENTER); // Centramos el texto
                label.setFont(new Font("Arial", Font.BOLD, 42)); // Letra grande
                label.setBackground(new Color(204, 192, 179)); // Gris por defecto (vacío)
                label.setBorder(new LineBorder(new Color(180, 170, 160), 2, true)); // Borde sutil  
                // Lo guardamos en nuestra matriz visual y lo agregamos al panel
                casillasVisuales[fila][col] = label;
                add(label); 
            }
        }
    }
    // Este método se llama cada vez que apretás una flechita del teclado
    public void actualizarTablero(Tablero tablero) {
        if (tablero == null) return;
        // Recorremos la matriz y pintamos los JLabels según lo que dice la lógica
        for (int fila = 0; fila < 4; fila++) {
            for (int col = 0; col < 4; col++) {
                Casilla casillaLogica = tablero.getCasilla(fila, col);
                JLabel labelVisual = casillasVisuales[fila][col];             
                if (casillaLogica.estaVacia()) {
                    labelVisual.setText("");
                    labelVisual.setBackground(new Color(204, 192, 179));
                } else {
                    int valor = casillaLogica.getFicha().getValor();
                    labelVisual.setText(String.valueOf(valor));                   
                    // Colores según la ficha
                    if (valor == 1) {
                        labelVisual.setBackground(new Color(102, 204, 255)); // Celeste
                        labelVisual.setForeground(Color.WHITE);
                    } else if (valor == 2) {
                        labelVisual.setBackground(new Color(255, 102, 102)); // Rojizo
                        labelVisual.setForeground(Color.WHITE);
                    } else {
                        labelVisual.setBackground(Color.WHITE); // Blanco para los múltiplos
                        labelVisual.setForeground(Color.BLACK);
                    }
                }
            }
        }
    }
}
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
                    labelVisual.setForeground(Color.BLACK);
                } else {
                    int valor = casillaLogica.getFicha().getValor();
                    labelVisual.setText(String.valueOf(valor));             
                    
                    // Usamos nuestro método centralizado de colores
                    labelVisual.setBackground(obtenerColorFicha(valor));
                    
                    // Ajustamos el color del texto: blanco para fichas oscuras/intensas, negro para claras
                    if (valor == 1 || valor == 2 || valor >= 12) {
                        labelVisual.setForeground(Color.WHITE);
                    } else {
                        labelVisual.setForeground(new Color(119, 110, 101)); // Tono clásico del Threes
                    }
                }
            }
        }
    }

    private Color obtenerColorFicha(int valor) {
        switch (valor) {
            case 0:  return new Color(200, 200, 200); // Casilla vacía (gris claro)
            case 1:  return new Color(102, 204, 255); // Ficha 1 (Celeste)
            case 2:  return new Color(255, 102, 102); // Ficha 2 (Rojizo)
            case 3:  return new Color(255, 255, 255); // Ficha 3 (Blanco)
            case 6:  return new Color(255, 222, 173); // Ficha 6 (Naranja claro / Amarillo)
            case 12: return new Color(255, 165, 0);   // Ficha 12 (Naranja)
            case 24: return new Color(255, 69, 0);    // Ficha 24 (Rojo fuerte)
            default: return new Color(238, 232, 170); // Para fichas más grandes (Khaki / Dorado)
        }
    }
}
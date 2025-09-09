package br.com.mariojp.figureeditor;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            JFrame frame = new JFrame("Figure Editor — Clique esquerdo = Elipse | Clique direito = Retângulo");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

         
            String[] options = { "Estilo Azul", "Estilo Vermelho" };
            JComboBox<String> comboBox = new JComboBox<>(options);

          
            FigureFactory factory = new BlueFactory();
            DrawingPanel panel = new DrawingPanel(factory);

            
            comboBox.addActionListener(e -> {
                String choice = (String) comboBox.getSelectedItem();
                FigureFactory newFactory;
                if ("Estilo Vermelho".equals(choice)) {
                    newFactory = new RedFactory();
                } else {
                    newFactory = new BlueFactory();
                }
                panel.setFigureFactory(newFactory);
                panel.clear();
            });

           
            frame.add(comboBox, BorderLayout.NORTH);
            frame.add(panel, BorderLayout.CENTER);

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
package com.nilknow.temp;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JPanel panel;

    public TextArea textArea;
    public JButton button;

    public void run(){
        setSize(500, 600);

        panel = new JPanel(new FlowLayout());
        add(panel);

        textArea = new TextArea();
        panel.add(textArea);

        button = new JButton("Click Me");
        panel.add(button);

        setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
        SwingUtilities.invokeLater(app::run);
    }
}

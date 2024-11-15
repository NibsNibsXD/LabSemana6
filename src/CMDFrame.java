import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */


public class CMDFrame extends JFrame {
    private Funciones cmd = new Funciones("");
    private JTextArea window = new JTextArea();
    private JScrollPane panel = new JScrollPane(window);
    private int lastEditablePosition;
    
    public CMDFrame() {
        crearFrame();
        window.setText("Microsoft Windows [Version 10.0.22621.521]" 
                + "\n(c) Microsoft Corporation. All rights reserved." + "\n\n" 
                + /*cmd.getPath()*/ ">");
        lastEditablePosition = window.getText().length(); 
    }

    private void crearFrame() {
        window.setBackground(Color.black);
        window.setForeground(Color.white);
        window.setFont(new java.awt.Font("Consolas", 0, 16));
        window.setEditable(true);
        getContentPane().setLayout(new BorderLayout());

        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    // pendiente manejo(); --------------------------------------------------------------------------------
                    
                } else if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) &&
                        window.getCaretPosition() <= lastEditablePosition) {
                    e.consume();
                    
                } else if (window.getCaretPosition() < lastEditablePosition) {
                    window.setCaretPosition(lastEditablePosition); 
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (window.getCaretPosition() < lastEditablePosition) {
                    e.consume(); 
                    
                }
            }
        });

        setTitle("Administrator: Command Prompt");
        setSize(700, 400);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CMDFrame frame = new CMDFrame();
            frame.setVisible(true);
        });
    }
}

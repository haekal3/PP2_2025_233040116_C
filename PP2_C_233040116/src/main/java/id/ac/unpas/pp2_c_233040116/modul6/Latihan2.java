/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040116.modul6;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author KATANA 15
 */
public class Latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Konverter Suhu (Event)");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 150); 
                frame.setLayout(new FlowLayout());
                frame.setLocationRelativeTo(null);
                JLabel label = new JLabel("Celcius: ");
                JTextField input = new JTextField(10);
                JLabel label1 = new JLabel("Fahrenheit:");
                JLabel hasil = new JLabel("..."); 
                JButton button = new JButton("Konversi");
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String teksInput = input.getText().trim();
                            double celcius = Double.parseDouble(teksInput);
                            double fahrenheit = (celcius * 9.0 / 5.0) + 32;
                            hasil.setText(String.format("%.2f", fahrenheit));
                        } catch (NumberFormatException ex) {
                            hasil.setText("Input Angka!");
                        }
                    }
                };
                button.addActionListener(listener);
                frame.add(label);
                frame.add(input);
                frame.add(label1);
                frame.add(hasil);
                frame.add(button);
                frame.setVisible(true);
            }
        });
    }
}
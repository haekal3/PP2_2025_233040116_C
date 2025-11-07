/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040116.modul6;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author KATANA 15
 */
public class Latihan1 {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
            JFrame frame = new JFrame();
            frame.setTitle("Kalkulator Latihan 1");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLayout(new BorderLayout(5, 5));
            JTextField layar = new JTextField("0");
            layar.setEditable(false);
            layar.setHorizontalAlignment(JTextField.RIGHT);
            frame.add(layar, BorderLayout.NORTH);
            JPanel panel = new JPanel();

            panel.setLayout(new GridLayout( 4, 4, 5, 5));
            frame.add(panel, BorderLayout.CENTER); 
            panel.add(new JButton("7"));
            panel.add(new JButton("8"));
            panel.add(new JButton("9"));
            panel.add(new JButton("/"));
            panel.add(new JButton("4"));
            panel.add(new JButton("5"));
            panel.add(new JButton("6"));
            panel.add(new JButton("*"));
            panel.add(new JButton("1"));
            panel.add(new JButton("2"));
            panel.add(new JButton("3"));
            panel.add(new JButton("-"));
            panel.add(new JButton("0"));
            panel.add(new JButton("C"));
            panel.add(new JButton("=")); 
            JButton tombolTambah = new JButton("+"); 
            tombolTambah.setBackground(Color.RED);
            tombolTambah.setFont(new Font("Arial", Font.ITALIC, 24));
            tombolTambah.setForeground(Color.WHITE);
            panel.add(tombolTambah); 
            frame.setVisible(true);
        } 
        });
    }
}

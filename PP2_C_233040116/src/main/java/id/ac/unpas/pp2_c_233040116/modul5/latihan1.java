/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040116.modul5;

import javax.swing.*;

/**
 *
 * @author KATANA 15
 */
public class latihan1 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 1. buat objek JFrame
                JFrame frame = new JFrame("INI BINGKAI");
                // 2. Atur ukuran jendela
                frame.setSize(400,300);
                // 3. Atur aksi saat tombol close ditekan
                frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
                //4. Buat jendela terlihat
                frame.setVisible(true);
            }
        });
    }
}

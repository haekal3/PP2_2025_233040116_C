/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040116.modul8.view;

import id.ac.unpas.pp2_c_233040116.modul8.controller.PersegiPanjangController;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author KATANA 15
 */
public class PersegiPanjangView extends JFrame{
    //kompone UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasil = new JLabel("-");
    private JLabel lblKeliling = new JLabel ("-");
    //Button reset
    private JButton btnReset = new JButton ("Reset");
    private JButton btnHitung = new JButton("Hitung Luas");
    
    public PersegiPanjangView(){
        //inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLayout(new GridLayout(5, 2, 10, 10));
        
        this.add(new JLabel("Panjang: "));
        this.add(txtPanjang);
        this.add(new JLabel ("Lebar: "));
        this.add(txtLebar);
        this.add(new JLabel ("Hasil Luas: "));
        this.add(lblHasil);
        this.add(new JLabel ("Hasil Keliling: "));
        this.add(lblKeliling);
        this.add(btnHitung);
        //menambahkan button reset
        this.add(btnReset);
    }
    
    //Mengambil nilai panjang dari TextField
    public double getPanjang(){
        return Double.parseDouble(txtPanjang.getText());
    }
    
    //Mengambil nilai lebar dari TextField
    public double getLebar(){
        return Double.parseDouble(txtLebar.getText());
    }
    
    //Menampilkan hasil ke Label
    public void setHasil(double hasil){
        lblHasil.setText(String.valueOf(hasil));
    }
    
    public void setKeliling(double hasilKeliling){
        lblKeliling.setText(String.valueOf(hasilKeliling));
    }
    
    //Menampilkan pesan error (jika input bukan angka)
    public void tampilkanPesanError(String pesan){
        JOptionPane.showMessageDialog(this, pesan);
    }
    
    //Mendaftarkan listener untuk tombol (controller akan memberikan aksinya)
    public void addHitungListener(ActionListener listener){
        btnHitung.addActionListener(listener);
    }
    
    public void addResetListener (ActionListener listener){
        btnReset.addActionListener(listener);
    }

    public void resetView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

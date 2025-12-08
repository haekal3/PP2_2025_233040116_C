/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040116.modul8.controller;

import id.ac.unpas.pp2_c_233040116.modul8.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040116.modul8.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author KATANA 15
 */
public class PersegiPanjangController {
    //model dan view sebagai atribut kelas
    private PersegiPanjangModel model;
    private PersegiPanjangView view;
    
    public PersegiPanjangController (PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        
        //menghubungkan tombol di view dengan logic controller
        this.view.addHitungListener(new HitungListener());
        this.view.addResetListener(new ResetListener());
    }
    
    class HitungListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){
            try{
                //1. Ambil data dari view
                double p = view.getPanjang();
                double l = view.getLebar();
                
                //2. kirim data ke model
                model.setPanjang(p);
                model.setLebar(l);
                
                //3. jalankan logika bisnis ke model
                model.hitungLuas();
                model.hitungKeliling();
                
                //4. ambil hasil dari model dan ditampilkan kembali di view
                double hasil = model.getLuas();
                view.setHasil(hasil);
                double hasilKeliling = model.getKeliling();
                view.setKeliling(hasilKeliling);
                
            } catch (NumberFormatException ex){
                //handle jika user memasukan huruf
                view.tampilkanPesanError("Masukan angka yang valid");
            }
        }
    }
    
    class ResetListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){
            // 1. Reset nilai di Model (opsional, tapi disarankan)
            model.setPanjang(0);
            model.setLebar(0);
            model.hitungLuas();      
            model.hitungKeliling();  
            
            // 2. Reset tampilan di View
            view.resetView(); 
        }
    }
}

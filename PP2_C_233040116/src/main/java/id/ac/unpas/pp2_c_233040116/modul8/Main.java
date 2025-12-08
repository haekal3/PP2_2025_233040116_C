/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040116.modul8;

import id.ac.unpas.pp2_c_233040116.modul8.controller.PersegiPanjangController;
import id.ac.unpas.pp2_c_233040116.modul8.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040116.modul8.view.PersegiPanjangView;

/**
 *
 * @author KATANA 15
 */
public class Main {
    public static void main(String[] args) {
        //1. instansiasi model
        PersegiPanjangModel model= new PersegiPanjangModel();
        
        //2. instansiasi view
        PersegiPanjangView view = new PersegiPanjangView();
        
        //3. instansiasi controller (hubungkan model & view)
        PersegiPanjangController controller = new PersegiPanjangController(model, view);
        
        //4. tampilkan view
        view.setVisible(true);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040116.modul8.model;

/**
 *
 * @author KATANA 15
 */
public class PersegiPanjangModel {
    private double panjang;
    private double lebar;
    private double luas;
    private double keliling;
    
    //menghitung luas (logika bisnis)
    public void hitungLuas(){
        this.luas = this.panjang * this.lebar;
    } 
    
    //menghitung keliling 
    public void hitungKeliling(){
        this.keliling = 2 * (this.panjang + this.lebar);
    }
    //getters dan setters
    public void setPanjang(double panjang){
        this.panjang = panjang;
    }
    
    public void setLebar(double lebar){
        this.lebar = lebar;
    }
    
    public double getLuas(){
        return luas;
    }
    
    public double getKeliling(){
        return keliling;
    }
}

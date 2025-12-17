/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040116.modul10;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author KATANA 15
 */
public class MahasiswaApp extends JFrame{
    //komponen GUI
    //latihan3
    //JTextField dan JButton tambahan untuk fitur pencarian data
    JTextField txtNama, txtNIM, txtJurusan, txtCari;
    JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    JTable tableMahasiswa;
    DefaultTableModel model;
    
    public MahasiswaApp() {
        //setup frame
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize (600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        //1. Panel form (input data)
        JPanel panelForm = new JPanel (new GridLayout(4,2,10,10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panelForm.add(new JLabel("nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);
        
        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);
        
        panelForm.add(new JLabel("jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);
        
        //Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        
        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);
        
        //Panel Pencarian
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.setBorder(BorderFactory.createTitledBorder("Pencarian Data"));

        txtCari = new JTextField(20);
        btnCari = new JButton("Cari");

        panelCari.add(new JLabel("Cari Nama:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        
        //Gabungkan panel form dan tombol di bagian atas (NORTH)
        JPanel panelAtas = new JPanel();
        panelAtas.setLayout(new BoxLayout(panelAtas, BoxLayout.Y_AXIS));
        panelAtas.add(panelForm);
        panelAtas.add(panelTombol);
        panelAtas.add(panelCari); //Menambahkan panel cari ke layout
        
        add(panelAtas, BorderLayout.NORTH);
        
        //2. Menampilkan tabel
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");
        
        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);
        
        //Listener klik tabel (untuk mengambil data saat  baris diklik)
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });
        
        //Aksi tombol simpan (Create)
        btnSimpan.addActionListener(e -> tambahData());
        //aksi tombol edit (update)
        btnEdit.addActionListener(e -> ubahData());
        //aksi tombol hapus (delete)
        btnHapus.addActionListener(e -> hapusData());
        //Aksi tombol Clear
        btnClear.addActionListener(e -> kosongkanForm());
        
        //latihan 3
        //Event handler tombol Cari
        //Saat tombol diklik, sistem akan menjalankan method cariData()
        btnCari.addActionListener(e -> cariData());
        
        //load data saat aplikasi pertama kali jalan
        loadData();
    }
        //logika crud
        //1. menampilkan data
        private void loadData() {
            model.setRowCount(0); //reset tabel
            try {
                Connection conn = KoneksiDB.configDB();
                Statement stm = conn.createStatement();
                ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
                
                int no = 1;
                while (res.next()) {
                    model.addRow(new Object[] {
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                    });
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Load data: " + e.getMessage());
            }
        }

        //latihan 3
        //Method untuk mencari data mahasiswa berdasarkan nama
        //Menggunakan query LIKE agar pencarian bersifat parsial
        private void cariData() {
            model.setRowCount(0);
            try {
                String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                //Menambahkan tanda % di awal dan akhir untuk pencarian parsial
                pst.setString(1, "%" + txtCari.getText() + "%");

                ResultSet res = pst.executeQuery();
                int no = 1;
                while (res.next()) {
                    model.addRow(new Object[]{
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                    });
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal cari data: " + e.getMessage());
            }
        }

        //latihan 4
        //Method untuk mengecek apakah NIM sudah terdaftar di database
        //Method ini akan dipanggil sebelum proses INSERT
        private boolean isNimExists(String nim) {
            boolean exists = false;
            try {
                String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, nim);

                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    exists = res.getInt(1) > 0;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error cek NIM: " + e.getMessage());
            }
            return exists;
        }

        //2.create (menambah data)
        private void tambahData(){
            //Latihan 2
            //Validasi input: mengecek apakah txtNama atau txtNIM kosong
            //Jika salah satu kosong, tampilkan pesan error
            //dan batalkan proses penyimpanan data
            if (txtNama.getText().trim().isEmpty() || txtNIM.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
                return; //Batalkan penyimpanan
            }

            //Validasi NIM Duplikat
            if (isNimExists(txtNIM.getText().trim())) {
                JOptionPane.showMessageDialog(this, "NIM sudah terdaftar! Gunakan NIM lain.");
                return; //Batalkan penyimpanan
            }
            
            try {
                String sql = "INSERT INTO Mahasiswa (nama, NIM, Jurusan) VALUES (?,?,?,?)";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                
                pst.setString(1, txtNama.getText());
                pst.setString(2, txtNIM.getText());
                pst.setString(3, txtJurusan.getText());
                
                pst.execute();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
                loadData();
                kosongkanForm();
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "gagal simpan:" + e.getMessage());
            }
        }
        //3.Update mengubah data berdasarkan nim
        private void ubahData() {
            // Validasi juga sebaiknya ada di edit
            if (txtNama.getText().trim().isEmpty() || txtNIM.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
                return;
            }
            try {
                String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ?, WHERE nim = ?";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                
                pst.setString(1, txtNama.getText());
                pst.setString(2, txtNIM.getText());
                pst.setString(3, txtJurusan.getText());
                
                pst.execute();
                JOptionPane.showMessageDialog(this, "Data berhasil diubah");
                loadData();
                kosongkanForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "gagal edit:" + e.getMessage());
            }
        }
        //4.delete data
        private void hapusData() {
            try {
                String sql = "DELETE FROM mahasiswa WHERE nim = ?";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                
                pst.setString(1, txtNIM.getText());
                
                pst.execute();
                JOptionPane.showMessageDialog(this, "data berhasil dihapus");
                loadData();
                kosongkanForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "gagal hapus: " + e.getMessage());
            }
        }
        private void kosongkanForm() {
            txtNama.setText(null);
            txtNIM.setText(null);
            txtJurusan.setText(null);
        }

        public static void main(String[] args) {
        //menjalankan aplikasi
            SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}

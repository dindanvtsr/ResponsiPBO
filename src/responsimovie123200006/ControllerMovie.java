/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responsimovie123200006;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Lenovo
 */
public class ControllerMovie {
    ModelMovie modelMovie;
    MovieView movieView;
     
    public String pilihandata;
     
    public ControllerMovie(ModelMovie modelMovie, MovieView movieView) {
        this.modelMovie = modelMovie;
        this.movieView = movieView;
    
        
        if (modelMovie.getBanyakData()!=0) {
            String dataMovie[][] = modelMovie.MovieList();
            movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        
        
         movieView.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = movieView.getJudul();
                String alur = movieView.getAlur();
                String penokohan = movieView.getPenokohan();
                String akting = movieView.getAkting();
                modelMovie.insertmovie(judul, alur, penokohan, akting);
         
            String dataMovie[][] = modelMovie.MovieList();
            movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
            }
        });
    
          movieView.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = movieView.getJudul();
                String alur = movieView.getAlur();
                String penokohan = movieView.getPenokohan();
                String akting = movieView.getAkting();
                modelMovie.updateMovie(judul, alur, penokohan, akting);
                
                String dataMovie[][] = modelMovie.MovieList();
            movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
            }
        });
    
    
    movieView.tabel.addMouseListener(new MouseAdapter(){    
        public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = movieView.tabel.getSelectedRow();

                pilihandata = movieView.tabel.getValueAt(baris, 0).toString();
                System.out.println(pilihandata);
                
    }
            
 });
    
    movieView.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Apa anda ingin menghapus Film " + pilihandata + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    modelMovie.deleteMovie(pilihandata);
                    String dataMovie[][] = modelMovie.MovieList();
                    movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }
            }
        });
    
    
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubeskita;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zulvan Firdaus
 */
public class Programmer extends Orang {
    private List<Tugas> tugas = new ArrayList();

    public Programmer(String username, String password, String id, String nama, String posisi) {
        super(username, password, id, nama, posisi);
    }

   public int getJumlahTugas(){
       return tugas.size();
   }

    public List<Tugas> getTugas() {
        return tugas;
    }
   
   public void tambahtugas(Tugas t){
       tugas.add(t);
   }
   
   public void hapustugas(Tugas t){
       tugas.remove(t);
   }
   
   public void gantistatus(int i){
       tugas.get(i).setStatus(true);
   }
   
   public Tugas getTugas(int i){
       return tugas.get(i);
   }
}

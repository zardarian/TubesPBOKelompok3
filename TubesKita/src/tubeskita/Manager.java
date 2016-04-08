/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubeskita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Zulvan Firdaus
 */
public class Manager extends Orang {

     private List <Proyek> proyek=new ArrayList();
    
    public Manager(String username, String password, String id, String nama, String posisi) {
        super(username, password, id, nama, posisi);
    }
    
    
    public void createProyek(LocalDate deadline, String nama, String idproyek) {	
        proyek.add(new Proyek(idproyek,nama,deadline));
    }

    public List<Proyek> getProyek() {
        return proyek;
    }
    
    public void removeProyek(int i){
        proyek.remove(i);
    }
    
    public int jumlahproyek(){
        return proyek.size();
    }
    
    public Proyek getProyek(int i) {
	return proyek.get(i);
    }

    public void hapusProyek(Proyek p){
        proyek.remove(p);
    }

}

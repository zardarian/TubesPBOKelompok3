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
public class Proyek implements Comparable<Proyek> {
    private String idProyek;
    private String namaProyek;
    private LocalDate deadline;
    private List<Programmer> programmer;
    private List<Tugas> tugas;
    private boolean statusProyek = false;

    public Proyek(String idProyek, String namaProyek, LocalDate deadline) {
        this.programmer = new ArrayList();
        this.tugas = new ArrayList();
        this.idProyek = idProyek;
        this.namaProyek = namaProyek;
        this.deadline = deadline;
    }
    
    public void createTugas(String id, String namaTugas, Programmer pelaksana, LocalDate deadline){
        Tugas t = new Tugas(id,namaTugas,pelaksana,deadline);
        tugas.add(t);
        programmer.add(pelaksana);
        pelaksana.tambahtugas(t);
    }
    
    public void removetugas(int i){
        Tugas t=tugas.get(i);
        t.getPelaksana().hapustugas(t);
        tugas.remove(i);
    }
    
    public void gantistatus(){
        for(int i = 0;i<tugas.size();i++){
            if(tugas.get(i).isStatus()==false){
                statusProyek=false;
                return;
            }
        }
        statusProyek=true;
    }

    public Tugas getTugas(int i){
        return tugas.get(i);
    }
    
    public void hapustugas(Tugas t){
        tugas.remove(t);
    }
    
    public LocalDate getDeadline() {
        return deadline;
    }

    public String getIdProyek() {
        return idProyek;
    }

    public String getNamaProyek() {
        return namaProyek;
    }

    public boolean isStatusProyek() {
        return statusProyek;
    }

    public void setIdProyek(String idProyek) {
        this.idProyek = idProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public void setStatusProyek(boolean statusProyek) {
        this.statusProyek = statusProyek;
    }
    
    public int jumlahTugas(){
        return tugas.size();
    }

    public List<Programmer> getProgrammer() {
        return programmer;
    }

    public List<Tugas> getTugas() {
        return tugas;
    }
    
    public int jumlahProgrammer(){
        return programmer.size();
    }
    
    public int notifproyek(){
        if(LocalDate.now().plusDays(1).equals(deadline)) {
            return 1;
        } else if (LocalDate.now().plusDays(7).equals(deadline)){
            return 7;
        } else if (LocalDate.now().equals(deadline)) {
            return 0;}
        else return 99;
    }
    
    @Override
    public String toString() {
        return "Proyek{" + "idProyek=" + idProyek + ", namaProyek=" + namaProyek +", jumlahTugas="+ tugas.size()+", jumlahProgrammer="+programmer.size()+ ", deadline=" + deadline + ", statusProyek=" + statusProyek + '}';
    }

    @Override
    public int compareTo(Proyek o) {
        return getDeadline().compareTo(o.getDeadline());
    }

    
}

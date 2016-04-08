/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubeskita;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Zulvan Firdaus
 */
public class Tugas implements Comparable<Tugas> {
    private String id;
    private String namaTugas;
    private Programmer pelaksana;
    private boolean status = false;
    private LocalDate deadline;

    public Tugas(String id, String namaTugas, Programmer pelaksana, LocalDate deadlineTugas) {
        this.id=id;
        this.namaTugas = namaTugas;
        this.pelaksana = pelaksana;
        this.deadline = deadlineTugas;
    }

    public String getId() {
        return id;
    }
    
    public String getNamaTugas() {
        return namaTugas;
    }
    
    public int notiftugas(){
        if(LocalDate.now().equals(deadline.minusDays(1))) {
            return 1;
        } else if (LocalDate.now().equals(deadline.minusDays(7))){
            return 7;
        } else return 0;
    }

    public Programmer getPelaksana() {
        return pelaksana;
    }

    public boolean isStatus() {
        return status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setNamaTugas(String namaTugas) {
        this.namaTugas = namaTugas;
    }

    public void setPelaksana(Programmer pelaksana) {
        this.pelaksana = pelaksana;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDeadlineTugas(LocalDate deadlineTugas) {
        this.deadline = deadlineTugas;
    }

    @Override
    public String toString() {
        return "Tugas{" + "namaTugas=" + namaTugas + ", pelaksana=" + pelaksana + ", status=" + status + ", deadline=" + deadline + '}';
    }

    @Override
    public int compareTo(Tugas o) {
        return getDeadline().compareTo(o.getDeadline());
    }
    
    
}

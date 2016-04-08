/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubeskita;

import java.io.Serializable;

/**
 *
 * @author Zulvan Firdaus
 */ 
public abstract class Orang implements Comparable<Orang>, Serializable {
    private String username;
    private String password;
    private String id;
    private String nama;
    private String posisi;

    public Orang(String username, String password, String id, String nama, String posisi) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.nama = nama;
        this.posisi = posisi;
    }

    public String getUsername() {
        return username;
    }
    
    public void setPassword(String password){
	this.password = password;
    }
    public String getPassword() {
	return password;
    }
    public void setId(String id){
	this.id = id;
    }
    public String getId(){
	return id;
    }
    public void setNama(String nama) {
	this.nama = nama;
    }
    public String getNama() {
	return nama;
    }
    public void setPosisi(String Posisi) {
	this.posisi = posisi;
    }
    public String getPosisi() {
        return posisi;
    }

    @Override
    public String toString() {
        return "Orang{" + "id=" + id + ", nama=" + nama + ", posisi=" + posisi + '}';
    }
    
    @Override
    public int compareTo(Orang o) {
        return getId().compareTo(o.getId());
    }
}
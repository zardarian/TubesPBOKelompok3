package tubeskita;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Zulvan Firdaus
 */
public class Application {

    private List<Orang> orang = new ArrayList();
    private String username, nama, posisi, password, ulangi, id, date2;
    private LocalDate date;
    private int pil, pil2;
    private Scanner s1 = new Scanner(System.in);
    private Scanner s2 = new Scanner(System.in);
    private Scanner s3 = new Scanner(System.in);
    private Scanner s4 = new Scanner(System.in);
    private Scanner s5 = new Scanner(System.in);
    private Scanner s6 = new Scanner(System.in);
    private Scanner s7 = new Scanner(System.in);
    private Scanner s8 = new Scanner(System.in);

    public void start() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream reader = new ObjectInputStream (
                new FileInputStream("list_orang.dat"));
            orang = (List<Orang>) reader.readObject();
        } catch (FileNotFoundException e){
            orang.add(new Manager("admin", "admin", "999999", "admin", "Manager"));
        }
    }
    
    public void save () throws IOException {
        try (ObjectOutputStream writter = new ObjectOutputStream(
                new FileOutputStream("list_orang.dat"))) {
            writter.writeObject(orang);
        }
            
    }

    public void notiftugas(Programmer p) {
        for (int k = 0; k < p.getJumlahTugas(); k++) {
            if (p.getTugas(k).notiftugas() == 1) {
                System.out.println(p.getTugas(k).getId() + " deadline tinggal 1 hari lagi");
            } else if (p.getTugas(k).notiftugas() == 7) {
                System.out.println(p.getTugas(k).getId() + " deadline tinggal 7 hari lagi");
            }
        }
    }

    public void notifproyek(Manager m) {
        for (int k = 0; k < m.jumlahproyek(); k++) {
            if (m.getProyek(k).notifproyek() == 1) {
                
                System.out.println(m.getProyek(k).getIdProyek() + " deadline tinggal 1 hari lagi");
            } else if (m.getProyek(k).notifproyek() == 7) {
                System.out.println(m.getProyek(k).getIdProyek() + " deadline tinggal 7 hari lagi");
            }
        }
    }
    
    public void login() throws IOException {
        int i = 0;
        do {
            System.out.println("--LOGIN--");
            System.out.print("Username : ");
            username = s2.nextLine();
            if (cariOrang(username) == null) {
                System.out.println("Tidak ada yang username cocok");
                i++;
                System.out.println("Program akan tertutup dalam " + (3 - i) + " lagi");
            } else {
                Orang o = cariOrang(username);
                System.out.print("Password : ");
                password = s4.nextLine();
                if (o.getPassword().equals(password)) {
                    if (o instanceof Manager) {
                        menumanager((Manager) o);
                    } else {
                        menuProgrammer((Programmer) o);
                    }
                } else {
                    System.out.println("Password salah");
                    i++;
                    System.out.println("Program akan tertutup dalam " + (3 - i) + " lagi");
                }
            }
        } while (i < 3);
        save();
    }

    public Orang cariOrang(String u) {
        for (int k = 0; k < orang.size(); k++) {
            if (orang.get(k).getUsername().equals(u)) {
                return orang.get(k);
            }
        }
        return null;
    }

    public boolean gantiStatus(Programmer p, String id) {
        for (int k = 0; k < p.getJumlahTugas(); k++) {
            if (p.getTugas(k).getId().equals(id)) {
                p.getTugas(k).setStatus(true);
                return true;
            }
        }
        return false;
    }

    public Proyek cariProject(Manager m, String id) {
        for (int k = 0; k < m.jumlahproyek(); k++) {
            if (m.getProyek(k).getIdProyek().equals(id)) {
                return m.getProyek(k);
            }
        }
        return null;
    }

    public boolean hapusTugas(Programmer p, String id) {
        for (int k = 0; k < p.getJumlahTugas(); k++) {
            if (p.getTugas(k).getId().equals(id)) {
                if (p.getTugas(k).isStatus() == true) {
                    p.hapustugas(p.getTugas(k));
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public Tugas cariTugas(Proyek p, String id) {
        for (int k = 0; k < p.jumlahTugas(); k++) {
            if (p.getTugas(k).getId().equals(id)) {
                return p.getTugas(k);
            }
        }
        return null;
    }

    public Programmer cariPro(String id) {
        for (int k = 0; k < orang.size(); k++) {
            if (orang.get(k).getId().equals(id)) {
                return (Programmer) orang.get(k);
            }
        }
        return null;
    }

    public void menumanager(Manager m) {
        try {
            do {
                System.out.println(m.getNama());
                System.out.println(m.getId());
                System.out.println("Notif : ");
                notifproyek(m);
                System.out.println("Menu : ");
                System.out.println("1. Buat Project");
                System.out.println("2. Lihat Project");
                System.out.println("3. Edit Profil");
                System.out.println("4. Register");
                System.out.println("5. Buat Tugas");
                System.out.println("6. Hapus Proyek");
                System.out.println("7. Hapus Tugas");
                System.out.println("8. Lihat Pegawai");
                System.out.println("9. Logout");
                System.out.println("0. Exit");
                System.out.print("Pilih : ");
                pil = s1.nextInt();
                switch (pil) {
                    case 1:
                        System.out.println("--Pembuatan Project--");
                        System.out.print("Nama Project : ");
                        nama = s3.nextLine();
                        System.out.print("ID Project : ");
                        id = s2.nextLine();
                        System.out.print("Deadline Project (dd-MM-yyyy): ");
                        date2 = s4.next();
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
                            date = LocalDate.parse(date2, formatter);
                            m.createProyek(date, nama, id);
                        } catch (DateTimeParseException e) {
                            System.out.println("Tanggal tidak sesuai format");
                        }
                        break;

                    case 2:
                        Collections.sort(m.getProyek());
                        System.out.println("--List Project--");
                        System.out.println();
                        for (int i = 0; i < m.jumlahproyek(); i++) {
                            m.getProyek(i).gantistatus();
                            System.out.println(m.getProyek(i).toString());
                        }
                        break;
                    case 3: {
                        int i;
                        do {
                            System.out.println("--Menu Edit--");
                            System.out.println("1. Edit nama");
                            System.out.println("2. Edit password");
                            System.out.println("0. Exit");
                            System.out.print("Pilih : ");
                            pil2 = s1.nextInt();
                            switch (pil2) {
                                case 1:
                                    System.out.print("Nama Baru : ");
                                    String input = s2.nextLine();
                                    m.setNama(input);
                                    break;
                                case 2:
                                    System.out.print("Password Lama : ");
                                    input = s2.nextLine();
                                    if (input.equals(m.getPassword())) {
                                        System.out.print("Password Baru : ");
                                        input = s2.nextLine();
                                        System.out.print("Ulangi : ");
                                        String input2 = s2.nextLine();
                                        if (input.equals(input2)) {
                                            m.setPassword(input);
                                        } else {
                                            System.out.println("Password berbeda");
                                        }
                                    } else {
                                        System.out.println("Password yang anda masukan salah");
                                    }
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Input yang anda berikan salah");
                            }
                        } while (pil2 != 0);
                    }
                    break;
                    case 4:
                        System.out.println("--Register--");
                        System.out.print("username  : ");
                        username = s2.nextLine();
                        System.out.print("ID        : ");
                        id = s3.nextLine();
                        System.out.print("nama      : ");
                        nama = s6.nextLine();
                        System.out.print("password  : ");
                        password = s5.nextLine();
                        posisi:
                        System.out.println("--Posisi--");
                        System.out.println("1. Manager");
                        System.out.println("2. Programmer");
                        System.out.print("Pilih : ");
                        int i = s1.nextInt();
                        if (i == 1) {
                            orang.add(new Manager(username, password, id, nama, "Manager"));
                            System.out.println("Akun Manager berhasil dibuat");
                        } else if (i == 2) {
                            orang.add(new Programmer(username, password, id, nama, "Programmer"));
                            System.out.println("Akun Programmer berhasil dibuat");
                        } else {
                            System.out.println("Input salah");
                            continue;
                        }
                        break;
                    case 5:
                        System.out.println("--Buat Tugas--");
                        System.out.print("ID Proyek : ");
                        id = s2.nextLine();
                        if (cariProject(m, id) == null) {
                            System.out.println("proyek tidak ada");
                        } else {
                            Proyek p = cariProject(m, id);
                            System.out.println(p);
                            System.out.print("ID Pelaksana : ");
                            id = s3.nextLine();
                            if (cariPro(id) == null) {
                                System.out.println("Tidak ada programmer yang cocok");
                            } else {
                                Programmer pr = cariPro(id);
                                System.out.println(pr);
                                System.out.print("Nama : ");
                                nama = s5.nextLine();
                                System.out.print("ID : ");
                                id = s7.nextLine();
                                System.out.print("Deadline (dd-MM-yyyy) : ");
                                date2 = s6.next();
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
                                    date = LocalDate.parse(date2, formatter);
                                    p.createTugas(id, nama, pr, date);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Tanggal tidak sesuai format");
                                }
                            }
                        }
                        break;
                    case 6:
                        System.out.print("ID Proyek : ");
                        id = s2.nextLine();
                        Proyek p = cariProject(m, id);
                        m.hapusProyek(p);
                        System.out.println("Proyek telah terhapus");
                        break;
                    case 7:
                        System.out.print("ID Proyek : ");
                        id = s2.nextLine();
                        p = cariProject(m, id);
                        System.out.print("ID Tugas : ");
                        id = s4.nextLine();
                        Tugas t = cariTugas(p, id);
                        System.out.println("Tugas telah terhapus");
                        break;
                    case 8:
                        Collections.sort(orang);
                        for (int f = 0; f < orang.size(); f++) {
                            System.out.println(orang.get(f));
                        }
                        break;
                    case 9:
                        login();
                        break;
                    case 0:
                        save();
                        System.exit(0);
                    default:
                        System.out.println("Input yang anda berikan tidak sesuai");
                        break;
                }
            } while (pil != 0);
        } catch (Exception e) {
            System.out.println("Input yang anda berikan tidak sesuai");
        }
    }

    public void menuProgrammer(Programmer p) {
        try {
            int pilih, pilih2;
            do {
                menyu:
                System.out.println(p.getNama());
                System.out.println(p.getId());
                System.out.println("Notif : ");
                notiftugas(p);
                System.out.println("Menu : ");
                System.out.println("1. Daftar tugas");
                System.out.println("2. Ubah Status Tugas");
                System.out.println("3. Hapus tugas");
                System.out.println("4. Edit Profil");
                System.out.println("5. Logout");
                System.out.println("0. Exit");
                System.out.print("Pilih : ");
                pilih = s1.nextInt();
                switch (pilih) {
                    case 1:
                        Collections.sort(p.getTugas());
                        for (int i = 0; i < p.getJumlahTugas(); i++) {
                            System.out.println(p.getTugas(i));
                        }
                        break;
                    case 2:
                        System.out.print("ID Tugas : ");
                        id = s2.nextLine();
                        if (gantiStatus(p, id) == true) {
                            System.out.println("Status tugas sudah diganti");
                        } else {
                            System.out.println("Tugas tidak ditemukan");
                        }
                        break;
                    case 3:
                        System.out.print("ID Tugas : ");
                        id = s2.nextLine();
                        if (hapusTugas(p, id) == true) {
                            System.out.println("Tugas berhasil dihapus");
                        } else {
                            System.out.println("Tugas gagal dihapus");
                        }
                        break;
                    case 4:
                        do {
                            System.out.println("--Menu Edit--");
                            System.out.println("1. Edit nama");
                            System.out.println("2. Edit password");
                            System.out.println("0. Back");
                            System.out.print("Pilih : ");
                            pilih2 = s1.nextInt();
                            switch (pilih2) {
                                case 1:
                                    System.out.print("Nama Baru : ");
                                    String input = s2.nextLine();
                                    p.setNama(input);
                                    break;
                                case 2:
                                    System.out.print("Password Lama : ");
                                    input = s2.nextLine();
                                    if (input.equals(p.getPassword())) {
                                        System.out.print("Password Baru : ");
                                        input = s2.nextLine();
                                        System.out.print("Ulangi : ");
                                        String input2 = s2.nextLine();
                                        if (input.equals(input2)) {
                                            p.setPassword(input);
                                        } else {
                                            System.out.println("Password berbeda");
                                        }
                                    } else {
                                        System.out.println("Password yang anda masukan salah");
                                    }
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("input yang anda berikan tidak sesuai");
                            }
                        } while (pilih2 != 0);
                    case 5:
                        login();
                        break;
                    case 0:
                        save();
                        System.exit(0);
                    default:
                        System.out.println("input yang anda berikan tidak sesuai");
                        break;
                }
            } while (pilih != 0);
        } catch (Exception e) {
            System.out.println("Salah Input");
        }
    }
}

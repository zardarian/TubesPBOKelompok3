package tubeskita;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Zulvan Firdaus
 */
public class TampilanManager {

    Scanner s1 = new Scanner(System.in);
    Scanner s2 = new Scanner(System.in);
    Scanner s3 = new Scanner(System.in);

    public void lihatproject(Manager m) {
        System.out.println("List Project");
        System.out.println();
        for (int i = 0; i < m.jumlahproyek(); i++) {
            System.out.println(m.getProyek(i).toString());
        }
    }

    public void buatproject(Manager m) {
        System.out.println("Pembuatan Project");
        System.out.println();
        System.out.print("Nama Project : ");
        String nama = s2.nextLine();
        System.out.print("ID Project : ");
        String id = s2.nextLine();
        System.out.print("Deadline Project (dd-MM-yyyy): ");
        String string = s2.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(string, formatter);
        m.createProyek(date, nama, id);
    }

    public void buattugas(Manager m, Orang[] orang) {
        System.out.print("Nomor index project : ");
        int i = s1.nextInt();
        System.out.println(m.getProyek(i));
        System.out.print("Nama Tugas : ");
        String nama = s2.nextLine();
        System.out.print("ID Programmer : ");
        String id = s1.nextLine();
        System.out.print("Deadline Project (dd-MM-yyyy): ");
        String string = s2.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(string, formatter);

    }

    public void running(Manager m) {
        int i;
        do {
            i = menu(m);
            switch (i) {
                case 1:
                    buatproject(m);
                    break;
                case 2:
                    lihatproject(m);
                    break;
                case 0:
                    System.out.println("dadah");
                    break;
                default:
                    System.out.println("Input yang anda berikan salah");
                    break;
            }
        } while (i!=0);
    }

    public int menu(Manager m) {
        System.out.println(m.getNama());
        System.out.println(m.getId());
        int pil;
        System.out.println("Menu : ");
        System.out.println("1. Buat Project");
        System.out.println("2. Lihat Project");
        System.out.println("3. Edit Profil");
        System.out.print("Pilih : ");
        pil = s1.nextInt();
        return pil;
    }
}

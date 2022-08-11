import java.util.Scanner;

class buku {

    public static void main(String[] args) {
        // deklarasi variabel
        String judul, pengarang, penerbit;
        int tahun;

        // membuat scanner baru
        Scanner keyboard = new Scanner(System.in);

        // Tampilkan output ke user
        System.out.println("### Daftar Buku ###");
		
        System.out.print("Judul: ");
        judul = keyboard.nextLine();
		
        System.out.print("Pengarang: ");
        pengarang = keyboard.nextLine();
		
		System.out.print("Penerbit: ");
        penerbit = keyboard.nextLine();

        System.out.print("Tahun: ");
        tahun = keyboard.nextInt();



        // Menampilkan output akhir
        System.out.println("--------------------");
        System.out.println("Judul     : " + judul);
        System.out.println("Pengarang : " + pengarang);
        System.out.println("Penerbit  : " + penerbit);
        System.out.println("Tahun     : " + tahun);
    }

}
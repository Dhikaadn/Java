import java.util.Scanner;
public class demo_kontrak{
	public static void main(String[] a){
		Scanner pilih = new Scanner(System.in);
		System.out.println("--- Perhitungan Gaji Karyawan Kontrak ---");
		System.out.print("1. Jumlah anak(berdasarkan Kartu Keluarga) : ");
		int anak = pilih.nextInt();
		Scanner pilih2 = new Scanner(System.in);
		System.out.print("2. Jumlah Hari masuk : ");
		int hari = pilih2.nextInt();
		karyawan_kontrak ko = new karyawan_kontrak(0, 0, 0, 55000, hari, anak, 0, 0);
		System.out.println("3. Gaji Anda Sekarang : ");
		System.out.println("Tunjangan Anak : "+"Rp"+ko.getTunjanganAnak());
		System.out.println("Upah           : "+"Rp"+ko.getUpah());
		System.out.println("Total Gaji     : "+"Rp"+ko.getTotal());
	}
}
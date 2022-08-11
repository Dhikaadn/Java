import java.util.Scanner;
public class demo_tetap{
	public static void main(String[] a){
		Scanner pilih = new Scanner(System.in);
		System.out.println("--- Perhitungan Gaji Karyawan Tetap ---");
		System.out.print("1. Jumlah anak(berdasarkan Kartu Keluarga) : ");
		int anak = pilih.nextInt();
		karyawan_tetap te = new karyawan_tetap(5000000, 0, 0, anak ,0);
		System.out.println("2. Gaji Anda Sekarang : ");
		System.out.println("Tunjangan Anak : "+"Rp"+te.getTunjanganAnak());
		System.out.println("Gaji pokok     : "+"Rp"+te.getGajiPokok());
		System.out.println("Total Gaji     : "+"Rp"+te.getTotal());
	}
}
import java.util.Scanner;
public class karyawandemo{
	public static void main(String[] a){
		System.out.println("---Mode Pembeli---");
		Scanner input = new Scanner(System.in);
		System.out.print("Masukkan nama anda : ");
		String nama = input.nextLine();
		karyawan ka = new karyawan(true, true, nama, 0, "", "");
		System.out.println(ka.getCetak());
		System.out.println(ka.getKaryawan());

	}
}
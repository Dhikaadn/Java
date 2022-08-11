import java.util.Scanner;
public class pembelidemo{
	public static void main(String[] a){
		System.out.println("---Mode Pembeli---");
		Scanner input = new Scanner(System.in);
		System.out.print("Masukkan nama anda : ");
		String nama = input.nextLine();
		pembeli pe = new pembeli("Sedan/Minibus/Bus", 150, nama, 0, "", "");
		System.out.println(pe.getCetak());

	}
}
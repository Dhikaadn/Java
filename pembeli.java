import java.util.Scanner;
public class pembeli extends induk{
	public String nama;
	public int total;
	public String jenis;
	public String data;



	public pembeli(){
	}

	public pembeli(String merk, int seri, String nama, int total, String jenis, String data){
		super(merk, seri);
		setCetak(nama, total, jenis);
		setKaryawan(data, total);
	}


	public void setCetak(String nama, int total, String jenis){
		String opsi = "";
		System.out.println("Selamat datang saudara "+nama+" silakan pilih menu dibawah ini!");
		do{
			System.out.println("1. Pilihan mobil");
			System.out.println("2. Beli mobil");
			Scanner pilih = new Scanner(System.in);
			System.out.print("Pilih dengan tulis angka : ");
			int angka = pilih.nextInt();
			System.out.println("");


				switch(angka){
					case 1:
						System.out.println("1. Mobil sedan");
						System.out.println("2. Mobil minibus");
						System.out.println("3. Bus");
						break;
					case 2:
						System.out.println("Silakan pilih mobil yang ingin anda beli!(input angka) ");
						System.out.println("1. Mobil sedan Rp150000000");
						System.out.println("2. Mobil minibus Rp350000000");
						System.out.println("3. Bus Rp1000000000");

						Scanner menumobil = new Scanner(System.in);
						System.out.print("Pilih dengan tulis angka : ");
						int angkamobil = menumobil.nextInt();
						System.out.println("");

						switch(angkamobil){
							case 1:
								int harga = 150000000;
								int jumlah;

								Scanner jumlahin = new Scanner(System.in);
								System.out.print("Berapa Unit yang ingin anda pesan : ");
								jumlah = jumlahin.nextInt();
								total = jumlah*harga;
								jenis = "Mobil sedan";

								System.out.println("Jenis : "+jenis);
								System.out.println("Harga : "+"Rp"+total);
								System.out.println("Terima kasih");
								break;

							case 2:
								int hargaa = 350000000;
								int jumlahh;
	

								Scanner jumlahinn = new Scanner(System.in);
								System.out.print("Berapa Unit yang ingin anda pesan : ");
								jumlahh = jumlahinn.nextInt();
								total = jumlahh*hargaa;
								jenis = "Mobil minibus";

								System.out.println("Jenis : "+jenis);
								System.out.println("Harga : "+"Rp"+total);
								System.out.println("Terima kasih");
								break;

							case 3:
								int hargaaa = 1000000000;
								int jumlahhh;
								jenis = "Mobil bus";

								System.out.println("Jenis : "+jenis);
								Scanner jumlahinnn = new Scanner(System.in);
								System.out.print("Berapa Unit yang ingin anda pesan : ");
								jumlahhh = jumlahinnn.nextInt();
								total = jumlahhh*hargaaa;

								System.out.println("Harga : "+"Rp"+total);
								System.out.println("Terima kasih");
								break;


						}

						break;
			
				}

			Scanner awal = new Scanner(System.in);
			System.out.print("Masuk menu?y/t ");
			opsi = awal.nextLine();
		}while(opsi.equals("y"));

		this.nama = nama;
		this.total = total;
		this.jenis = jenis;
	}

	public String getCetak(){
		return "---Data Pembeli---\nNama : "+nama+"\n"+"Total : "+total+"\n"+"Jenis : "+jenis;
	}

	public int getTotal(){
		return total;
	}

	//Mode karyawan
	public void setKaryawan(String data, int total){
		data = getCetak();
		total = getTotal();
		String opsi = "";
		System.out.println("");
		System.out.println("---Mode karyawan---");
		System.out.println("Selamat datang karyawan silakan pilih menu dibawah ini!");
		do{
			System.out.println("1. Lihat data customer");
			System.out.println("2. Gaji");
			Scanner pilih = new Scanner(System.in);
			System.out.print("Pilih dengan tulis angka : ");
			int angka = pilih.nextInt();
			System.out.println("");

				switch(angka){
					case 1:
						System.out.println(data);
					case 2:
						System.out.println("Gaji anda sekarang adalah "+"Rp"+total/100*10);
						break;
				} 

			Scanner awal = new Scanner(System.in);
			System.out.print("Masuk menu?y/t ");
			opsi = awal.nextLine();
			
	}while(opsi.equals("y"));
}

	public String getKaryawan(){
		return "Selamat bekerja kembali";
	}

}


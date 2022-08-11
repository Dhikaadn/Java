import java.util.Scanner;
public class Bentuk{
	Scanner input = new Scanner(System.in);
	public String warna = input.nextLine();
	public boolean isi = true;
	public double jml= 20;

	public Bentuk(){ 
	}

	public Bentuk(String warna, boolean isi, double jml){
		setWarna(warna);
		setIsi(isi);
		setJml(jml);
	}

	public String getWarna(){
		return warna;
	}

	public void setWarna(String warna){
		this.warna = warna;
	}



	public void setIsi(boolean isi){
		this.isi = isi;
	}

	public boolean getIsi(){
		return isi;
	}

	public double getJml(){
		return jml;
	}

	public void setJml(double jml){
		this.jml = jml;
	}



	//public String toString() {
       // return "Bentuk {" + "Warna: " + warna + ", Isi: " + isi + ", Jumlah: " + jml + "}";
    //}

}
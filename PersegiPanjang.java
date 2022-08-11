public class PersegiPanjang extends Bentuk{
	public double panjang;
	public  double lebar;


	public PersegiPanjang(){
	}

	public PersegiPanjang(String warna, boolean isi, double panjang, double lebar){
		super(warna, isi);
		setPanjang(panjang);
		setLebar(lebar);
	}

	public void setPanjang(double panjang){
		this.panjang = panjang;
	}

	public void setLebar(double lebar){
		this.lebar = lebar;
	}

	public double getPanjang(){
		return panjang;
	}

	public double getLebar(){
		return lebar;
	}

	public double getLuas(){
		return panjang*lebar;
	}

	public double getKeliling(){
		return 2*(panjang+lebar);
	}


	public String toString(){
		return "Persegi Panjang {Warna    : "+super.warna+"\n\t   Isi \t          : "+ super.isi + "\n\t   Panjang        : "+ panjang + "\n\t   Lebar          : "+ lebar + "\n\t   Luas           : "+ getLuas() + "\n\t   Keliling       : "+ getKeliling() + "}";
	}
}


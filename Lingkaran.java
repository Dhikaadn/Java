import java.util.Scanner;
public class Lingkaran extends Bentuk{
	public double radius = 7.5;

	public Lingkaran(){
	}

	public Lingkaran(String warna, boolean isi, double jml, double radius){
		super(warna, isi, jml);
		setRadius(radius);
	}                                         

	public void setRadius(double radius){
		this.radius = radius;
	}

	public double getRadius(){
		return radius;
	}

	public double getLuas(){
		return 3.14*radius*radius*super.jml;
	}

	public double getKeliling(){
		return 2*3.14*radius;
	}

	public String toString(){
		return "Lingkaran {Warna    : "+super.warna+"\n\t   Isi \t    : "+ super.isi +  "\n\t   Jumlah \t    : "+ super.jml + "\n\t  Radius   : "+ radius + "\n\t   Luas     : "+ getLuas() + "\n\t   Keliling : "+ getKeliling() + "}";
	}
}


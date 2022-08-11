import java.util.Scanner;
public class karyawan_tetap extends induk_karyawan{
	public int anak;
	public int total;

	public karyawan_tetap(){

	}

	public karyawan_tetap(int pokok, int harian, int tunjangan_anak, int anak, int total){
		super(pokok, harian, tunjangan_anak);
		setTunjanganAnak(anak);
		setTotal(total);
	}

	public int getGajiPokok(){
		return super.pokok;
	}

	public void setTunjanganAnak(int anak){
		this.anak = anak; 
	}

	public int getTunjanganAnak(){
		return super.tunjangan_anak*anak;
	}

	public void setTotal(int total){
		int tunjang = getTunjanganAnak();
		total = super.pokok+tunjang;
		this.total = total;
	}

	public int getTotal(){
		return total;
	}
	
}
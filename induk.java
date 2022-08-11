public class induk{
	public String merk; 
	public int seri;

	public induk(){ 
	}

	public induk(String merk, int seri){
		setMerk(merk);
		setSeri(seri);
	}

	public String getMerk(){
		return merk;
	}

	public void setMerk(String merk){
		this.merk = merk;
	}



	public void setSeri(int seri){
		this.seri = seri;
	}

	public double getSeri(){
		return seri;
	}


}
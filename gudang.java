public class gudang{
	public boolean stock; 
	public boolean status;

	public gudang(){ 
	}

	public gudang(boolean stock, boolean status){
		setStock(stock);
		setStatus(status);
	}

	public boolean getStock(){
		return stock;
	}

	public void setStock(boolean stock){
		this.stock = stock;
	}



	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean getStatus(){
		return status;
	}


}
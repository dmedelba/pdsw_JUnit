package inf.tareaJunit;

public class CuentaBanco {
	
	private int balanceCLP;
	private int balanceUSD;
	private int retiroMAXclp = 200000;
	private int retiroMAXusd = 100;
	
	public CuentaBanco(int balanceCLP, int balanceUSD) {
		this.balanceCLP = balanceCLP;
		this.balanceUSD = balanceUSD;
	}
	
	public void depositoCLP(int monto){
		this.balanceCLP = balanceCLP + monto;

	}
	
	public void depositoUSD(int monto){
		this.balanceUSD = balanceUSD + monto;
	}
	
	public boolean retiroCLP(int monto) {
		if(this.balanceCLP < monto || monto > retiroMAXclp) {
			return false;
		}
		this.balanceCLP= balanceCLP - monto;
		return true;
	}
	
	public boolean retiroUSD(int monto) {
		if(this.balanceUSD < monto || monto > retiroMAXusd) {
			return false;
		}
		this.balanceUSD= balanceUSD - monto;
		return true;
	}
	
	public int getBalanceCLP() {
		return this.balanceCLP;
	}
	public int getBalanceUSD() {
		return this.balanceUSD;
	}
}

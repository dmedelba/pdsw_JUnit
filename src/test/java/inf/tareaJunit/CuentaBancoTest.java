package inf.tareaJunit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CuentaBancoTest {
	private int clpInicial; 
	private int usdInicial;
	CuentaBanco cuentaInicial;
	CuentaBanco cuentaSinSaldo;
	
	@BeforeEach
	protected void setUp() throws Exception {
		clpInicial = 1000000;
		usdInicial = 1000;
		cuentaInicial = new CuentaBanco(clpInicial, usdInicial);
		cuentaSinSaldo = new CuentaBanco(0,0);
	}

	
	@Test
	public void testCuentaBancoAsignarSaldoCLP() throws Exception {
		//Given
		int saldoAgregar = 1000;
		
		//When
		CuentaBanco cuentaNueva = new CuentaBanco(saldoAgregar,0);
		int saldoCLP = cuentaNueva.getBalanceCLP();
		
		//Then
		assertEquals(1000,saldoCLP);
		assertNotEquals(101,saldoCLP);
	}
	
	@Test
	public void testCuentaBancoAsignarSaldoUSD() throws Exception {
		//Given
		int saldoAgregar = 100;
		
		//When
		CuentaBanco cuentaNueva = new CuentaBanco(0,saldoAgregar);
		int saldoUSD = cuentaNueva.getBalanceUSD();
		
		//Then
		assertEquals(100,saldoUSD);
		assertNotEquals(101,saldoUSD);
	}
	
	@Test
	public void testCuentaBancoSaldoInicial() throws Exception {
		//Given
		
		//When
		CuentaBanco cuentaNueva = new CuentaBanco(clpInicial,usdInicial);
		int saldoCLP = cuentaNueva.getBalanceCLP();
		int saldoUSD = cuentaNueva.getBalanceUSD();
	
		
		//Then
		assertEquals(usdInicial,saldoUSD);
		assertEquals(clpInicial,saldoCLP);
	}

	@Test
	public void testDepositoCLP() throws Exception {
		//Given
		int saldoDepositar = 1000000;
		int saldoInicial = cuentaInicial.getBalanceCLP();
		int saldoEsperado = saldoInicial + saldoDepositar;
		
		//When
		cuentaInicial.depositoCLP(saldoDepositar);
		int saldoFinal = cuentaInicial.getBalanceCLP();
		
		//Then
		assertEquals(saldoFinal,saldoEsperado, "Deposito CLP no se realizó correctamente");
		
	}

	@Test
	public void testDepositoUSD() throws Exception {
		//Given
		int saldoDepositar = 100;
		int saldoInicial = cuentaInicial.getBalanceUSD();
		int saldoEsperado = saldoInicial + saldoDepositar;
		
		//When
		cuentaInicial.depositoUSD(saldoDepositar);
		int saldoFinal = cuentaInicial.getBalanceUSD();
		
		//Then
		assertEquals(saldoFinal,saldoEsperado, "Deposito USD no se realizó correctamente");
	}

	@Test
	public void testRetiroCLPSimple() throws Exception {
		//Given
		int saldoRetirar = 150000;
		int saldoInicial = cuentaInicial.getBalanceCLP();
		int saldoEsperado = saldoInicial - saldoRetirar;
		
		//When
		cuentaInicial.retiroCLP(saldoRetirar);
		int saldoFinal = cuentaInicial.getBalanceCLP();
		
		//Then
		assertEquals(saldoFinal,saldoEsperado, "Retiro CLP no se realizó correctamente");

	}

	@Test
	public void testRetiroUSDSimple() throws Exception {
		//Given
		int saldoRetirar = 50;
		int saldoInicial = cuentaInicial.getBalanceUSD();
		int saldoEsperado = saldoInicial - saldoRetirar;
		
		//When
		cuentaInicial.retiroUSD(saldoRetirar);
		int saldoFinal = cuentaInicial.getBalanceUSD();
		
		//Then
		assertEquals(saldoFinal,saldoEsperado, "Retiro USD no se realizó correctamente");
	}
	
	@Test
	public void testRetiroMontoMayorActualCLP() throws Exception {
		//Given
		boolean puedoRetirar;
		int saldoRetirar = 180000;	
		
		//When
		puedoRetirar = cuentaSinSaldo.retiroCLP(saldoRetirar);
		
		//Then
		assertFalse(puedoRetirar);

	}
	
	@Test
	public void testRetiroMontoMayorActualUSD() throws Exception {
		//Given
		boolean puedoRetirar;
		int saldoRetirar = 90;	
		
		//When
		puedoRetirar = cuentaSinSaldo.retiroUSD(saldoRetirar);
		
		//Then
		assertFalse(puedoRetirar);
	}
	
	@Test
	public void testRetiroMontoMayorPermitidoCLP() throws Exception {
		//Given
		boolean puedoRetirar;
		int saldoRetirar = 500000;	
		
		//When
		puedoRetirar = cuentaInicial.retiroCLP(saldoRetirar);
		
		//Then
		assertFalse(puedoRetirar);

	}
	
	@Test
	public void testRetiroMontoMayorPermitidoUSD() throws Exception {
		//Given
		boolean puedoRetirar;
		int saldoRetirar = 200;	
		
		//When
		puedoRetirar = cuentaInicial.retiroUSD(saldoRetirar);
		
		//Then
		assertFalse(puedoRetirar);

	}
	

	@Test
	public void testGetBalanceCLP() throws Exception {
		//Given
		int saldoCLP = 15000000;
		int saldoUSD = 20000;
		CuentaBanco nuevaCuenta = new CuentaBanco(saldoCLP,saldoUSD);
		
		//When
		int balanceCLP= nuevaCuenta.getBalanceCLP();
		
		//Then
		assertEquals(saldoCLP, balanceCLP);
	}

	@Test
	public void testGetBalanceUSD() throws Exception {
		//Given
		int saldoCLP = 15000000;
		int saldoUSD = 20000;
		CuentaBanco nuevaCuenta = new CuentaBanco(saldoCLP,saldoUSD);
		
		//When
		int balanceUSD= nuevaCuenta.getBalanceUSD();
		
		//Then
		assertEquals(saldoUSD, balanceUSD);

	}

}

package inf.tareaJunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
	private int clpInicial; 
	private int usdInicial;
	CuentaBanco cuentaInicial;
	CuentaBanco cuentaSinSaldo;
	int ejecuciones;
	int operaciones;
	int op;
	String respuesta;
	CuentaBanco cuenta;
	List<ArrayList> Historial;

	@BeforeEach
	protected void setUp() throws Exception {
		clpInicial = 1000000;
		usdInicial = 1000;
		cuentaInicial = new CuentaBanco(clpInicial, usdInicial);
		cuentaSinSaldo = new CuentaBanco(0,0);
	}
	
	@Test
	public void testAddEjecucion() throws Exception {
		//Given
		ejecuciones = Main.getEjecuciones();
		int esperado = ejecuciones +1;
		
		//When	
		Main.addEjecucion();
		int nuevaCantidadEjecuciones = Main.getEjecuciones();
		
		//Then
		assertEquals(esperado, nuevaCantidadEjecuciones, "No se agrego una nueva ejecución");

	}
	
	@Test
	public void testCantidadEjecuciones() throws Exception {
		//Given
		
		//ejecuciones = 1, Max= 3
		
		//When
		Main.addEjecucion(); //2
		Main.addEjecucion(); //3
		Main.addEjecucion(); //4 
		
		//Then
		assertTrue(Main.cantidadEjecuciones());

	}
	
	@Test
	public void testAddOperaciones() throws Exception {
		//Given
		operaciones = Main.getOperaciones();
		int esperado = operaciones +1;
		
		//When	
		Main.addOperaciones();
		int nuevaCantidadEjecuciones = Main.getOperaciones();
		
		//Then
		assertEquals(esperado, nuevaCantidadEjecuciones, "No se agrego una nueva operación");

	}

	@Test
	public void testCantidadOperaciones() throws Exception {
		//Given
		
		//operaciones = 1, Max= 4
		
		//When
		Main.addOperaciones(); //2
		Main.addOperaciones(); //3
		Main.addOperaciones(); //4
		Main.addOperaciones(); //5
		
		//Then
		assertTrue(Main.cantidadOperaciones());

	}

	@Test
	public void testOperacionDepositoCLP() throws Exception {
		//Given
		op = 1;
		respuesta = "CLP 200000";
		
		int saldoAnteriorCLP = cuentaInicial.getBalanceCLP();
		int saldoEsperadoCLP = saldoAnteriorCLP + 200000;
		//When
		
		Main.Operacion(op,respuesta,cuentaInicial);
		int saldoFinalCLP = cuentaInicial.getBalanceCLP();
		
		//Then
		assertEquals(saldoFinalCLP,saldoEsperadoCLP);

	}
	
	@Test
	public void testOperacionDepositoUSD() throws Exception {
		//Given
		op = 1;
		respuesta = "USD 200";
		
		int saldoAnteriorUSD = cuentaInicial.getBalanceUSD();
		int saldoEsperadoUSD = saldoAnteriorUSD + 200;
		//When
		
		Main.Operacion(op,respuesta,cuentaInicial);
		int saldoFinalUSD = cuentaInicial.getBalanceUSD();
		
		//Then
		assertEquals(saldoFinalUSD,saldoEsperadoUSD);

	}
	
	@Test
	public void testOperacionDepositoRetiroCLP() throws Exception {
		//Given
		op = 2;
		respuesta = "CLP 100000";
		
		int saldoAnteriorCLP = cuentaInicial.getBalanceCLP();
		int saldoEsperadoCLP = saldoAnteriorCLP - 100000;
		//When
		
		Main.Operacion(op,respuesta,cuentaInicial);
		int saldoFinalCLP = cuentaInicial.getBalanceCLP();
		
		//Then
		assertEquals(saldoFinalCLP,saldoEsperadoCLP);

	}
	
	@Test
	public void testOperacionRetiroUSD() throws Exception {
		//Given
		op = 2;
		respuesta = "USD 100";
		
		int saldoAnteriorUSD = cuentaInicial.getBalanceUSD();
		int saldoEsperadoUSD = saldoAnteriorUSD - 100;
		//When
		
		Main.Operacion(op,respuesta,cuentaInicial);
		int saldoFinalUSD = cuentaInicial.getBalanceUSD();
		
		//Then
		assertEquals(saldoFinalUSD,saldoEsperadoUSD);

	}
	
	@Test
	public void testDepositoNegativoCLP() throws Exception {
		//Given
		op = 1;
		respuesta = "CLP -100";

		//When
		
		boolean Respuesta = Main.Operacion(op,respuesta,cuentaInicial);
		
		//Then
		assertFalse(Respuesta);

	}
	
	@Test
	public void testDepositoNegativoUSD() throws Exception {
		//Given
		op = 1;
		respuesta = "USD -100";

		//When
		
		boolean Respuesta = Main.Operacion(op,respuesta,cuentaInicial);
		
		//Then
		assertFalse(Respuesta);

	}
	
	@Test
	public void testRetiroNegativoCLP() throws Exception {
		//Given
		op = 2;
		respuesta = "CLP -100";

		//When
		
		boolean Respuesta = Main.Operacion(op,respuesta,cuentaInicial);
		
		//Then
		assertFalse(Respuesta);

	}
	
	@Test
	public void testRetiroNegativoUSD() throws Exception {
		//Given
		op = 2;
		respuesta = "USD -100";

		//When
		
		boolean Respuesta = Main.Operacion(op,respuesta,cuentaInicial);
		
		//Then
		assertFalse(Respuesta);

	}
	
	@Test
	public void testCrearTransaccion() throws Exception {
		//Given
		
		//clpInicial = 1000000;
		//usdInicial = 1000;
		
		ArrayList<String> Esperado = new ArrayList<String>();
		Esperado.add("1000000");
		Esperado.add("CLP");
		Esperado.add("1000");
		Esperado.add("USD");
		
		//When
		ArrayList<String> Transaccion  = Main.crearTransaccion(clpInicial, usdInicial);
		
		//Then
		assertEquals(Esperado,Transaccion);

	}
	
	@Test
	public void testHistorialADD1Transaccion() throws Exception {
		
		//Given
		List<ArrayList> HistorialEsperado = new ArrayList<ArrayList>();
		ArrayList<String> Transaccion1  = Main.crearTransaccion(clpInicial, usdInicial);
		HistorialEsperado.add(Transaccion1);
		
		//When
		Main.Historial(Transaccion1);
		Historial = Main.getHistorial();
		
		//Then
		assertEquals(Historial,HistorialEsperado);

	}
	
	@Test
	public void testHistorialMasTransacciones() throws Exception {
		
		//Given
		List<ArrayList> HistorialEsperado = Main.getHistorial();
		int montoCLP = clpInicial + 100000;
		int montoUSD = usdInicial;
		ArrayList<String> Transaccion2  = Main.crearTransaccion(montoCLP, montoUSD);
		HistorialEsperado.add(Transaccion2);
		
		//When	
		Main.Historial(Transaccion2);
		Historial = Main.getHistorial();
		
		//Then
		assertEquals(Historial,HistorialEsperado);

	}

	@Test
	public void testMain() throws Exception {
		
		//Es la interfaz por consola, como no era requerimiento no se consideró para test.

	}
	
	

}

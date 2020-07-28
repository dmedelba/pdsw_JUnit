package inf.tareaJunit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List<ArrayList> Historial = new ArrayList<ArrayList>();
	
	static int ejecuciones = 1;
	static int operaciones = 1;
	
	//get y set para las variables anteriores, para acceder desde el test
	public static List<ArrayList> getHistorial() {
		return Historial;
	}

	public static void setHistorial(List<ArrayList> historial) {
		Historial = historial;
	}

	public static int getEjecuciones() {
		return ejecuciones;
	}

	public static void setEjecuciones(int ejecuciones) {
		Main.ejecuciones = ejecuciones;
	}

	public static int getOperaciones() {
		return operaciones;
	}

	public static void setOperaciones(int operaciones) {
		Main.operaciones = operaciones;
	}

	
	//funciones utilizadas para el procesamiento con el usuario
	public static boolean cantidadEjecuciones() {
		if(ejecuciones > 3) {
			return true;	
		}
		return false;
	}
	
	public static void addEjecucion() {
		ejecuciones++;
	}

	public static boolean cantidadOperaciones() {
		if(operaciones > 4) {
			return true;	
		}
		return false;
	}
	
	public static void addOperaciones() {
		operaciones++;
	}

	public static boolean Operacion(int op, String respuesta, CuentaBanco cuenta) {
		String [] listResp = respuesta.split(" ");
		String moneda = listResp[0];
		String r1 = listResp[1];
		int monto = Integer.parseInt(r1);
		if(monto >0){
			switch(op) {
			case 1:
				if(moneda.equals("CLP")){
					cuenta.depositoCLP(monto);
				}
				if(moneda.equals("USD")){
					cuenta.depositoUSD(monto);
				}
				System.out.println("Depósito exitoso");
				break;
			case 2:
				boolean puedeRetirar;
				if(moneda.equals("CLP")){
					puedeRetirar = cuenta.retiroCLP(monto);
					if(!puedeRetirar) {
						System.out.println("No es posible retirar dicho monto.");
						break;
					}
					System.out.println("Retiro exitoso");
				}
				if(moneda.equals("USD")){
					puedeRetirar = cuenta.retiroUSD(monto);
					if(!puedeRetirar) {
						System.out.println("No es posible retirar dicho monto.");
						break;
					}
					System.out.println("Retiro exitoso");
				}
				
				break;
			}
			return true;
		}	
		else {
			System.out.println("El monto debe ser positivo");
			return false;
		}
		
	}
	
	
	public static void leerHistorial() {
		for (int i=0; i<Historial.size(); i++) {
			System.out.println(Historial.get(i));
		}
	}
	
	public static void Historial(ArrayList<String> transaccion) {
		Historial.add(transaccion);
	}
	
	public static ArrayList<String> crearTransaccion(int balanceCLP, int balanceUSD){
		ArrayList<String> h0 = new ArrayList<String>();
		h0.add(Integer.toString(balanceCLP));
		h0.add("CLP");
		h0.add(Integer.toString(balanceUSD));
		h0.add("USD");
		return h0;
	}
	
	public static void Menu() {
		System.out.println("Bienvenido a su Banco, seleccione operación a realizar");
		System.out.println("1. Depositar");
		System.out.println("2. Retirar");
		System.out.println("3. Ver transacciones");
		System.out.println("4. Cerrar sesión");
	}
	
	
	//MAIN
	public static void main(String[] args) {			
		Scanner input = new Scanner(System.in);
		
		//Iniciamos la cuenta 
		CuentaBanco cuenta = new CuentaBanco(1000000,10000);
		
		ArrayList<String> h0 = crearTransaccion(cuenta.getBalanceCLP(),cuenta.getBalanceUSD());
		Historial(h0);
		
		boolean flag = true;
		
		while(flag) {		
			if(cantidadEjecuciones()) {
				System.out.println("[Fin] Sesiones máximas permitidas");
				break;
			}
			
			Scanner inicio = new Scanner(System.in);
			Menu();
			int respuesta = inicio.nextInt();
			if(cantidadOperaciones()) {
				System.out.println("[Fin sesión] No puede realizar más de 4 operaciones por sesión.");
				respuesta = 4;
			}
			switch(respuesta) {
			case 1:
				inicio.nextLine();
				System.out.println("Ingrese el monto a depositar y moneda, formato (Moneda Valor). Ej: CLP 200000");
				String respuesta1 = inicio.nextLine();	
				boolean resp1 = Operacion(1, respuesta1, cuenta);
				
				if(resp1) {
					ArrayList<String> h1 = crearTransaccion(cuenta.getBalanceCLP(),cuenta.getBalanceUSD());			
					Historial(h1);
					addOperaciones();
				}
				break;

			case 2:
				inicio.nextLine();
				System.out.println("Ingrese el monto a retirar y moneda, formato (Moneda Valor). Ej: USD 200");
				String respuesta2 = inicio.nextLine();	
				boolean resp2 = Operacion(2, respuesta2, cuenta);
				System.out.println("Depósito exitoso");
				
				if(resp2) {
					ArrayList<String> h2 = crearTransaccion(cuenta.getBalanceCLP(),cuenta.getBalanceUSD());
					Historial(h2);
					addOperaciones();
				}
			
				break;
			case 3:
				leerHistorial();
				addOperaciones();
				break;
			case 4:
				System.out.println("Se ha cerrado la sesión, para volver a iniciar sesión ingrese '1'");
				System.out.println("Ingrese '0' para finalizar el programa");
				int respuesta4 = inicio.nextInt();
				switch(respuesta4) {
				case 0:
					flag = false;
					break;
				case 1:
					operaciones = 0;
					addEjecucion();
					break;
				}
				break;	
			}
		}
	}
}

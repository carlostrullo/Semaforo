package control;
import modelo.*;
public class Ejecutable {

	private static Operario nOperario;
	private static Empacador nEmpacador;
	private static Bodega nBodega;

	
	public static void main(String[] args) {
		nBodega = new Bodega();
		nOperario = new Operario(nBodega);
		nEmpacador = new Empacador(nBodega);

		nOperario.start();
		nEmpacador.start();

	}

}

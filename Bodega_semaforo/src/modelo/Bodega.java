package modelo;
import java.util.concurrent.Semaphore;

public class Bodega {

private final static int CAPACIDAD_MAXIMA = 200;
private final static int TIPO_UNO = 10;
private final static int TIPO_DOS = 15;


private int cantidadTipoUno, cantidadtipoDos, capacidadBodega;
private Semaphore cantidadT1Guardados;
private Semaphore cantidadT2Guardados;
private Semaphore volumenDisponible;
private Semaphore mutex;

public Bodega() {

cantidadTipoUno = 0;
cantidadtipoDos = 0;
capacidadBodega = CAPACIDAD_MAXIMA;
cantidadT1Guardados = new Semaphore(cantidadTipoUno);
cantidadT2Guardados = new Semaphore(cantidadtipoDos);
volumenDisponible = new Semaphore(CAPACIDAD_MAXIMA);
mutex = new Semaphore(1);


	}

public void descargarPaquete(int tipo) throws InterruptedException {

if (tipo == TIPO_UNO) {
volumenDisponible.acquire(TIPO_UNO);
mutex.acquire();
capacidadBodega = capacidadBodega - TIPO_UNO;
cantidadTipoUno++;
mutex.release();
cantidadT1Guardados.release(1);
}

 else if (tipo == TIPO_DOS) {
	volumenDisponible.acquire(TIPO_DOS);
	mutex.acquire();
	capacidadBodega = capacidadBodega - TIPO_DOS;
	cantidadtipoDos++;
	mutex.release();
	cantidadT2Guardados.release(1);
	estadoBodega();

		}
	}

	public void crearPaquete() throws InterruptedException {
	cantidadT1Guardados.acquire(3);
	cantidadT2Guardados.acquire(4);
	mutex.acquire();
	cantidadTipoUno -= 3;
	cantidadtipoDos -= 4;
	capacidadBodega += 90;
	mutex.release();
	volumenDisponible.release(90);
	estadoBodega();

	}

	private void estadoBodega() {

		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - ");
		System.out.println("La Bodega Tiene:");
		System.out.println("Cantidad Articulos Tipo Uno: " + cantidadTipoUno);
		System.out.println("Cantidad Articulos Tipo Dos: " + cantidadtipoDos);

		System.out.println("Volumen Ocupado:" + (CAPACIDAD_MAXIMA - capacidadBodega));
		
		System.out.println("");
	}
}

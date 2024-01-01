import java.util.Scanner;

/**
 * @author ADLG.
 * <p>Clase Main para probar el funcionamiento correcto de SimulatedAnnealing y GeneticAlgorithm.</p>
 */
public class Main
{
	/* Main para probar el funcionamiento del problema de las N reinas. */
	public static void main(String[] args)
	{
		Scanner t = new Scanner(System.in);
		SimulatedAnnealing sm = new SimulatedAnnealing();
		GeneticAlgorithm ga = new GeneticAlgorithm();
		int[] res1, res2;
		int numQueens,repeticiones,tamPoblacion,generaciones;
		double temperatura,velEnfriamiento,probMutacion;

        int  opc = 0;
        while(true)
		{
			System.out.println("\n\t\t\t°°°Menu°°°\n1.Resolver el problema de las N reinas con Simulated Annealing"+
        	"\n2.Resolver el problema de las N reinas con Genetic Algorithm\n3.Probar Simulated Annealing con valores preestablecidos."+
        	"\n4.Probar Genetic Algorithm con valores preestablecidos\n5.Salir");
			opc = t.nextInt();
			switch (opc)
			{
				case 1:
					System.out.println("\n**Simulated Annealing**\nIngrese el número de Reinas"); numQueens = t.nextInt();
        			System.out.println("Ingrese el número de iteraciones"); repeticiones = t.nextInt();
					System.out.println("Ingrese la temperatura"); temperatura = t.nextDouble();
					System.out.println("Ingrese el factor de enfriamiento"); velEnfriamiento = t.nextDouble();
					System.out.println("#Configuración\nNo.Reinas:"+numQueens+"\tNo.Iteraciones:"+repeticiones+"\tTemeratura:"+temperatura+"\tFactor de enfriamiento:"+velEnfriamiento);
					res1 = sm.solucion(numQueens, repeticiones, temperatura, velEnfriamiento);
					System.out.println("Tablero Solución");
					printTab(res1); System.out.println("Costo: "+sm.getCosto(res1));
				break;

				case 2:
					System.out.println("\n**Genetic Algorithm**\nIngrese el número de Reinas"); numQueens = t.nextInt();
	        		System.out.println("Ingrese el tamaño de la población"); tamPoblacion = t.nextInt();
					System.out.println("Ingrese la probabilidad de mutación"); probMutacion = t.nextDouble();
					System.out.println("Ingrese el número de generaciones"); generaciones = t.nextInt();
					System.out.println("#Configuración\nNo.Reinas:"+numQueens+"\tTamaño de población:"+tamPoblacion+"\tProbabilidad de mutación:"+probMutacion+"\tNúmero de generaciones:"+generaciones);
					res2 = ga.solucion(numQueens, tamPoblacion, probMutacion, generaciones);
					System.out.println("Tablero Solución");
					printTab(res2); System.out.println("Costo: "+ga.getCosto(res2));
				break;

				case 3:
					System.out.println("#Configuración\nNo.Reinas:15\tNo.Iteraciones:50000\tTemeratura:120\tFactor de enfriamiento:.95");
					res1 = sm.solucion(15, 50000, 120, .95);
					System.out.println("Tablero Solución");
					printTab(res1); System.out.println("Costo: "+sm.getCosto(res1));
				break;

				case 4:
					System.out.println("#Configuración\nNo.Reinas:4\tTamaño de población:10\tProbabilidad de mutación:.5\tNúmero de generaciones:50000");
					res2 = ga.solucion(4, 10, .5, 50000);
					System.out.println("Tablero Solución");
					printTab(res2); System.out.println("Costo: "+ga.getCosto(res2));
				break;
				
				case 5:
					System.exit(0);
				break;
				default:System.out.println("No se ha encontrado la opción, digite una opción valida del Menu.");
			}
		}
	}

	/**
	 * Imprime el tablero solucion.
	 * @param a el tablero a imprimir.
	 */
	public static void printTab(int[] a)
	{
		int[][] tablero = new int[a.length][a.length];
		for (int i=0; i<a.length; i++)
		{
			for (int j=0; j<a.length; j++)
			{
				if (a[i] == j)
					{tablero[i][j] = a[i];
					System.out.print("[Q] ");}
				else
					{tablero[i][j] = 0;
					System.out.print("[ ] ");}
			}
			System.out.println();
		}
	}
}

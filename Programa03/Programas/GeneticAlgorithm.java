import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ADLG.
 * <p>Clase con las operaciones necesarias para resolver el problema de las n Reinas con Genetic Algorithm.</p>
 */
public class GeneticAlgorithm
{
    /**
     * Devuelve la solución en forma de arreglo denotando las posiciones de las reinas.
     * @param n el número de reinas.
     * @param tamPoblacion el tamaño de la población.
     * @param probMutacion la probabilidad de la mutación.
     * @param generaciones el número de generaciones.
     * @return el arreglo con la solución.
     */
    public int[] solucion(int n, int tamPoblacion, double probMutacion, int generaciones)
    {
        tamPoblacion = tamPoblacion-(tamPoblacion%2);
        int[][] poblacion = generarPoblacion(n,tamPoblacion);
        int maxFitnss = n*(n-1)/2;

        int [] r = new int[n];
        System.out.println("\nTablero Inicial");
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<2; j++)
            {
                r[i]=poblacion[i+1][j+1];
            }
        }
        Main.printTab(r);
        System.out.println("Costo: "+getCosto(r)+"\n");

        for (int x = 0; x<generaciones; x++)
        {
            poblacion = getPoblacion(poblacion);
            poblacion = crossovers(poblacion,n);

            for (int i = 0; i<tamPoblacion; i++)
            {
                if (getFitness(poblacion[i]) == maxFitnss)
                    return poblacion[i];

                poblacion[i] = mutacion(poblacion[i],probMutacion);

                if (getFitness(poblacion[i]) == maxFitnss)
                    return poblacion[i];
            }
        }
        return null;
    }

    /**
     * Funcion que se encarga del manejo de los crossovers.
     * @param poblacion la población.
     * @param n las reinas a considerar.
     * @return el arreglo bidimensional con el crossover de la población.
     */
    private int[][] crossovers(int[][] poblacion, int n)
    {
        int crossoverPos,tmp;
        for (int i = 0; i<poblacion.length; i += 2)
        {
            crossoverPos = (int)(Math.random()*n);
            for (int j = 0; j<crossoverPos; j++)
            {
                tmp = poblacion[i][j];
                poblacion[i][j] = poblacion[i+1][j];
                poblacion[i+1][j] = tmp;
            }
        }
        return poblacion;
    }

    /**
     * Funcion que se encarga de obtener la población.
     * @param poblacion la población.
     * @return el arreglo bidimensional con la población.
     */
    private int[][] getPoblacion(int[][] poblacion)
    {
        Arrays.sort(poblacion,Comparator.comparingInt(this::getFitness));

        return poblacion;
    }

    /**
     * Funcion que se encarga del tema de la mutación.
     * @param a el arreglo al cual se le añadira la mutación.
     * @param probMutacion la probabilidad de la mutación.
     * @return el arreglo con la mutación.
     */
    private int[] mutacion(int[] a, double probMutacion)
    {
        if (probMutacion >= Math.random())
            a[(int)(Math.random()*a.length)] = (int)(Math.random()*a.length);

        return a;
    }

    /**
     * Funcion que obtiene el fitness de una solución.
     * @param a el arreglo del cual se quiere calcular el fitness.
     * @return el fitness en forma de int.
     */
    private int getFitness(int[] a)
    {
        return (a.length)*((a.length)-1)/2 - getCosto(a);
    }

    /**
     * Funcion que genera el cromosoma en forma de arreglo.
     * @param n el numero de reinas a considerar.
     * @return el cromosoma en forma de arreglo.
     */
    private int[] generarCromosoma(int n)
    {
        int [] a = new int[n];
        for (int i = 0; i<a.length; i++)
            {a[i] = (int)(Math.random()*a.length);}

        return a;
    }

    /**
     * Genera la población de manera random con ayuda de la función generarCromosoma().
     * @param n el numero de reinas a considerar.
     * @param tamPoblacion el tamaño de la población.
     * @return el costo de dicha solución.
     */
    private int [][] generarPoblacion(int n, int tamPoblacion)
    {
        int [][] poblacion = new int[tamPoblacion][];
        for (int i = 0; i<tamPoblacion; i++)
            {poblacion[i] = generarCromosoma(n);}

        return poblacion;
    }

    /**
     * Devuelve un el costo.
     * @param a el arreglo que contiene una solucion.
     * @return el costo de dicha solución.
     */
    public int getCosto(int[] a)
    {
        int costo = 0;
        for (int i = 0; i<a.length; i++)
        {
            for (int j = i+1; j<a.length; j++)
            {
                if (a[i] == a[j] || Math.abs(a[i]-a[j]) == j-i)
                    costo += 1;
            }
        }
        return costo;
    }
}
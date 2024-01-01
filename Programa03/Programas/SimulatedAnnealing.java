/**
 * @author ADLG.
 * <p>Clase con las operaciones necesarias para resolver el problema de las n Reinas con Simulated Annealing.</p>
 */
public class SimulatedAnnealing
{
    /**
     * Devuelve la solución en forma de arreglo denotando las posiciones de las reinas.
     * @param n el número de reinas.
     * @param repeticiones el número de iteraciones.
     * @param temperatura la temperatura.
     * @param velEnfriamiento la velocidad de enfriamiento.
     * @return el arreglo con la solución.
     */
    public int[] solucion(int n, int repeticiones, double temperatura, double velEnfriamiento)
    {
        int [] r = new int[n];
        r = randStateInicial(r);
        int costoObj = getCosto(r);

        System.out.println("\nTablero Inicial");
        Main.printTab(r);
        System.out.println("Costo: "+getCosto(r)+"\n");

        for (int x = 0; x<repeticiones && costoObj>0; x++)
        {
            r = hacerMov(r, costoObj, temperatura);
            costoObj = getCosto(r);
            temperatura = Math.max(temperatura*velEnfriamiento,0.01);
        }

        if (costoObj==0)
            return r;
        else
            return null;
    }

    /**
     * Realiza los movimientos teniendo en cuenta el costo objetivo junto con la temperatura.
     * @param a el arreglo que denota el estado aleatorio inicial.
     * @param costoObj el costo objetivo.
     * @param temperatura la temperatura.
     * @return el arreglo con la solución.
     */
    private int[] hacerMov(int[] a, int costoObj, double temperatura)
    {
        int n = a.length;
        int colum,fila,filaTemp,costo,costoax;
        double probAccept;
        while (true)
        {
            colum = (int)(Math.random()*n);
            fila = (int)(Math.random()*n);
            filaTemp = a[colum];
            a[colum] = fila;

            costo = getCosto(a);
            if (costo < costoObj)
                return a;

            costoax = costoObj-costo;
            probAccept = Math.min(1,Math.exp(costoax/temperatura));

            if (Math.random() < probAccept)
                return a;

            a[colum] = filaTemp;
        }
    }

    /**
     * Devuelve un arreglo con un estado aleatorio inicial.
     * @param a el arreglo a tratar.
     * @return el arreglo con el estado aleatorio inicial.
     */
    public int[] randStateInicial(int[] a)
    {
        for (int i = 0; i<a.length; i++)
            {a[i] = (int)(Math.random()*a.length);}

        return a;
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
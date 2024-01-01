import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

/**
* Clase para probar el funcionamiento correcto de Alcanzabilidad de la tarea Programa 01.
* En este metodo se muestra como se representara la grafica ademas de los componentes 
* que formaron la grafica de manera aleatoria.
* @author ADLG.
*/

public class Alcanzabilidad
{
    /* Main para probar el problema de Alcanzabilidad */
    public static void main(String[] args)
    {
        System.out.println("\t\t\t\t*** Alcanzabilidad ***\n\nUna grafica sera representada mediante vertices, aristas y una lista de adyacencia.\n");

        /*Creacion de la grafica y valores random. */
        Alcanzabilidad graficaRand = new Alcanzabilidad();
        Random random = new Random();
        int n1;
        n1 = random.nextInt(10)+1;

        /*Impresion de la grafica con sus componentes. */
        System.out.println("Grafica generada aleatoriamente:\nVertices         Aristas");
        for (int i = 0; i < graficaRand.lista_adyacencia.size(); i++)
        {
            System.out.print("   "+i + " \t   -> \t [ ");
            List<Integer> list = graficaRand.lista_adyacencia.get(i);

            if (list.isEmpty())
                {System.out.print("Sin aristas");}
            else
            {
                int size = list.size();
                for (int j = 0; j < size; j++)
                {
                    System.out.print(list.get(j));
                    if (j < size - 1)
                        {System.out.print(" , ");}
                }
            }
            System.out.println("]");
        }

        /*Fase Verificadora*/
        if (determina_Camino(graficaRand,0,n1))
        {
            System.out.println("\n/*Fase verificadora*/:\n¡SI! Existe un camino en la Grafica al cual se puede llegar del vertice 0 al vertice "+n1+".");
        }
        else
        {
            System.out.println("\n/*Fase verificadora*/:\n¡NO! Existe un camino en la Grafica al cual se puede llegar del vertice 0 al vertice "+n1+".");

        }
    }

    /* Inicializacion de variables y estructuras para generar aristas y vertices de manera random. */
    Random random = new Random();
    public int vertices;
    public int aristas;
    public List<List<Integer>> lista_adyacencia;
  
    /* Construye una grafica de manera random. */
    public Alcanzabilidad()
    {
        /* Creacion de numero de vertices y numero de aristas de manera random de tal manera que cumplan los requisitos. */
        this.vertices = (int)((Math.random()*11)+10);
        this.aristas = (int)(Math.random()* 3 + 15);
  
        /* Creacion de la lista de adyacencia. */
        lista_adyacencia = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++)
            {lista_adyacencia.add(new ArrayList<>());}
  
        /* Creacion de aristas de manera random comprobando si ya existe una arista entre dos vertices. */
        for (int i = 0; i < aristas; i++)
        {
            int v = random.nextInt(vertices);
            int w = random.nextInt(vertices);

            if (lista_adyacencia.get(v).contains(w))
            {
                i--;
                continue;
            }
            agrega_arista(v, w);
        }
    }

    /**
     * Agrega aristas entre dos vertices.
     * @param v1 vertice 1.
     * @param v2 vertice 2.
     */
    public void agrega_arista(int v1, int v2)
    {
        lista_adyacencia.get(v1).add(v2);

        if (v1 != v2)
            {lista_adyacencia.get(v2).add(v1);}
    }

    /**
     * Verifica si hay un camino de s a t .
     * @param grafica la grafica donde se buscara un camino.
     * @param s inicio del camino (vertice de inicio).
     * @param t fin del camino (vertice objetivo).
     * @return true si existe un camino, false si no existe un camino.
     */
    public static boolean determina_Camino(Alcanzabilidad grafica, int s, int t)
    {
        /* Se verifican el numero total de vertices de la grafica y se inicializan los valores necesarios para buscar un camino de s a t. */
        int n = grafica.lista_adyacencia.size();
        boolean[] descubierto = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        descubierto[s] = true;
        q.add(s);
        List<Integer> camino = new ArrayList<>();
        Random random = new Random();
        Integer numero;
 
        while (!q.isEmpty())
        {
            /* Elimina el elemento de la parte frontal de la Cola. */
            int v = q.poll();

            /* Si existe un camino imprime los vertices candidatos por los cuales se puede viajar desde s a t. */
            if (v == t)
            {
                camino.remove(Integer.valueOf(0));
                System.out.println("Vertices por los cuales se podria viajar para generar un camino (sin repetición) de s a t: "+camino);
                return true;
            }

            /* Recorre la lista de adyacencia de la grafica para verificar vertices y aristas candidatos. */
            System.out.println("/*Fase Adivinadora*/\nCandidatos a Solucion:"+camino);
            for (int u: grafica.lista_adyacencia.get(v))
            {
                /*Fase Adivinadora*/

                /* Si el vertice tiene mas de una arista escoge una arista random mediante un numero generado conforme a las aristas de dicho vertice
                   de lo contrario solo viaja por la unica arista que tiene el vertice y se marcan en la cola creada anteriormente. */
                if ((grafica.lista_adyacencia.get(v)).size() > 1)
                {
                    numero = random.nextInt((grafica.lista_adyacencia.get(v)).size());
                    numero = (grafica.lista_adyacencia.get(v)).get(numero);
                    if (!descubierto[numero])
                    {
                        descubierto[numero] = true;
                        q.add(numero);
                        if (!camino.contains(numero))
                            {camino.add(numero);}
                    }
                    else
                    {
                        if (!camino.contains(u))
                            {camino.add(u);}
                    }
                }
                else
                {
                    if (!descubierto[u])
                    {
                        descubierto[u] = true;
                        q.add(u);
                         if (!camino.contains(u))
                            {camino.add(u);}
                    }
                }

            }
        }
        return false;
    }
}
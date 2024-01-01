import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase para probar el funcionamiento correcto de 3-SAT de la tarea Programa 01.
 * Principalmente aqui se crean todos los valores necesarios para resolver 3-SAT,
 * tambien las estructuras que guardaran las interpretaciones que podran toma
 * las literales, ademas de los valores random para elegir una interpretacion
 * y variables que formaran parte de las Clausulas.
 * @author ADLG.
 */


public class TresSAT
{
    /* Main para probar el problema 3 SAT. */
    public static void main(String[] args)
    {
        System.out.println("\t\t\t\t*** 3-SAT ***\n\nCada clausula sera representada por numeros 1's y 0's Ejemplo: [[1,1,1], [0,0,0]]"
            +"\nEl 0 representa variables negadas mientras que el 1 representa la variable tal cual.");

        /* Creacion de numeros random para elegir las variables de las Clausulas. */
        int n1,n2,n3,n4,n5,n6;
        Random random = new Random();
        n1 = random.nextInt(8);
        n2 = random.nextInt(8);
        n3 = random.nextInt(8);
        n4 = random.nextInt(8);
        n5 = random.nextInt(8);
        n6 = random.nextInt(8);

        /* Creacion de estructuras para guardar valores de las variables e interpretaciones para asignar a las Clausulas. */
        List<List<Integer>> lista_Clausulas = new ArrayList<>();
        List<List<Integer>> lista_variables = new ArrayList<>();
        List<List<Boolean>> lista_soluciones = new ArrayList<>();

    	List<Integer> l1 = Arrays.asList(0,0,0); List<Boolean> l_000 = Arrays.asList(false,false,false);
        List<Integer> l2 = Arrays.asList(1,1,1); List<Boolean> l_111 = Arrays.asList(true,true,true);
        List<Integer> l3 = Arrays.asList(0,0,1); List<Boolean> l_001 = Arrays.asList(false,false,true);
        List<Integer> l4 = Arrays.asList(1,1,0); List<Boolean> l_110 = Arrays.asList(true,true,false);
        List<Integer> l5 = Arrays.asList(1,0,0); List<Boolean> l_100 = Arrays.asList(true,false,false);
        List<Integer> l6 = Arrays.asList(0,1,1); List<Boolean> l_011 = Arrays.asList(false,true,true);
        List<Integer> l7 = Arrays.asList(1,0,1); List<Boolean> l_101 = Arrays.asList(true,false,true);
        List<Integer> l8 = Arrays.asList(0,1,0); List<Boolean> l_010 = Arrays.asList(false,true,false);

        /* Guardando los valores para las variables e interpretaciones. */
        lista_variables.add(l1);lista_variables.add(l2);lista_variables.add(l3);lista_variables.add(l4);
        lista_variables.add(l5);lista_variables.add(l6);lista_variables.add(l7);lista_variables.add(l8);
        lista_soluciones.add(l_000);lista_soluciones.add(l_111);lista_soluciones.add(l_001);lista_soluciones.add(l_110);
        lista_soluciones.add(l_100);lista_soluciones.add(l_011);lista_soluciones.add(l_101);lista_soluciones.add(l_010);

        /*Fase Adivinadora*/
        System.out.println("/*Fase Adivinadora*/\nCandidato a Solucion:"+lista_soluciones.get(n6));
        /* Creacion de forma aleatoria del conjunto de Clausulas (5 Clausulas con 3 literales cada una).*/
        lista_Clausulas.add(lista_variables.get(n1));
        lista_Clausulas.add(lista_variables.get(n2));
        lista_Clausulas.add(lista_variables.get(n3));
        lista_Clausulas.add(lista_variables.get(n4));
        lista_Clausulas.add(lista_variables.get(n5));

        /* Aplicacion de interpretaciones a las Clausulas en "lista_Clausulas" con una solucion escogida de manera aleatoria */
        List<Boolean> lista_sol = new ArrayList<>();
        lista_sol.add(solucion(lista_Clausulas.get(0),lista_soluciones.get(n6)));
        lista_sol.add(solucion(lista_Clausulas.get(1),lista_soluciones.get(n6)));
        lista_sol.add(solucion(lista_Clausulas.get(2),lista_soluciones.get(n6)));
        lista_sol.add(solucion(lista_Clausulas.get(3),lista_soluciones.get(n6)));
        lista_sol.add(solucion(lista_Clausulas.get(4),lista_soluciones.get(n6)));

        /*Fase Verificadora*/
        if (lista_sol.contains(false))
        {
            System.out.println("\n/*Fase Verificadora*/\nEsta lista Clausulas: "+lista_Clausulas+"\n¡NO! es satisfacible asignando los siguientes valores en orden a cada Clausula:"+lista_soluciones.get(n6));
        }
        else
        {
            System.out.println("\n/*Fase Verificadora*/\nEsta lista Clausulas: "+lista_Clausulas+"\n¡SI! es satisfacible asignando los siguientes valores en orden a cada Clausula:"+lista_soluciones.get(n6));
        }
    }

    /**
     * Aplica interpretaciones a una Clausula dada.
     * @param l1 lista de variables (Una Clausula con 3 literales).
     * @param l2 lista de interpretaciones.
     * @return true si la evaluacion de las literales con los or fue verdadera, false si todas las literales tienen valor falso.
     */
    public static boolean solucion(List<Integer> l1, List<Boolean> l2)
    {
        List<Boolean> aplicacion_Interp = new ArrayList<>();
        int i=0;

        /* Recorre la lista de literales dada para asignar la interpretacion dada por la lista l2. */
        for (Integer e: l1)
        {
            /* El caso para las variables negadas. */
            if (e==0)
            {
                if (l2.get(i)==false)
                    {aplicacion_Interp.add(true);}
                else
                    {aplicacion_Interp.add(false);}
            }
            else
                {aplicacion_Interp.add(l2.get(i));}
            i++;
        }

        if (aplicacion_Interp.contains(true))
            {return true;}
        else
            {return false;}
    }
}

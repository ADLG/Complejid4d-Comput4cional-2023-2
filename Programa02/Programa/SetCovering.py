#!/usr/bin/env python
# -*- coding: utf-8 -*-

__author__ = "ADLG"
"""
SetCovering.py
------------
Programa 02.
"""
import QueueOps

def set_covering(S, w):
    """ Como se esta haciendo uso de colas, la prioridad es un factor a considerar a
    la ahora de agregar los conjuntos a dicha cola, entonces se buscara el conjunto S mas
    optimo y mas adelante se actualizaran los conjuntos que contiengan los elementos
    cubieros para devolver la solucion con los indices de los conjuntos en F y el costo. """

    entradas = {}
    l = list()
    S1 = []
    opsCola = QueueOps.QueueOps()
    costo = 0
    cubierta = 0

    for i, elem in enumerate(S):
        S1.append(set(elem))
        for j in elem:
            if j != entradas:
                entradas[j] = set()
            entradas[j].add(i)

    for i, elem in enumerate(S1):
        if len(elem) == 0:
            opsCola.addpoints(i,100)
        else:
            opsCola.addpoints(i, float(w[i])/len(elem))
    while cubierta < len(entradas):
        a = opsCola.poppoints()
        l.append(a)
        costo += w[a]
        cubierta += len(S1[a])
        for m in S1[a]:
            for n in entradas[m]:
                if n != a:
                    S1[n].discard(m)
                    if len(S1[n]) == 0:
                        opsCola.addpoints(n,100)
                    else:
                        opsCola.addpoints(n, float(w[n])/len(S1[n]))
        S1[a].clear()
        opsCola.addpoints(a,100)          
    return l, costo

if __name__ == "__main__":
    print("\t  ***Programa 02***\n\n- Set-Covering Problem -")

    print("Ejemplar 1")
    S = [[1, 2],[2, 3, 4, 5],[6, 7, 8, 9, 10, 11, 12, 13],[1, 3, 5, 7, 9, 11, 13],[2, 4, 6, 8, 10, 12, 13]]
    F = [1, 2, 3, 4, 5]
    l, costo = set_covering(S, F)
    print ("Ejemplar de entrada: \nF =",S)
    print ("\nDetalles de la solución:\nIndice de conjuntos en F:", l)
    print ("Costo:", costo)
    print("\nEjemplar 2")
    S = [[4,1,3],[2,5],[1,4,3,2]]
    F = [1,2,3,4,5]
    l, costo = set_covering(S, F)
    print ("Ejemplar de entrada: \nF =",S)
    print ("\nDetalles de la solución:\nIndice de conjuntos en F:", l)
    print ("Costo:", costo)
    print("\nEjemplar 3")
    S = [[1,3,4,6,7],[4,7,8,12],[2,5,9,11,13],[1,2,14,15],[3,6,10,12,14],[8,14,15],[1,2,6,11],[1,2,4,6,8,12]]
    F = [1,2,3,4,5,6,7,8]
    l, costo = set_covering(S, F)
    print ("Ejemplar de entrada: \nF =",S)
    print ("\nDetalles de la solución:\nIndice de conjuntos en F:", l)
    print ("Costo:", costo)
    print("\nEjemplar 4")
    S = [[1,2,3],[2,4],[3,4],[4,5]]
    F = [1,2,3,4,5]
    l, costo = set_covering(S, F)
    print ("Ejemplar de entrada: \nF =",S)
    print ("\nDetalles de la solución:\nIndice de conjuntos en F:", l)
    print ("Costo:", costo)
    print("\nEjemplar 5")
    S = [[1,2,3,8,9,10],[1,2,3,4,5],[4,5,7],[5,6,7],[6,7,8,9,10]]
    F = [1,2,3,4,5]
    l, costo = set_covering(S, F)
    print ("Ejemplar de entrada: \nF =",S)
    print ("\nDetalles de la solución:\nIndice de conjuntos en F:", l)
    print ("Costo:", costo)

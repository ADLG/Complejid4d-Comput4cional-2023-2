#!/usr/bin/env python
# -*- coding: utf-8 -*-

__author__ = "ADLG"
"""
QueueOps.py
------------
Programa 02.
"""
import itertools
from heapq import *

class QueueOps:
    def __init__(self):
        self.qOps = []
        self.entradas = {}
        self.cont = itertools.count()

    def addpoints(self, points, prioridad = 0):
        """ Agrega un punto o actualiza una prioridad """
        if points in self.entradas:
            self.delpoints(points)
        count = next(self.cont)
        principal = [prioridad, count, points]
        self.entradas[points] = principal
        heappush(self.qOps, principal)

    def delpoints(self, points):
        """ Marca un punto como eliminado """
        principal = self.entradas.pop(points)
        principal[-1] = 'eliminado'

    def poppoints(self):
        """ Elimina un punto y devuleve el de menor prioridad """
        while self.qOps:
            prioridad, count, points = heappop(self.qOps)
            if points != 'eliminado':
                del self.entradas[points]
                return points

    def __len__(self):
        return len(self.entradas)

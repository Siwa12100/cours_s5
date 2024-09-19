def tri_A(tab):
    # On itère sur chaque indice du tableau
   for i in range(len(tab)):
       # On considère que le min est à l'indice courant
       min = i
       # On itère de i + 1 jusqu'à la fin du tableau
       for j in range(i+1, len(tab)):
           
           # Si la valeur à l'indice i est supérieure
           # à la valeur à l'indice j
           if tab[min] > tab[j]:
               # Alors on dit que le minimum est mtn à 
               # l'indice j
               min = j
        # On met à l'indice i la valeur min et à j
        # l'ancienne valeur de i               
       tmp = tab[i]
       tab[i] = tab[min]
       tab[min] = tmp
       
       # A chaque itération sur un indice du tableau
       # (en partant du début), on va récupèrer la valeur
       # minimale dans le reste du tableau et on le met à 
       # l'indice courant.
       
       # On se retouve à la fin avec toutes les valeurs classées
       # par ordre croissant.
   return tab


def selsoro(t):
    
    # On itère du début du tableau jusqu'à
    # son avant dernière valeur 
    for j in range(len(t)-1):
        # On fait une sous liste à partir de la liste t qui part de l'indice j
        # jusqu'à la fin de la liste (t[j:]). 
        # Sur cette sous liste, on récupère la première occurence de la valeur 
        # minimale contenue dans la sous liste allant de l'indice j à la fin du 
        # tableau.
        # On rajoute + j car par exemple si la sous tableau commence à l'indice j =  4 et 
        # que le .index() renvoie 3, en fait la position réelle du min dans le tableau n'est 
        # pas 3 mais 3 + j.
        
        # En gros, cette ligne récupère l'indice de la valeur minimale entre l'indice courant et 
        # la fin du tableau
        i = t[j:].index(min(t[j:]))+j
        
        # Si l'indice de la valeur minimale n'est pas égal à j, c'est à dire l'indice 
        # où doit se trouver au final la valeur minimale du sous tableau
        if i != j:
            # Alors on intervertie les valeurs aux indices i et j de manière à ce que 
            # la valeur min se trouve bien au début du sous tableau commençant à l'indice j. 
            t[i], t[j] = t[j], t[i]
    return t

def tri_C(liste):
    # On déclare le tableau final 
    r=[]
    # On boucle tant que la liste n'est pas vide
    while liste:
        # On défini le minimum comme étant au début de la liste
        mini=liste[0]
        
        # On récupère le minimum dans la liste restante
        for z in liste:
            if z < mini:
                mini=z
        
        # On supprime le minimum de la liste restante
        liste.remove(mini)
        # On l'ajoute à la liste finale
        r.append(mini)
    return r


def tri_D(liste):       
    
    # On parcours l'ensemble des indices de la liste      
    for i in range(len(liste)):
        # On considère le minimum comme étant au début de la sous liste allant de 
        # i à la fin de la liste
        mini = i
        
        # On récupère l'indice du minimum dans la sous liste
        for j in range(i+1, len (liste)):
            if liste[j] < liste[mini] :
                mini = j
                
        # On s'assure que la valeur min soit bien à l'indice i
        liste[i], liste[mini] = liste[mini], liste[i]
    return liste


def tri_E(liste) :
    
    # On parcours la liste jusqu'à l'avant dernière valeur        
    for i in range(len(liste)-1):
        
        k = i+1 
        cle = liste[k]
        while cle < liste[k-1] and k>0:
            liste[k] = liste[k-1]
            k = k-1 
        liste[k] = cle
    return liste

# =======
# Exo 1 :
# =======
import matplotlib.pyplot as plt
import time

# 1.)
# ---

# Le tri A et selsoro fonctionnent de la même manière. Comme un tri à bulle, 
# mais on ne fait pas remonter la valeur la plus haute à la fin du tableau, 
# on fait remonter la valeur la plus basse au début du tableau.
# En gros c'est ce qu'on appelle un tri par insertion...
# Tri D aussi par insertion.

tabTest = [1, 23, 21, 18, 17, 78, 71, 9, 1872]

debut_temps = time.time()
resultat = tri_A(list(tabTest))
fin_temps = time.time()
intervalle_temps = fin_temps - debut_temps

print("temps mis pour tri A : " + intervalle_temps)
# print("- Test tri_A : ", tri_A(list(tabTest)))
# print("- Test selsoro : ", selsoro(list(tabTest)))
# print("- Test tri_C : ", tri_C(list(tabTest)))
# print("- Test tri_D : ", tri_D(list(tabTest)))
# print("- Test tri_E : ", tri_E(list(tabTest)))
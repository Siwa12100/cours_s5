def tri_A(tab):
   for i in range(len(tab)):
       min = i
       for j in range(i+1, len(tab)):
           if tab[min] > tab[j]:
               min = j                
       tmp = tab[i]
       tab[i] = tab[min]
       tab[min] = tmp
   return tab


def selsoro(t):   
    for j in range(len(t)-1):
        i = t[j:].index(min(t[j:]))+j
        if i != j:
            t[i], t[j] = t[j], t[i]
    return t


def tri_C(liste):
    r=[]
    while liste:
        mini=liste[0]
        for z in liste:
            if z < mini:
                mini=z
        liste.remove(mini)
        r.append(mini)
    return r


def tri_D(liste):            
    for i in range(len (liste)):
        mini = i      
        for j in range(i+1, len (liste)):
            if liste[j] < liste[mini] :
                mini = j
        liste[i], liste[mini] = liste[mini], liste[i]
    return liste


def tri_E(liste) :            
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

# 1.)
# ---

tabTest = [1, 23, 21, 18, 17, 78, 71, 9, 1872]
print("- Test tri_A : ", tri_A(tabTest))
print("- Test selsoro : ", selsoro(tabTest))
print("- Test tri_C : ", tri_C(tabTest))
print("- Test tri_D : ", tri_D(tabTest))
print("- Test tri_E : ", tri_E(tabTest))
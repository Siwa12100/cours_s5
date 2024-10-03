# ============
# Exercice 1 :
# ============

ListePiecesBillets = [1, 2, 5, 10, 20, 50, 100, 200, 500]
# ListePiecesBillets = [5, 10, 20, 25]

def MonnaieRec(n , L) : 
    resultat = []
    for val in L :
        if val == n : 
            resultat.append(val)
            return resultat 
        
    if len(L) == 1 : 
        return []
    
    index = len(L)
    d = L[index - 1]
    
    while n >= d :
        resultat.append(d)
        n = n - d
        
    listeReduite = []
    listeReduite = L.copy()
    listeReduite.pop(index - 1)
    return MonnaieRec(n, listeReduite) + resultat
    
def MonnaieRec_Connifiee(n ,L) : 
    return len(MonnaieRec(n,L))

# print("Rep : ",  MonnaieRec(40, ListePiecesBillets.copy()))
# print("liste : ", ListePiecesBillets)
# print("Rep : ",  MonnaieRec(36, ListePiecesBillets.copy()))
# print("liste : ", ListePiecesBillets)
# print("Rep : ",  MonnaieRec_Connifiee(8, ListePiecesBillets))



def MonnaieProgDyn(n, L) :

    valeurs = []
    print("n : ", n)


    for i in range(n):
        # print("i : ", i)
        temp = MonnaieRec(i, L.copy())
        nbRendu = MonnaieRec_Connifiee(i, L.copy())
        valeurs.append(nbRendu)
        # print("Temp : ", temp, " ; nbRendu : ", nbRendu)


    return valeurs 


# ret = MonnaieProgDyn(30, ListePiecesBillets)

# cpt = 0
# for valeur in ret :
#     print("v ---> ", valeur, "res ---> ", MonnaieRec(cpt, ListePiecesBillets.copy()))
#     cpt+= 1


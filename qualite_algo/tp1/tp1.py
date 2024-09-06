import matplotlib.pyplot as plt
import time

import resource,sys
resource.setrlimit(resource.RLIMIT_STACK, (2**29,-1))
sys.setrecursionlimit(10**6)

# Fibo : 
# Si n = 0 ou n = 1, F (n) = 1
# Si n > 1, F (n) = F (n − 2) + F (n − 1)

# Exo 1 : 
# =======

# 1.)
# ---
def FiboRec(n) : 
    if n == 0 or n == 1 :
        return 1

    if n > 1 :
        return FiboRec(n - 2) + FiboRec(n - 1)

# 2.)
# ---
def  FiboIter(n) :
    valeur = []
    cpt = 0

    while cpt <= n :
        if cpt == 0 or cpt == 1 :
            valeur.append(1)

        if cpt > 1 :
            valeur.append(valeur[cpt-1] + valeur[cpt-2])

        cpt = cpt + 1
    return valeur[n]

# n = 5
# # resultat = FiboRec(n)
# resultat = FiboIter(n)
# print(resultat)


# 4.)
# ---
def afficher_courbes_temps(valeurs, temps) : 
    plt.title("Calcul des nombres de Fibonacci")
    plt.xlabel("Valeur de n")
    plt.ylabel("Dur´ee en secondes")
    plt.plot(valeurs,"blue")
    plt.plot(temps,"red")
    plt.show()


def test_temps_fibo_iter(n) :
    cpt = 0
    resultat = 0
    resultats = []
    temps = []

    while cpt != (n + 1) :
        debut_temps = time.time()
        resultat = FiboIter(cpt)
        temps.append(time.time() - debut_temps)
        resultats.append(resultat)
        print("Resultat fiboIter(", cpt,") --> ", resultat, ". tps mis : ", temps[cpt])
        cpt = cpt + 1

    afficher_courbes_temps(resultats, temps)

def test_temps_fibo_Recur(n) :
    cpt = 0
    resultat = 0
    resultats = []
    temps = []

    while cpt != (n + 1) :
        debut_temps = time.time()
        resultat = FiboRec(cpt)
        temps.append(time.time() - debut_temps)
        resultats.append(resultat)
        print("Resultat fiboIter(", cpt,") --> ", resultat, ". tps mis : ", temps[cpt])
        cpt = cpt + 1

    afficher_courbes_temps(resultats, temps)

# test_temps_fibo_iter(50)
# test_temps_fibo_Recur(50)



# Exercice 2 :
# ============
    
# 2.)
def Ackermann(m, n) :
    resultat = 0

    if m == 0 :
        resultat = n + 1
        return resultat
    
    if m > 0 and n == 0 :
        resultat = Ackermann(m - 1, 1)
        return resultat
    
    resultat = Ackermann(m - 1, Ackermann(m, n - 1))
    return resultat


m = 4
n = 12

m_courant = 0

# 3.)
# ---
# while m_courant <= m :
#     n_courant = 0

#     while n_courant <= n :
#         resultat = Ackermann(m_courant, n_courant)
#         print("Ackerman(", m_courant, ", ", n_courant, ") --->", resultat)
#         n_courant = n_courant + 1
    
#     m_courant = m_courant + 1


# Exercice 3 :
# ============

def exp_basique(x, n, p) : 
    
    cpt = 1
    resultat = 0
    while cpt <= n : 
        resultat = x * x
        cpt = cpt + 1
    
    return resultat % p

# print("Exp_basique(4, 4, 10): ", exp_basique(4, 4, 10))

def puissance(x, n) :

    if n == 1 : 
        return x

    if n % 2 ==  0 :
        return puissance(x * x, x / 2)
    
    return x * puissance (x * x, (n - 1) / 2)


def exp_rapide(x, n, p) :
    return puissance(x, n) % p

print("Exp_rapide(4, 4, 10): ", exp_rapide(4, 4, 10))

debut_temps = time.time()
exp_basique(123456789, 123456789, 987654321)
print("test calcul avec basique. tps ---> ", time.time() - debut_temps)

debut_temps = time.time()
exp_rapide(123456789, 123456789, 987654321)
print("test calcul avec rapide. tps ---> ", time.time() - debut_temps)


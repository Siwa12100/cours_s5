import matplotlib.pyplot as plt
import time

# on suppose que les valeurs des temps sont stock´ees dans
# deux listes T0 et T1, chacune de la mˆeme taille
plt.title("Calcul des nombres de Fibonacci")
plt.xlabel("Valeur de n")
plt.ylabel("Dur´ee en secondes")
plt.plot(T0,"blue")
plt.plot(T1,"red")
plt.show()
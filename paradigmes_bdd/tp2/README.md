# Projet Redis - Jean Marcillac WEB 1 

## Fonctionnalités

Le projet se décompose en deux menus. Celui pour l'éditeur de la bibliothèque, et celui pour les différents clients.

### Menu editeur

L'éditeur peut : 

* Ajouter des livres
* Supprimer des livres
* Voir l'ensemble des livres de la bibliothèque
  
### Menu client

Le menu des clients commence par faire choisir le client que l'on souhaite incarner. 3 clients sont disponibles, de manière à pouvoir tester des fonctionnalités comme le fait que si certains clients ont loué l'ensemble des exemplaire d'un livre, alors le dernier client ne pourra plus en louer. 

Le client peut ainsi :

- Voir l'ensemble des livres de la bibliothèque
- Voir les livres qu'il loue 
- Louer l'un des livres disponibles
- Rendre ses livres loués
- S'abonner à un canal de news 

Lorqu'un livre est édité ou bien supprimé, un client peut en être notifié. Pour cela, il doit s'abonner à un canal spéficique. Par exemple, s'il choisi le canal "titre" et le filtre "Les fleurs du mal", alors il sera notifié lorque le livre ayant pour titre "Les fleurs du mal" est édité ou bien supprimé. 

## Lancement du projet

Le projet utilisant Jedis, une bibliothèque permettant d'intéragir avec Redis en java, Maven a été installé pour gérer les dépendances. Pour installer maven sous debian, un `sudo apt-get install maven` fonctionne. 

Ensuite pour lancer le projet, il suffit de rentrer la commande suivante à la racine du projet (là où se trouve le pom.xml) : `mvn exec:java -Dexec.mainClass="jeanmarcillac.Main"`. 
Il est tout à fait possible de lancer plusieurs sessions du projet, de manière par exemple à pouvoir tester les fonctionnalités de canaux.

## Autres détails utiles à savoir

### Déployement d'un conteneur Redis

De manière à ce que le projet puisse tourner sans nécessiter les machines de l'IUT, j'ai pris soin de déployer sur un VPS personnel un service Redis dans un conteneur docker. C'est depuis ce service que le projet accède aux données. Il est donc possible de lancer ce projet depuis n'importe qu'elle machine, et le système de pub/sub marche sans soucis entre des machines différentes.

### Avertissement

Peu de vérifications sur les valeurs rentrées ont été éffectuées. Dans ce sens, si vous ne rentrez pas par exemple une valeur lorsque cela est demandé, cela peut faire crasher le programme. Il est donc préférable de bien rentrer le type de valeur demandé, pour minimiser les potentiels soucis.


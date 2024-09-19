# TP 3 - NoSQL Base de donn´ees graphe

## Reponses

### 1.)

```sql
CREATE (e:Etudiant {NumEtudiant : 172, Nom : 'Richard', Prenom : 'Ana', Prenom2 : 'Maria' })
CREATE (e:Etudiant {NumEtudiant : 284, Nom : 'Leroux', Prenom : 'Nicolas', Prenom2 : 'Jean' })
CREATE (e:Etudiant {NumEtudiant : 145, Nom : 'Marc', Prenom : 'Alex'})
CREATE (e:Etudiant {NumEtudiant : 189, Nom : 'Bern', Prenom : 'Clara'})
CREATE (e:Etudiant {NumEtudiant : 187, Nom : 'Lavoisier', Prenom : 'Sarah'})
```

```sql
CREATE (c:cours {NumCours : 1, NomCours : "Science des donnees"})
CREATE (c:cours {NumCours : 2, NomCours : "Base de donnees"})
CREATE (c:cours {NumCours : 3, NomCours : "virtualisation"})
```

```sql
CREATE (p:Projet {NumProjet : 34, NomProjet : "data mining"})
CREATE (p:Projet {NumProjet : 44, NomProjet : "analyse des donnees"})
CREATE (p:Projet {NumProjet : 3, NomProjet : "machine learning"})
CREATE (p:Projet {NumProjet : 51, NomProjet : "virtualisation"})
```

```sql
CREATE (s:Salle {NomSalle : "301"})
CREATE (s:Salle {NomSalle : "Amphi"})
CREATE (s:Salle {NomSalle : "Lardy_108"})
CREATE (s:Salle {NomSalle : "401"})
CREATE (s:Salle {NomSalle : "Lardy_110"})
```

### 2.)

```sql
MATCH (c:cours), (s:Salle) WHERE c.NumCours = 1 AND s.NomSalle = "301" CREATE (c)-[r:PrendPlaceA]->(s)
MATCH (c:cours), (s:Salle) WHERE c.NumCours = 1 AND s.NomSalle = "Lardy_108" CREATE (c)-[r:PrendPlaceA]->(s)
MATCH (c:cours), (s:Salle) WHERE c.NumCours = 1 AND s.NomSalle = "401" CREATE (c)-[r:PrendPlaceA]->(s)
MATCH (c:cours), (s:Salle) WHERE c.NumCours = 2 AND s.NomSalle = "Amphi" CREATE (c)-[r:PrendPlaceA]->(s)
```

```sql
MATCH (e:Etudiant), (c:cours) WHERE c.NumCours = 1 AND e.NumEtudiant = 172 CREATE (e)-[r:Suit]->(c)
MATCH (e:Etudiant), (c:cours) WHERE c.NumCours = 1 AND e.NumEtudiant = 284 CREATE (e)-[r:Suit]->(c)
MATCH (e:Etudiant), (c:cours) WHERE c.NumCours = 2 AND e.NumEtudiant = 145 CREATE (e)-[r:Suit]->(c)
MATCH (e:Etudiant), (c:cours) WHERE c.NumCours = 1 AND e.NumEtudiant = 189 CREATE (e)-[r:Suit]->(c)
```

```sql
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 34 AND e.NumEtudiant = 172 CREATE (e)-[r:TravailleSur {Heure : "1"}]->(p)
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 44 AND e.NumEtudiant = 172 CREATE (e)-[r:TravailleSur {Heure : "2"}]->(p)
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 34 AND e.NumEtudiant = 284 CREATE (e)-[r:TravailleSur {Heure : "3"}]->(p)
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 44 AND e.NumEtudiant = 284 CREATE (e)-[r:TravailleSur {Heure : "4"}]->(p)
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 3 AND e.NumEtudiant = 284 CREATE (e)-[r:TravailleSur {Heure : "1"}]->(p)
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 51 AND e.NumEtudiant = 284 CREATE (e)-[r:TravailleSur {Heure : "1"}]->(p)
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 34 AND e.NumEtudiant = 145 CREATE (e)-[r:TravailleSur {Heure : "1"}]->(p)
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 44 AND e.NumEtudiant = 145 CREATE (e)-[r:TravailleSur {Heure : "2"}]->(p)
MATCH (e:Etudiant), (p:Projet) WHERE p.NumProjet = 51 AND e.NumEtudiant = 145 CREATE (e)-[r:TravailleSur {Heure : "3"}]->(p)
```

### 3.)

```sql
CREATE (e:Etudiant {NumEtudiant : 100, Nom : "Marcilhac", Prenom : "Jean"})
```

### 4.)

```sql
CREATE (c:cours {NumCours : 4, NomCours : "NoSql"})
MATCH (e:Etudiant), (c:cours) WHERE e.NumEtudiant = 100 And c.NumCours = 4 CREATE (e)-[r:Suit]->(c)
```

### 5.)

```sql




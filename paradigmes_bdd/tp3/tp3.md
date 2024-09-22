# TP 3 - NoSQL Base de données graphe

## 1.) Création des nœuds

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

## 2.) Création des relations

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

## 3.) Ajouter un autre étudiant portant votre nom et prénom

```sql
CREATE (e:Etudiant {NumEtudiant : 100, Nom : "Marcilhac", Prenom : "Jean"})
```

## 4.) Créer le cours C4 et lier l’étudiant à ce cours

```sql
CREATE (c:cours {NumCours : 4, NomCours : "NoSql"})
MATCH (e:Etudiant), (c:cours) WHERE e.NumEtudiant = 100 And c.NumCours = 4 CREATE (e)-[r:Suit]->(c)
```

## 5.) Dans quelles salles les cours avec le numéro de cours ”1” ont-ils lieu?

```sql
MATCH (c:cours {NumCours : 1}) -- (s:Salle) 
RETURN c.NomCours, s.NomSalle
```

## 6.) Combien d’heures et dans quels projets l’étudiant avec le numéro d’étudiant ”172” travaille-t-il?

```sql
MATCH (e:Etudiant {NumEtudiant: 172})-[r:TravailleSur]->(p:Projet)
RETURN e.Prenom, p.NomProjet, r.Heure
```

## 7.) Quels étudiants et combien d’heures travaillent-ils sur le projet portant le numéro de projet ’51’?

```sql
MATCH (e:Etudiant)-[r:TravailleSur]->(p:Projet {NumProjet: 51})
RETURN p.NomProjet, e.Nom, r.Heure
```

## 8.) Quels étudiants travaillent dans quels projets et combien d’heures?

```sql
MATCH (e:Etudiant)-[r:TravailleSur]->(p:Projet)
RETURN e.Nom, p.NomProjet, r.Heure
ORDER BY e.Nom
LIMIT 4
```

## 9.) Quels étudiants travaillent sur plus de deux projets et sur combien de projets exactement?

```sql
MATCH (e:Etudiant)-[r:TravailleSur]->(p:Projet)
WITH e, COUNT(p) as NbProjets
WHERE NbProjets > 2
RETURN e.Nom, NbProjets
ORDER BY NbProjets DESC
```

## 10.) Quels étudiants ont le même nom de famille et travaillent sur les mêmes projets?

```sql
MATCH (e1:Etudiant)-[r1:TravailleSur]->(p:Projet)<-[r2:TravailleSur]-(e2:Etudiant)
WHERE e1.Nom = e2.Nom AND e1.NumEtudiant <> e2.NumEtudiant
RETURN e1.Prenom, e2.Prenom, p.NomProjet
```

## 11.) Quelle est la durée moyenne que les étudiants passent sur un projet?

```sql
MATCH (e:Etudiant)-[r:TravailleSur]->(p:Projet)
RETURN AVG(TOFLOAT(r.Heure)) AS DureeMoyenne
```

## 12.) Quels étudiants suivent le même cours et travaillent sur le même projet?

```sql
MATCH (e1:Etudiant)-[:Suit]->(c:cours)<-[:Suit]-(e2:Etudiant),
      (e1)-[:TravailleSur]->(p:Projet)<-[:TravailleSur]-(e2)
RETURN e1.Prenom, e2.Prenom, c.NomCours, p.NomProjet
```

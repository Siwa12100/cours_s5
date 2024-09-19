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


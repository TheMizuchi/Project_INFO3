---
title: DONJON ET BALLONS
author: Groupe 3
---

## Principe du jeu

Ce jeu de coopération entre 2 joueurs se déroule dans un donjon. Le donjon est constitué de 3 étages (chacun généré aléatoirement) pour chaque étage, une salle contient la clé pour accéder à la salle du Boss. Pour gagner il faut vaincre les Boss de chacun des 3 étages.

## Vidéo de présentation du jeu

https://youtu.be/RQkvCTKLgik

## Pourcentage du projet

Nous avons équitablement réparti le travail du projet, c'est pourquoi nous avons choisi d'attribuer à tous les membres du groupe un pourcentage de 12.5%.

## Joueurs

Les 2 joueurs jouent sur le même PC sur le même clavier. Les joueurs doivent coopérer pour gagner. Si un joueur meurt la partie est perdue. Si les joueurs parviennent à tuer le 3ème Boss alors la partie est gagnée. Un joueur se bat au corps à corps (CàC) et l'autre joueur à distance.

### Attaque

Les joueurs ne peuvent pas attaquer en se déplaçant.

### Lampe à huile (torche)

Une seule lampe à huile partagée par les 2 joueurs. Le fait de porter la torche éclaire les alentours mais ralenti ses attaques voir la partie optionnelle. La torche peut être volée par l'autre joueur. Le joueur possédant la torche peut décider de poser la torche (permet de se libérer du malus de vitesse) au sol dans ce cas la torche éclaire moins. L'éclairage de la torche est dynamique et le champ de vision est bloqué par les murs.

### Possession (Contrainte de transfert d'automate)

Les joueurs peuvent prendre possession de l'ennemi le plus proche (sauf Boss) pendant une durée limitée. Durant une possession, le corps du joueur disparaît, si l'ennemi possédé meurt alors le joueur meurt (Game over). Si le joueur avec la torche réalise la possession alors la torche s'éteint pendant la possession (Elle est réutilisable après).

## Ennemi (mobs)

Les mobs sont controlés par des automates (Voir partie Menu).

### Ennemi classique (Contrainte : EGG)

Le jeu possède plusieurs ennemis :

- Ballon se dupliquant jusqu'à 2 fois (Contrainte : EGG)

> Le ballon posséde un point de vie (PV), à sa mort il se divise en 2 ballons de couleurs différentes (Initialement en vert, il passe en bleu puis en rouge (s'il meurt en étant rouge, il meurt définivement)). Le ballon inflige des dégats importants et posséde une trajectoire aléatoire. Le ballon rebondit sur les murs.

- Chauve souris

> Les chauves souris sont des ennemis passif, ils n'attaquent pas le jouer. Les chauves souris volent et peuvent donc passer au dessus du vide cependant elles meurent en heurtant un mur.

- Zombie

> Les zombies pourchassent le joueur le plus proche et attaque au corps à corps.

- Singe à fléchettes

> Le singe à fléchettes reste éloigné des joueurs et s'en rapprochent pour tirer.

### Boss (Contrainte doublure (avec les biomes))

Il existe 3 boss différents (1 par étage) :

- Ballon qui se dupplique jusqu'à 5 fois.
- Mystery machine dans un biome de glace (Contrainte doublure (avec les biomes))
- Doge (2 phases)

Les Boss possèdent des salles réservées, les joueurs sont bloqués dans la salle du Boss jusqu'à son élimination. Une fois le Boss éliminé, les joueurs peuvent retourner en arrière (Ouverture de la porte) et la porte de changement d'étage apparaît.

Les salles de Boss possèdent un biome (terrain) différent, constitué de glace faisant glisser les joueurs voir la partie optionnelle.

## Étage (Contrainte de génération aléatoire)

Le donjon est constitué de 3 étages. Chaque étage est généré aléatoirement avec des salles de mobs (ennemis), des salles d'énigmes, une salle contenant la clé pour accéder au Boss et la salle du Boss. Lorsque un joueur entre dans une porte, les 2 joueurs sont téléportés dans la salle si des entités hostiles y sont présentes dans laquelle ils doivent éliminer toutes les entités hostiles afin d'ouvrir les portes. Cependant il est impossible de retourner à l'étage précédent une fois la porte de fin d'étage passée.

### Salle d'énigme

Pour les salles avec les énigmes, des spawners sont présents, qui font apparaître des entités neutres utiles à la résolution de l'énigme (Utilisation de la possession) voir partie optionnelle.

### Génération des salles

Pour la génération des étages nous avons 2 idées d'implémentations principales :

- Solution préférable (pour la qualité du jeu) :
Les salles sont préfaites et on les places aléatoirement avec des liaisons par couloirs. Toutes les salles placées sont alors accessibles mais ne sont pas forcement utiles.

- Autre solution :
Une grille dont chaque case représente l'unité de mesure minimale d'une salle. Une salle est alors un ensemble de cases de la grille.

## Camera (Contrainte Viewport)

Centrée sur les 2 joueurs, La caméra s'agrandit quand les joueurs s'éloignent l'un de l'autre, et inversement (elle se réduit lorsque les joueurs se rapprochent). La caméra possède un seuil de zoom minimal et maximal. Si les joueurs essaient de s'éloigner hors de ce seuil, alors leurs mouvements sont entravés (Ils ne peuvent pas sortir de l'écran et les mouvements vers l'extérieur de l'écran sont annulés). Une distance maximale est imposée entre les 2 joueurs.

## Menu (Contrainte chargement des automates)

Tous les automates sont stockés dans des fichiers dans un même dossier. Au lancement du jeu, le jeu nous demande de choisir les automates à affecter pour chacune des entités.

## ATH

En haut de l'écran on affiche en superposé sur le terrain de jeu :

| HP J1 | Combustion Torche (voir partie optionnelle) | HP J2 |
| :--- | :---: | ---: |

## Optionnel

### Système de classe

En début de partie les joueurs sont des aventuriers. Une fois le premier Boss vaincu les joueurs peuvent choisir une classe. Après le deuxième Boss, les joueurs peuvent choisir une spécialisation de leur classe.

![Systéme de classes](Classes.png)

### Loots d'armes

Des armes sont disponibles dans le donjon avec des statistiques différentes (vitesse, dégât).

### Salle de boss

Au lieu d'avoir un seul biome défini pour toutes les salles de Boss, nous avons 1 biome défini par Boss.

### Torche

L'utilisation de la torche empêche l'utilisation d'armes à 2 mains.

### Épuisement de la torche

L'efficacité de la torche diminue avec le temps jusqu'à s'éteindre. Il est alors possible de la recharger dans une salle spéciale l'étage.

### Compétences

Liées aux classes à définir.

### Mini Map

Implémentation d'une minimap à ajouter à l'ATH.

### Enigmes

Rajouter des bouttons et des plaques de pression.

### Licences

https://policies.warnerbros.com/terms/en-us/html/terms_en-us_1.2.1.html

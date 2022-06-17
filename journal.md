#07/060/2022

  définition du jeu avec toute l'équipe
  création d'un compte rendu servant de prémise au contrat en prenant en compte les restrictions

#08/06/2022

Créations des sous équipes (* signifie chef d'équipe / respo merge)

Model :
  Maxime *
  THeo
  Mathis

Controle :
  Diego *
  Dova
  Michelle

View :
  Killian *
  Clement


choix du nom de l'équipe :

choix du nom du jeu : Dongeon et Balon


#09/06/2022

Model :
  Fait : reflexion sur la génération des salles

  Décision d'objectif :
      salles placé aléatoirement avec zone d'influence qui ne se recouvrent pas
      les couloire sont généré pour lier 2 salles avec 1 ou 2 virages si necessaire
      les portes sont placé en fonction des position des couloire

  Fait 1er test de détection de zone d'influence

  Fait création de la hirachie de class du model

View :
  Fait : Implémenter un système d'affichage des entités
  Fait : Implémenter un système d'affichage de la carte



#10/06/2022

Model :
  Entity :

    Fait : réfléchir sur les Entity, leur comportement et hérarchie

  Map :
    Décodage fichier JSON pour créer les salles

Controle:

  Fait : Réfléxion sur la création d'automates exécutable à partir de l'AST. Début d'une implémentation basique de la gestion d'automates

View :

  Fait :Implémenter un système d'animation pour les entités


# 11 et 12 Juin

Controle :
  Modification du BotBuilder pour qu'il puisse gérer plusieurs automates dans un même fichier, avec autant de transitions que l'on veut. Une transition contient (pour le moment) une condition (sans &, / et not) et au maximum une action sans proba.

General :
  Fait : Allez voté


## 13 Juin

Controle :

  Objectif de la journée :

  Fait : Création d'un arbre de condition pour évaluer les conditions des transitions. => Opérationnel (à merge)

  A faire : Implémentation des listes d'actions par transition et des probas sur les actions.

Model :

  Entity :

    la personne sur l'entité étant le chef de projet, vu les rafales de question, il n'y as eu aucune progression

  MAP :

    Fait : Graph et delaunay algorithme

    Placement cohérent des salles dans un étage (partie 1)

  View :
    Fait : Implémenter un Viewport

    Fait : Gérer plusieurs sources de lumière


# 14/06/2022

Decision :

  Balon à un niveau et des PV
  pas besoins de delete + create un entité graphique au divide


Model :

  Entity :

    Objectif : a faire au plus vite, merge le model map avec le reste

    Fait : création de l'interface de distuction avec le Controle

    Fait : implémentation de l'interface de discution avec la VIEW

    Fait : possiblité de déplacement d'une entité

    objectif réussi : merge le model entity et le Controle

  MAP :

    Fait : Test et correction de la partie de Lundi

    Fait : Placement cohérent des salles dans un étage (partie 2)

Controle :

  Objectif merge la liste d'action avec l'abre de condition dans le builder d'automates exécutable puis tester. => Fait

  Objectif fin de journée merge avec les autres groupes sur master. ==> Merge avec groupe model fait

View :

  Fait : Mettre des textures pour l'affichage de la carte

  Fait : Implémenter un système d'animation pour les entités

  Fait : afficher les entités visibles uniquement du Viewport

#15/06/2022

Model :

  Entity :

    Objectif : merge view et model entity

    Fait : finalisation des déplacement des entité
    Fait : posibilité des entité de ce déplacer selon un angle
    fait : merge Controle master + tests de déplacement controlé (ZQSD) d'une entié et son affichage

  MAP :

    Fait : Dijkstra et MST

    FAIT : Réorganisation du code JSON et et du code pour placer les salles

View :

  Fait : afficher les entités visibles uniquement du Viewport    

TOUT :
  echec, le merge avec la création de la map n'as pas été fait


Controle :
  Objectif implémenter les listes d'actions avec les probas dans le builder et créer une version stable utilisant les keys dans un automate (déplacer un joueur avec zqsd).

  Fait : Implémenter listes d'actions avec les probas dans le builder + Merge avec le model pour faire bouger un personnage avec zqsd.

  Fait : Etat de saut (Permet de sauter dans un état aléatoire de l'automate).

  Fait : Implémentation de toutes les signatures des conditions et actions.

View :

  Fait : merge la VIEW avec le model

  Objectif : Faire apparaître les classe concrète des entités

#16/06/2022

Objectif general :
  Clean le code avec les atributs publique non necessaire
  FAIT !

Model :
  Objectif : upgrade de move
  FAIT : direction absolue définitive pour les entité controler par clavier
  FAIT : Type d'entité créer (1er version)
  FAIT : Génération des salles de manière groupée
  FAIT : génération de couloirs (test en cours)


Controle :
  Objectif : Améliorer l'implémentation des BotKey + Implémenter plus d'action et de condition (discuter avec le model)

  Fait : Ameliorer l'implementation de BotMove + aide sur le model pour le move
  Fait : implémentation de nouveaux automates

  NON FAIT : Implementation de nouvelles actions

VIEW :
  Objectif : Gérer les ombres projetées (Si collisions effectué) (non fait car collision non fait, reporté)

  Fait : création de tout les mobs de la view (ballon, J1, J2, Bat)
  Fait : modification du système d'animation
  Fait : viewport dynamique sur 2 joueurs



#17/06/2022
  Objectif :

    View : faire Singe et Zombie
            test des animations des entité


    Model : finir le move
            Placer les lieux d'apparition d'entités dans les salles
            Finir les test de couloirs

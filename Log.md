---
title: Journal de bord du groupe Controller
author: CDO Diego
---

## 9 Juin

Réflexion sur la gestion des appuis de touches.

## 10 Juin

Réfléxion sur la création d'automates exécutable à partir de l'AST. Début d'une implémentation basique de la gestion d'automates

## 11 et 12 Juin

Modification du BotBuilder pour qu'il puisse gérer plusieurs automates dans un même fichier, avec autant de transitions que l'on veut. Une transition contient (pour le moment) une condition (sans &, / et not) et au maximum une action sans proba.

## 13 Juin

Objectif de la journée :

Création d'un arbre de condition pour évaluer les conditions des transitions. => Opérationnel (à merge)
Implémentation des listes d'actions par transition et des probas sur les actions.

## 14 Juin

Objectif merge la liste d'action avec l'abre de condition dans le builder d'automates exécutable puis tester. => Fait

Objectif fin de journée merge avec les autres groupes sur master. ==> Merge avec groupe model fait

## 15 Juin

Objectif implémenter les listes d'actions avec les probas dans le builder et créer une version stable utilisant les keys dans un automate (déplacer un joueur avec zqsd).

Fait : Implémenter listes d'actions avec les probas dans le builder + Merge avec le model pour faire bouger un personnage avec zqsd.

Fait : Etat de saut (Permet de sauter dans un état aléatoire de l'automate).

Fait : Implémentation de toutes les signatures des conditions et actions.

## 16 Juin

Objectif : Améliorer l'implémentation des BotKey + Implémenter plus d'actions et de conditions (discuter avec le model)

Fait : Améliorer implémentation des BotKeys ==> Le personnage bouge mais la fonction move ne permettra jamais de déplacer des entités avec cette implémentation

Implémenter plus d'actions et de condition a été commencé en discutant avec le model (Comment faire la fonction Move ?)

## 17 Juin

Objectif : Faire la fonction Move avec le Model

Fait : Faire la fonction Move + écriture des PlayerAbsoluteDir et PlayerRelativeDir

Problème la réimplémentation des Directions a empêché d'écrire d'autres action (Les directions ont du être revisité de A à Z).

Commencé : EntityAbsoluteDir et EntityRelativeDir (bugé)

Création de l'objet torche qui est fonctionnel

## 18 Juin

Correction des EntityAbsoluteDir EntityRelativeDir + Amélioration implémentation BotMove et BotTurn

## 20 Juin

Début de l'implémentation de la possession

## 21 Juin

A faire finir la possession (Corriger le centrage de la cam + intangibilité)

Finalisation de la possession

## 22 Juin

Ajout d'un champ `possessable` dans les propriétés des entitiés.

Implémentation de MyDir

Implémentation de Wait

Débuggage de la caméra

Début implémentation de la suppression des d'entités

Refonte des directions dans les automates (Class BotDirection) + adaptation du BotBuilder

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

Objectif : Améliorer l'implémentation des BotKey + Implémenter plus d'action et de condition (discuter avec le model)

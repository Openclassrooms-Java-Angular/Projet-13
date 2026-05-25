# PoC chat support - Your Car Your Way

Ce projet est une preuve de concept (PoC) de la fonctionnalité de chat avec le service du support client « Your Car Your Way ».  
Il permet de démontrer :
- le chargement de l'historique des messages
- l'envoi de messages en temps réel
- la réception de messages par un autre client connecté au serveur
- la persistance des messages en base de données

Ce PoC se limite volontairement à la fonctionnalité de chat texte, conformément au périmètre demandé.

## Stack technique

- Backend : Java, Spring Boot, Spring WebSocket, Spring Data JPA
- Frontend : Angular
- Base de données : MySQL

## Prérequis

- Java 17 ou version compatible
- Node.js et npm
- MySQL

## Installation

### 1. Base de données

Créer une base de données MySQL, par exemple :

```sql
CREATE DATABASE yourcaryourway;
```

Configurer ensuite les variables d'environnement du fichier `application.properties` du backend :

**Sous Linux / MacOS** :
```
export DB_USERNAME=votre_login
export DB_PASSWORD=votre_mot_de_passe
export DB_NAME=yourcaryourway
```

**Sous Windows (avec PowerShell)** :
```
$env:DB_USERNAME="votre_login"
$env:DB_PASSWORD="votre_mot_de_passe"
$env:DB_NAME="yourcaryourway"
```

### 2. Backend

Se placer dans le dossier backend puis lancer :

```bash
mvn clean install
mvn spring-boot:run
```

Le backend démarre sur :

```text
http://localhost:8080
```

### 3. Frontend

Se placer dans le dossier frontend puis lancer :

```bash
npm install
ng serve
```

Le frontend démarre sur :

```text
http://localhost:4200
```

## Données de démonstration

Le projet utilise un fichier `data.sql` qui crée :
- 2 utilisateurs de test
- 1 conversation de test
- des messages d'exemple

## Fonctionnement

Au chargement de la page :
- l'historique des messages de la conversation de test est récupéré via REST
- le client Angular ouvre ensuite une connexion WebSocket
- les nouveaux messages sont envoyés en temps réel et sauvegardés dans la base de données

## Scénario de démonstration

1. Ouvrir l'application dans deux fenêtres de navigateur
2. Choisir un utilisateur différent dans chaque fenêtre
3. Envoyer un message dans une fenêtre
4. Vérifier qu'il apparaît immédiatement dans l'autre fenêtre
5. Recharger la page pour vérifier que l'historique est conservé

## API et documentation Swagger

La documentation interactive de l'API est disponible ici : [Documentation Swagger](http://localhost:8080/swagger-ui/index.html)

## Limites du PoC

- Une seule conversation de démonstration est utilisée
- Le choix de conversation n'est pas implémenté dans l'interface
- La fonctionnalité de visio n'est pas incluse dans ce PoC
- L'authentification n'est pas implémentée
# ProjetTuteure
Repo du projet Android.  
Le README contiendra l'ensemble des informations dont nous aurons besoin pour nous familiariser avec Git ou Android.  
Si vous avez des liens à partager n'hésiter pas à la poster içi.

Toutes les infos sont pour Eclipse.

## Notes
1. Comparer son code avec le repo.  
Sur votre projet, faites __clic droit__ > __Compare With__ > __Commit...__.  
   * Eclipse affichera une arborescence avec les fichiers ayant un contenu différent de ce qui ce trouve sur le repo.  
   * Double cliquer sur un fichier pour afficher la différence entre votre code local et le dépot.


2. Parcourir les commit au sein d'Eclipse.  
Sur votre projet, faites __clic droit__ > __Show In__ > __History__.  
   * Cette manip vous affichera tout les commit.
   * Ne pas oublier les options suivantes :  ![alt text](https://i.stack.imgur.com/jHlmb.png "all commit")  


3. Récupérer le code d'un commit.  
Sur votre projet, faites __clic droit__ > __Replace With__ > __Commit...__.  
Autre méthode : __clic droit__ > __team__ > __synchronize workspace__. Dans la fenêtre, cliquer sur __pull__.  
   * Attention, cela supprime tout les changement non sauvegardés.  


4. Récupérer une branche.  
Sur le commit, dans la vue *history* : __Clic droit__ > __Checkout__.  


5. Changer une branche.  
Sur le commit, dans la vue *history* : __Clic droit__ > __Rebase on__.  


## Google Map
1. Modifier le fichier *eclipse.ini* :  
--launcher.XXMaxPermSize 512M (à faire deux fois dans le fichier)  
-Xms512m  
-Xmx1024m  
[lien](https://stackoverflow.com/questions/22282980/unable-to-execute-dex-gc-overhead-limit-exceeded#tab-top)  

2. Configurer Eclipse avec l'API Google Play Service :  
[par ici] (https://stackoverflow.com/questions/16395495/add-google-maps-to-android-app?answertab=active#tab-top)  


## Liens
* [~~créer un nouveau projet GitHub sous Eclipse~~](http://www.throrinstudio.com/dev/creer-un-nouveau-projet-github-sous-eclipse/)  
* [Article ultra complet sur GIT dans Eclipse](http://www.vogella.com/tutorials/EclipseGit/article.html)
* [tuto/explication sur la classe AsyncTask](http://olegoaer.developpez.com/tutos/mobile/android/rpc/#LII-B)
* [Savoir rédigez en markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)

Pour démarrer le serveur il suffit de taper la commande suivante en étant dans le dossier INF3995-Server:
	./server [adresse IP] [port]

Veuillez noter que les paramètres « adresse IP» et « port » sont optionnels. Vous pouvez vous arrêter à la commande simple: «./server ».

Pour les packages et librairies à installer, il sera nécessaire d'installer les suivants: libmad, restbed, asound, taglib, rapidjson, ssl et crypto.

Voici les commandes nécessaires pour les installations (vous pouvez vérifier les sites Web respectifs):

- libmad: (https://www.underbit.com/products/mad/)
% sudo apt-get install libmad0-dev

- asound: (http://alsa-project.org/main/index.php/Asoundrc)
% sudo apt-get install libasound2-dev

- restbed: (https://github.com/Corvusoft/restbed)
% sudo apt-get install librestbed-dev

- taglib: (https://taglib.org/)
% sudo apt-get install libtag1-dev

- rapidjson: (http://rapidjson.org/)
% sudo apt-get install rapidjson-dev

- crypto
% sudo apt-get install libssl-dev

- ssl
% sudo apt-get install libssl-dev
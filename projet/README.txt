Pour d�marrer le serveur il suffit de taper la commande suivante en �tant dans le dossier INF3995-Server:
	./server [adresse IP] [port]

Veuillez noter que les param�tres � adresse IP� et � port � sont optionnels. Vous pouvez vous arr�ter � la commande simple: �./server �.

Pour les packages et librairies � installer, il sera n�cessaire d'installer les suivants: libmad, restbed, asound, taglib, rapidjson, ssl et crypto.

Voici les commandes n�cessaires pour les installations (vous pouvez v�rifier les sites Web respectifs):

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
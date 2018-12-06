TP3 de INF3995

Le TP a pour but de faire tourner un serveur écrit en C++ sur un arch linux, tournant sur une FPGA.

Pour se faire, nous avons utilisé la librairie libnavajo.
Plusieurs raisons nous on poussé à ce choix. Tout d'abord, la librairie permet d'utiliser à la fois les webSocket et une api REST.
Enfin, nous avons essayé auparavant avec la librairie HTTPP et avec la librairie cpprestsdk. Néanmoins, aucune de ces deux librairie ne compile rapidement sur la FPGA, par manque de RAM.
Nous avons donc opté pour libnavajo, qui, en plus de compiler, est adapté à notre situation.
On pourrait néanmoins envisager de changer pour l'une des deux autres librairies plus tard sans le projet, si l'on trouve un moyen de les faire compiler. Nous trouvons qu'elles sont plus intéressantes, et nous avons déjà écrit le code source du server pour ces deux librairies.

Afin d'installer libnavajo, nous vous conseillons d'effectuer les commandes suivantes : 

cd libUsed/libnavajo
mkdir build
cd build
cmake ..
make
sudo make install

Une fois cela fait, vous pouvez compiler le projet. Pour se faire :

cd ../../src 
make

A présent, vous pouvez lancer le serveur avec la commande: 

./server
# Yar

***

_Looking to download Yar? Try [here](https://github.com/downloads/moerie/yar/Yar.jar)!_

***

Yar is a one-man side project written in Java, that aims to simplify bulk renaming.

Current feature list:

### Load files:
* Drag and drop

### Rename actions
* Insert text at any given index from the beginning/end of the file name
* Remove any number of characters starting at any index from the beginning/end of the file name
* Replace any text with something else
* Move a part of the file name to anywhere else in the file name

All these actions are **chainable** and provide a **live visual preview** before you actually need to rename anything!

### Exception handling
* If something unexpectedly goes wrong, you are provided with the full stacktrace and a more readable version of the exceptions that occured. 
_(Possible causes include files that have disappeared or have been renamed since you dropped them in Yar, trying to insert illegal characters into file names, etc.)_

### Screenshot

![Yar screenshot](http://i.imgur.com/qgpqa.png)
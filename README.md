# wordtree
Class that allows storage of words in a k-ary tree ADT

Words that begin with the same letters lie on the same branch of the tree ADT. Eg: "cat" and "catch" will share the first three nodes "c", "a", and "t". The remaining nodes "c" and "h" will then be attached to the node containing "t" but demarcated as a new and separate word. The usefulness of this style of k-ary tree is that it is extremely space-efficient. Words with common starting letters share root, and more, nodes, allowing for little to no replication of common letters between words.

The WordTree.java class contains only one Node object as the 'root' variable. A variety of in-built Java ADTs such as Sets, Queues, and HashMaps are combined to provide the functionality in the WordTree class. Methods inside the class include:
boolean add(String word)
boolean contains(String word)
boolean remove(String word)
int nodeCount()
int wordCount()
int lettersSaved()
void clear()
Set<String> allWords()
Set<String> allStartingWith(String prefix)
Map<Character, Set<String>> wordMap()

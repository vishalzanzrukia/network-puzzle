# Network Puzzle
This is network puzzle where we can have N number of elements (let's say 8 for example). Each element can connect to other element and vice versa is also possible but order does not matter, i.e. if A is connected to B then B is also connected to A automatically. So we can make a set of connections.

We can make any number of connections and any two elements can be connected. Let’s make the following connections : 1-2, 1-6, 2-4, 2-6, 5-8

![Network](network.png?raw=true "Network")

Now we need to be able to determine if two elements are connected, either directly or through a series of connections. 1 and 6 are connected, as are 6 and 4. But 7 and 4 are not connected,
neither are 5 and 6. We do not care about the path, 1 and 2 are connected both directly and also through 6, but for this problem the fact that there are two paths is irrelevant.

This repository contains a solution for above problem which helps to create network where we can connect nodes and query whether two nodes are connected or not. Hope this helps :)


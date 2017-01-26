Language - Java 8

Code Structure
===============
* Distance.java - An interface class which forces the class implementing it to implement the distance function. Every
                  AbstractNode of type T needs to implement this interface.

* Constants.java - A class to store all the Constant values across the project.

* AbstractNode.java - This is a class which contains the basic structure and common methods associated with a
                      generic Node in the topology. It contains a node of Type T and has the common implementation for the RankingFunction,
                      merging peer lists and calculating sumOfDistances of the node with respect to its peers. The node attribute in the class
                      defines the type of the AbstractNode.

* AbstractTopology.java - This is an abstract class for defining the topologies. It contains the attributes and methods
                          of a concrete topology class and also common methods which print the neighbors of the node into a file, plot the
                          sum of distances chart and print the topology plots as an image. The AbstractTopology class is made up of a list of
                          AbstractNode<T>.

* DynamicRingNode.java - This is a concrete implementation of a node in the DynamicRingTopology. This class implements
                         the Distance interface.

* DynamicRingTopology.java - The concrete class of the DynamicRingTopology extending the AbstractTopology class.
                             This class contains the implementation of the initialize and the execute methods pertinent
                             to the Dynamic Ring Topology.

* BinaryTreeNode.java - This is a concrete implementation of a node in the BinaryTreeTopology. This class implements the
                        Distance interface.

* BinaryTreeTopology.java -  The concrete class of the BinaryTreeTopology extending the AbstractTopology class.
                             This class contains the implementation of the initialize and the execute methods pertinent
                             to the Binary Tree Topology.

* CrescentMoonNode.java - This is a concrete implementation of a node in the CrescentMoonTopology. This class implements the
                          Distance interface.

* CrescentMoonTopology.java -  The concrete class of the CrescentMoonTopology extending the AbstractTopology class.
                               This class contains the implementation of the initialize and the execute methods pertinent
                               to the Crescent Moon Topology.


How to Compile
===============
Move into the main folder and execute the make command. This will trigger the compilation of the entire project via the
makefile.

How to Run
===========
After compiling the code you can execute the following commands for each topology.

Dynamic Ring Topology - java TMAN <N> <K> <n> <r1,r2,....,rn>

Binary Tree Topology - java TMAN <N> <K>

Crescent Moon Topology - java TMAN <N> <K>

N - Number of Nodes in the Topology.
K - The neighbors associated with each node in the topology.
n - No. of radius values.

Important Notes
================
* The topology plots have been generated using the Java AWT library. The graphs have been upscaled by a constant factor
  to ensure that it is not cumbersome is obtained when run. Some of the png files generated are of size 6k x 6k with a size
  of approx. 3 MB and hence they may take time to generate. It is taking approximately 30 secs to generate one BinaryTreeTopology plot.

* As mentioned above the size of each png files is large. So for performance sake as I have defaulted the size of each image
  to 6k x 6K. However, the images attached in the pdf are of a higher granularity and have been painted on a canvas of size 12k x 12k.
  So you might notice some difference between the one's generated and the one's in the pdf report attached.

* The sum of distance vs iteration charts have been drawn using a third party library called as JFreeChart. The library
  has been attached as a part of the tarred source code and there should be no problem in running the code because of it.

* Each topology when run outputs the following files :
   - The topology plot after the initialize phase (png file).
   - The topology plot after every 5th iteration (png file).
   - A Sum_Of_Distances vs Iterations chart (png file).
   - A text file containing the Node and its corresponding neighbors after every 5th iteration.
   - A text file containing the Sum of Distances after every iteration.
  The file namse used are of the following syntax : <topologyId>_N<N>_K<K>_<currCycleNum>.<extension>
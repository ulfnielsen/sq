Requirements
===

- Java8 SDK installed (javac/java)

Running
===

Main entry point is FindMinMaxDistances. To compile do (location is this directory):
```
javac -d bin -sourcepath src src/sqdistance/FindMinMaxDistances.java
```
then:
```
java -cp bin sqdistance.FindMinMaxDistances
```
Output should be something like

```
Closest points to (-200, 300)
(-198, 292) - norm=8.246211251235321
(-194, 307) - norm=9.219544457292887
(-191, 285) - norm=17.4928556845359
(-191, 319) - norm=21.02379604162864
(-220, 289) - norm=22.825424421026653
(-224, 317) - norm=29.410882339705484
(-170, 292) - norm=31.04834939252005
(-232, 311) - norm=33.83784863137726
(-190, 334) - norm=35.4400902933387
(-194, 265) - norm=35.510561809129406
Furthest points from (1000, 25)
(-32761, -32735) - norm=47042.775438955556
(-32764, -32731) - norm=47042.14314845785
(-32724, -32759) - norm=47032.954744519295
(-32764, -32706) - norm=47024.7387765206
(-32761, 32751) - norm=47019.10459589804
(-32766, 32739) - norm=47014.34410900571
(-32743, -32710) - norm=47012.44807495138
(-32715, -32727) - norm=47004.19905710553
(-32745, 32745) - norm=47003.4405655586
(-32746, -32691) - norm=47001.37415012459
(-32671, -32767) - norm=47000.5479223381
(-32722, -32712) - norm=46998.77076052096
(-32711, -32723) - norm=46998.54279655913
(-32764, -32659) - norm=46992.03711268538
(-32751, -32672) - norm=46991.7419340888
(-32766, 32704) - norm=46989.996775909654
(-32719, -32697) - norm=46986.17078460427
(-32732, 32731) - norm=46984.361866476385
(-32729, 32734) - norm=46984.29654682509
(-32681, -32731) - norm=46982.60632404294
Done in 819 ms
```


Implementation Notes
===

The code uses NIO to mem-map the points file and then a ShortBuffer to read them lazily and pass them on to a stream. This happens in the class Points which provide the Stream<Points> get(FileChannel) method to acquire a stream that can be used to iterate over all points in the file. The benefit of providing a stream is that we have all the new features in the Java stream API available.

Since the number of closest/furthest points are relatively small we choose to implement a very simple PointDistanceQueue which in contrast to the PriorityQueue of the Java Library has a max number of elements. This enables to discard most points early and run time improves.

The directory structure is that of an Eclipse project and it can be imported as such. The Junit test cases can be run from there as well.

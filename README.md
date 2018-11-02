# Metrics

[![](https://jitpack.io/v/ondrej-nemec/metrics.svg)](https://jitpack.io/#ondrej-nemec/metrics)
[![MIT License](http://img.shields.io/badge/license-MIT-green.svg) ](https://github.com/ondrej-nemec/metrics/blob/master/LICENSE)

* [Description](#description)
* [Get library](#how-to-install)
* [Usage](#usage)
	* [Distance](#distance)
	* [Length](#length)
	* [Entropy](#entropy)

## Description
Library for edit distance calculating. Allowed generic sequences. Provide classes for easiest work with big dataset.
Supported edit distances: *Levenshtein distance*, *Hamming distance*, *Jaro distance*, *Jaro-Winkler distance*.
Supported length: *Longest common substring length*, *Longest common subsequence length*.
Bonus: *Conditional Entropy*.
## How to install
### Download:
<a href="https://ondrej-nemec.github.io/download/metrics-4.0.jar" target=_blank>Download jar</a>
### Maven:

After `build` tag:
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
And to `dependencies`:
```xml
<dependency>
  <groupId>com.github.ondrej-nemec</groupId>
  <artifactId>metrics</artifactId>
  <version>4.0</version>
</dependency>
```

## Usage
**Note:** in this text, generic type 'S' is always type of what you compare - if you wont compare Strings (words), you will use List<Character>
### Distance
Each distance could be calculated with two ways - quick and info. Quick mode provide only distance, info mode give you more information about calculation.
#### Distance Quick mode 
Each distance in quick mode implement `DistanceQuick` interface and has method:
```java
Number calculate(List<S> sequenceFrom, List<S> sequenceTo);
```
#### Distance More-info mode
`DistanceInfo` - provide distance, description and 'final sequences', which are given sequences edited to same length.
In this mode metrics have this method: 
```java
DistanceResult<S, T> calculate(List<S> sequenceFrom, List<S> sequenceTo);
```
'T' is type of structure which you could get from result set. [More about DistanceResult](#distance-result). This type is defined in each metric, so you must define only 'S'.
##### Distance result
This is data object. From this class you can get informations about previous distance calculation in 'more-info' mode.
```java
//return edited first sequence
List<S> getFinalSequenceFrom();

//return edited second sequence
public List<S> getFinalSequenceTo();
	
//return description of calculation
String getDescription();

//return String represent each used operations
String getOperations();

//return calculated distance
Number getDistance();
	
//return used structure
T getStructure();
```

### Length
Each length has two ways - info and quick - too. 
#### Length Quick mode
Classes in length package implement `LengthQuick` interface. This interface provide:
```java
Number calculate(List<S> sequenceFrom, List<S> sequenceTo);
```
#### Length More-info mode
`LengthInfo` - provide length, description and more informations.
In this mode metrics have this method: 
```java
LengthResult<S, T> calculate(List<S> sequenceFrom, List<S> sequenceTo);
```
'T' is type of structure which you could get from result set. [More about LengthResult](#distance-result). This type is defined in each metric, so you must define only 'S'.
##### Length result
This is data object. From this class you can get informations about previous distance calculation in 'more-info' mode.
```java
//return description of calculation
String getDescription();

//return calculated length
Number getLength();
	
//return used structure
T getStructure();

//return all common sub(string/sequence/...) which have calculated length
public Collection<List<S>> getSubs();
```

### Entropy
For calculating use `Entropy` class. Remember size of both array and sizes of `first.get(i)` and `second.get(i)` must be same or new `RuntimeException` is throwed. This class is immutable, so when you create new entropy, everything is calculated.
```java
//create new entropy and calculate
new Entropy(List<List<S>> first, List<List<S>> second);

//return entropy for first sequence
public Double getEntropyFrom();

//return entropy for second sequence
public Double getEntropyTo();

//return every 'S' from first sequence with counts of occurrence
public List<Tuple2<S, Integer>> getFonemsFromWithCount();

//return every 'S' from second sequence with counts of occurrence
public List<Tuple2<S, Integer>> getFonemsToWithCount();


//return every 'S twins' from first and second sequence with counts of occurrence
public List<Tuple3<S, S, Integer>> getFonemsTwinsWithCounts();
```


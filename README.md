# Metrics

[![](https://jitpack.io/v/ondrej-nemec/metrics.svg)](https://jitpack.io/#ondrej-nemec/metrics)

* [Description](#description)
* [Get library](#how-to-install)
* [Usage](#usage)
	* [Quick mode](#quick-mode)
	* [More-info mode](#more-info-mode)
		* [Result set](#result-set)
	* [Entropy](#entropy)

## Description
Library for edit distance calculating. Allowed generic sequences. Provide classes for easiest work with big dataset. Supported metrics: *Levenshtein distance*, *Hamming distance*, *Jaro distance*, *Jaro-Winkler distance*. Each metric could be calculate in 'quick' or 'more-info' mode. As a bonus this library calculate *Conditional Entropy*.
## How to install
### Download:
<a href="https://ondrej-nemec.github.io/download/metrics-3.0.1.jar" target=_blank>Download jar</a>
### Maven:
```xml
<dependency>
  <groupId>io.github.ondrej-nemec.metrics</groupId>
  <artifactId>metrics</artifactId>
  <version>3.0.1</version>
</dependency>
```

## Usage
**Note:** in this text, generic type 'S' is always type of what you compare - if you wont compare Strings (words), you will use List<Character> 
### Quick mode
Each metric in quick mode has method:
```java
Number calculate(List<S> sequenceFrom, List<S> sequenceTo);
```
### More-info mode
Provide distance, description and 'final sequences', which are given sequences edited to same length.
In this mode metrics have this method: 
```java
ResultSet<S, T> calculate(List<S> sequenceFrom, List<S> sequenceTo);
```
'T' is type of structure which you could get from result set. [More about ResultSet](#result-set). This type is defined in each metric, so you must define only 'S'.
#### Result set
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


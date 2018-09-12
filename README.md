# Metrics
**Newest version:** 3.0

* [Description](#description)
* [Get library](#how-to-install)
* [Usage](#usage)
	* [Quick mode](#quick-mode)
	* [More-info mode](#more-info-mode)
	* [Entropy](#entropy)
	* [Collections](#collections)

## Description
Library for edit distance calculating. Allowed generic sequences. Provide classes for easiest work with big dataset. Supported metrics: *Levenshtein distance* , *Hamming distance* , *Jaro distance* , *Jaro-Winkler distance* . Each metric could be calculate in 'quick' or 'more-info' mode. As a bonus this library calculate *Conditional Entropy*
## How to install
**Download:**
<a href="https://ondrej-nemec.github.io/jars/metrics-3.0.jar" target=_blank>Download jar</a>
**Maven:**
```xml
<dependency>
  <groupId>io.github.ondrej-nemec.metrics</groupId>
  <artifactId>metrics</artifactId>
  <version>3.0</version>
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
In this mode metrics have this method
```java
ResultSet<S, T> calculate(List<S> sequenceFrom, List<S> sequenceTo);
```
'T' is type of structure which you could get from result set. [More about ResultSet](#result-set)
#### Result set
### Entropy
### Collections
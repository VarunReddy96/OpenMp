
### Go into this folder
```
cd openmp_examples
```

### Compile and run examples
```
omp4j -d class -v src/main/java/edu/rit/cs/MyTimer.java src/main/java/edu/rit/cs/OMP_{ExampleClassName}.java
java -cp class edu.rit.cs.OMP_{ExampleClassName}
```

### As an example, if you want to test the OMP_ArrayOperation:
```
omp4j -d class -v src/main/java/edu/rit/cs/MyTimer.java src/main/java/edu/rit/cs/OMP_ArrayOperation.java
java -cp class edu.rit.cs.OMP_ArrayOperation
```

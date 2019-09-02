
If you experienced this problem, the running environment cannot locate the native library.
```
Exception in thread "main" java.lang.UnsatisfiedLinkError: no mpi_java in java.library.path
	at java.lang.ClassLoader.loadLibrary(ClassLoader.java:1867)
	at java.lang.Runtime.loadLibrary0(Runtime.java:870)
	at java.lang.System.loadLibrary(System.java:1122)
	at mpi.MPI.<clinit>(MPI.java:206)
```

Add the following as VM Option
```
-Djava.library.path=/usr/local/lib
```
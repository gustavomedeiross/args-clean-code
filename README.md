# Args.java - My version of Uncle Bob's Args module 

`Args.java` is a simple command-line argument parser, where you can query the typed values of the arguments that were used based on a schema that you can pass to the `Args` class.

The refactoring of this class is one of the exercises from the famous book "Clean Code", written by Robert C. Martin (a.k.a. Uncle Bob) in 2009. This is my version of the refactoring, so feel free to leave any suggestions or criticize the code!

Uncle Bob's version: https://github.com/unclebob/javaargs

### Usage:

```java
public static void main(String[] args) {
    try {
        Args args = new Args("b,s*,i#,d##", args);
        Boolean b = args.getBoolean('b');
        String s = args.getString('s');
        Integer i = args.getInteger('i');
        Double d = args.getDouble('d');
    } catch(ArgsException e) {
        System.out.printf("Argument error: %s\n", e.errorMessage());
    }
}
```


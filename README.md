# lox-lang

Built a dynamically typed, high level language similar to JavaScript called Lox using RD Parser & AST Interpreter from the book 
[Crafting Interpreters](http://www.craftinginterpreters.com/).

***Example : [Fibonacci Series in Lox]()***

## Syntax
```js
// Print to Console
print "Hello World!";

// Variable Declaration
var a = 2;

// Logical Operators
var c = !true;
print c;

// Conditional Statements
if (100 > 10) {
    print "100 is greater than 10";
} else {
    print "100 is not greater than 10";
}

// While Loops
var d = 1;
while (d <= 10) {
    print d;
    d = d + 1;
}

// Functions
fun sayHi(name) {
    print "Hi " + name;
}
print sayHi("Varun");

// Fibonacci Series
fun fib(n) {
    if (n <= 1) return n;
    return fib(n - 1) + fib(n - 2);
}

var i = 1;
while (i <= 10) {
    print fib(i);
    i = i + 1;
}


```

## Get Started
1. Ensure that you have JDK >= 1.8 installed.
2. Run the following commands:
```shell
$ https://github.com/rvarun11/lox.git
$ cd lox
~/lox$ javac -d out src/vr/jlox/lox/*.java
```
3.
a) To run Lox CLI: 
```shell
~/lox$ java -cp ./out vr.jlox.lox.Lox
```
 b) To run a Lox File: 
```shell
~/lox$ java -cp ./out vr.jlox.lox.Lox <file-name.>
```
You can use existing play.lox file to get started!

## Grammar
```
program        → declaration* EOF ;
declaration    → funDecl | varDecl | statement ;

funDecl        → "fun" function ;
function       → IDENTIFIER "(" parameters? ")" block ;
parameters     → IDENTIFIER ( "," IDENTIFIER )* ;

varDecl        → "var" IDENTIFIER ( "=" expression )? ";" ;

statement      → exprStmt | ifStmt | printStmt | returnStmt | whileStmt | block;
exprStmt       → expression ";" ;
ifStmt         → "if" "(" expression ")" statement ( "else" statement )? ;
printStmt      → "print" expression ";" ;
returnStmt     → "return" expression? ";" ;
whileStmt      → "while" "(" expression ")" statement ;
block          → "{" declaration "}" ;

-- Grammar Rules with Precedence --
expression     → assignment ;
assignment     → IDENTIFIER "=" assignment | logic_or;
logic_or       → logic_and ( "or" logic_and )* ;
logic_and      → equality ( "and" equality )* ;
equality       → comparison ( ( "!=" | "==" ) comparison )* ;
comparison     → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
term           → factor ( ( "-" | "+" ) factor )* ;
factor         → unary ( ( "/" | "*" ) unary )* ;
unary          → ( "!" | "-" ) unary | call ;
call           → primary ( "(" arguments? ")" )* ;
arguments      → expression ( "," expression )* ;
primary        → NUMBER | STRING | "true" | "false" | "nil" | "(" expression ")"  | IDENTIFIER ;

-- Base Grammar for AST --
expression     → literal | unary | binary | grouping ;
literal        → NUMBER | STRING | "true" | "false" | "nil" ;
grouping       → "(" expression ")" ;
unary          → ( "-" | "!" ) expression ;
binary         → expression operator expression ;
operator       → "==" | "!=" | "<" | "<=" | ">" | ">=" | "+"  | "-"  | "*" | "/" ;
```


***Note***: I haven't worked on For Loops, Semantic Analysis & OOP as of yet.
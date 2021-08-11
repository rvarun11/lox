package vr.jlox.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {

    private static final Interpreter interpreter = new Interpreter();
    static boolean hadError = false;
    static boolean hadRuntimeError = false;

    public static void main(String[] args) throws IOException {
//        repl.it:
//        runFile("ADD PATH");

//        Local Testing:
        runFile("C:\\Users\\KSR\\IdeaProjects\\lox\\play.lox");

//        CLI : Comment the lines above & uncomment to play with CLI
//        if (args.length > 1) {
//            System.out.println("Usage: jLox [script]");
//            System.exit(64);
//        } else if (args.length == 1) {
//            runFile(args[0]);
//        } else {
//            runPrompt();
//        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        // Indicate an error in the exit code.
        if (hadError) System.exit(65);
        if (hadRuntimeError) System.exit(70);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
        }
    }

    private static void run(String source) {
        // 1. Scan the entire source and make tokens
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // For now, just print the tokens.
//        for (Token token : tokens) {
//            System.out.println(token);
//        }
        // 2. Parse the tokens to make them into statements.
        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();

        if (hadError) return;
        // 3. Start our interpreter
        interpreter.interpret(statements);

    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }

    private static void report(int line, String where,
                               String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() +
                "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }

}

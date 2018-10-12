import java.util.*;
import java.io.*;

public class BB {

    public HashMap<String, Integer> variablesList = new HashMap<String,Integer>();
    public File inputFile;
    public Scanner fileInput;

    public void openFile(String fileName) throws FileNotFoundException {
        inputFile = new File(fileName);
        fileInput = new Scanner(inputFile);
    }

    public String getSourceLine() {
        return fileInput.nextLine();
    }

    public void closeFile() {
        fileInput.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        BB myBB = new BB();
        Lexer myLexer = new Lexer(myBB);
        myBB.openFile("example.txt");
        ExecutableBlock root = myLexer.parse();
        root.execute();
        //Expr block = myLexer.analiseLine("clear A;");
        //block.execute();
        myBB.closeFile();
        /*
        Expr root = new ExecutableBlock(myBB);
        root.addNewBlock(new VariableOperation(0, "A", myBB));
        root.addNewBlock(new VariableOperation(1, "A", myBB));
        root.addNewBlock(new VariableOperation(1, "A", myBB));
        root.addNewBlock(new VariableOperation(0, "B", myBB));

        ExecutableBlock loopList = new ExecutableBlock(myBB);
        loopList.addNewBlock(new VariableOperation(2, "A", myBB));
        loopList.addNewBlock(new VariableOperation(1, "B", myBB));
        root.addNewBlock(new LoopExpression("A", 0, loopList, myBB));

        root.execute();
        */
    }

    public void panic(String errorMessage) {
        System.out.println("PANIC: " + errorMessage);
        closeFile();
        System.exit(0);
    }
}

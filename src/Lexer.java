import java.util.*;
import java.io.*;

public class Lexer {
    BB localBB;
    public Lexer (BB initialBB)
    {
        localBB = initialBB;
    }

    public ExecutableBlock parse() {
        ExecutableBlock parsedBlock = new ExecutableBlock(localBB);
        Expr currentBlock;
        currentBlock = analiseLine(localBB.getSourceLine());

        while((currentBlock !=null))
        {
            parsedBlock.addNewBlock(currentBlock);
            currentBlock = analiseLine(localBB.getSourceLine());
        }

        return parsedBlock;
    }

    public Expr analiseLine(String line) {
        Expr analisedExpression;

        if(line.isEmpty())
        {
            return null;
        }

        if(line.charAt(line.length()-1)!=';') {
            localBB.panic("Missing semicolon");
        }

        line = line.substring(0, line.length()-1);
        String[] arguments = line.split(" ", 5);


        switch(arguments[0]) {
            case "clear":
                return new VariableOperation(0,arguments[1],localBB);
            case "incr":
                return new VariableOperation(1,arguments[1],localBB);
            case "decr":
                return new VariableOperation(2,arguments[1],localBB);
            case "while":
                ExecutableBlock loopExecutionFlow = parse();
                Integer valueNotEqual = Integer.parseInt(arguments[3]);
                return new LoopExpression(arguments[1], valueNotEqual, loopExecutionFlow, localBB);

            }
            /*
            default:
                localBB.panic("Operation " + arguments[0] + " not recognised");
                return null;

        }
        */
        return null;


    }


}

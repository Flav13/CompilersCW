package compilers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 132187
 */
public class Task2 {
    
     public static Parser create() {
        ParserImpl p = new ParserImpl(); 
        return p;       
    }
    
}

 interface Parser {
    
    public Block parse ( List < Token > input ) throws SyntaxException; 
                                               
    
}

 class ParserImpl implements Parser {

    private int rightBrackets = 0;
    private int leftBrackets = 0;

    @Override
    public Block parse(List<Token> input) throws SyntaxException {
        List<Exp> l = new ArrayList<>();
        Block bl = new Block(l);

        BlockTokens bt = parseBlock(bl, input);
        return bt.returnBlock();
    }

    public BlockTokens parseBlock(Block blo, List<Token> input) throws SyntaxException {
        
        if(input.isEmpty()){
            throw new SyntaxException("Input is Empty!");
        }

        if (input.get(0) instanceof T_LeftCurlyBracket) {
            
            List<Token> input2 = new ArrayList<Token>();

            for (int i = 1; i < input.size(); i++) {
                input2.add(input.get(i));
            }
            leftBrackets++;
            BlockTokens b = parseENE(blo, input2);

            if (b.returnTokens().size() > 0 && b.returnTokens().get(0) instanceof T_RightCurlyBracket) {
                rightBrackets++;

                if (rightBrackets == leftBrackets && b.returnTokens().size() == 1) {
                    return b;
                } else {
                    if (leftBrackets > rightBrackets) {
                        if (b.returnTokens().size() != 1) {
                            List<Token> input22 = new ArrayList<Token>();
                            for (int i = 1; i < b.returnTokens().size(); i++) {
                                input22.add(b.returnTokens().get(i));
                            }

                            BlockTokens blt = new BlockTokens(b.returnBlock(), input22);
                            return blt;
                        } else {
                            throw new SyntaxException("Syntax Error!");
                        }
                    } else {
                        throw new SyntaxException("Syntax Error!");
                    }

                }
            } else {
                throw new SyntaxException("Syntax Error!");
            }

        } else {
            throw new SyntaxException("Syntax Error!");
        }


    }

    private BlockTokens parseENE(Block bl, List<Token> input) throws SyntaxException {

        BlockTokens b = parseE(bl, input);

        if (b.returnTokens().size() > 0 && b.returnTokens().get(0) instanceof T_Semicolon) {

            List<Token> input2 = new ArrayList<Token>();
            for (int i = 1; i < b.returnTokens().size(); i++) {
                input2.add(b.returnTokens().get(i));
            }
            BlockTokens b2 = parseENE(b.returnBlock(), input2);
            return b2;
        } else {
            return b;
        }


    }

    private BlockTokens parseE(Block bl, List<Token> input) throws SyntaxException {
        if (input.get(0) instanceof T_Integer) {

            T_Integer t = (T_Integer) input.get(0);
            List l = bl.exps;
            IntLiteral i = new IntLiteral(t.n);

            List<Token> input2 = new ArrayList<Token>();


            for (int in = 1; in < input.size(); in++) {
                input2.add(input.get(in));
            }


            l.add(i);
            Block b = new Block(l);
            BlockTokens bt = new BlockTokens(b, input2);
            return bt;

        } else {
            if (input.get(0) instanceof T_Skip) {

                List<Token> input2 = new ArrayList<Token>();

                for (int in = 1; in < input.size(); in++) {
                    input2.add(input.get(in));
                }

                List l = bl.exps;
                l.add(new Skip());
                Block b2 = new Block(l);
                BlockTokens bt2 = new BlockTokens(b2, input2);
                return bt2;


            } else {
                List<Exp> emptyList = new ArrayList<>();
                Block emptyBlock = new Block(emptyList);

                BlockTokens bt22 = parseBlock(emptyBlock, input);

                BlockExp be = new BlockExp(bt22.returnBlock());

                bl.exps.add(be);

                BlockTokens finalBT = new BlockTokens(bl, bt22.returnTokens());


                return finalBT;
            }
        }

    }
}




 class Block {

    public List<Exp> exps;

    public Block(List<Exp> _exps) {
       // assert (_exps.size() > 0);
        exps = _exps;
    }
}


abstract class Exp {
}

class IntLiteral extends Exp {

    public int n;

    IntLiteral(int _n) {
        n = _n;
    }
}

class Skip extends Exp {

    public Skip() {
    }
}

class BlockExp extends Exp {

    public Block b;

    public BlockExp(Block _b) {
        b = _b;
    }
}

 class BlockTokens {

    private Block b;
    private List<Token> t;

    public BlockTokens(Block b, List<Token> t) {
        this.b = b;
        this.t = t;
    }

    Block returnBlock() {
        return b;
    }

    List<Token> returnTokens() {
        return t;
    }
}

 class SyntaxException extends Exception {
    
    public String msg;
    public SyntaxException ( String _msg ) { msg = _msg; }
}
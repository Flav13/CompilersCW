package compilers;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 132187
 */
public class Task1 {

    public static Lexer create() {
        LexerImpl l = new LexerImpl();
        return l;
    }
}

interface Lexer {

    public List<Token> lex(String input) throws LexicalException;
}

class LexerImpl implements Lexer {

    @Override
    public List<Token> lex(String input) throws LexicalException {
        List<Token> tokens = new ArrayList<>();

        Lex lexer = new Lex(new StringReader(input));
        Token nextResult = null;
        try {
            nextResult = lexer.yylex();
            while (nextResult != null) {
                tokens.add(nextResult);
                nextResult = lexer.yylex();
            }
        } catch (IOException ex) {
            Logger.getLogger(LexerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tokens;

    }
}

interface Token {
}

class T_Semicolon implements Token {
} // represents ;
class T_LeftBracket implements Token {
} // represents (
class T_RightBracket implements Token {
} // represents )
class T_EqualDefines implements Token {
} // represents =
class T_Equal implements Token {
} // represents ==
class T_LessThan implements Token {
} // represents < 
class T_GreaterThan implements Token {
} // represents >
class T_LessEq implements Token {
} // represents <=
class T_GreaterEq implements Token {
} // represents >=
class T_Comma implements Token {
} // represents ,
class T_LeftCurlyBracket implements Token {
} // represents {
class T_RightCurlyBracket implements Token {
} // represents }
class T_Assign implements Token {
} // represents :=
class T_Plus implements Token {
} // represents +
class T_Times implements Token {
} // represents *
class T_Minus implements Token {
} // represents -
class T_Div implements Token {
} // represents /
class T_Identifier implements Token { // represents names like x, i, n, numberOfNodes ...

    public String s;

    public T_Identifier(String _s) {
        s = _s;
    }
}

class T_Integer implements Token { // represents non-negative numbers like 0, 1, 2, 3, ...

    public int n;

    public T_Integer(int _n) {
        n = _n;
    }
}

class T_Def implements Token {
} // represents def
class T_Skip implements Token {
} // represents skip
class T_If implements Token {
} // represents if
class T_Then implements Token {
} // represents then
class T_Else implements Token {
} // represents else
class T_While implements Token {
} // represents while
class T_Do implements Token {
} // represents do
class T_Repeat implements Token {
} // represents repeat
class T_Until implements Token {
} // represents until
class T_Break implements Token {
} // represents break
class T_Continue implements Token {
} // represents continue

class LexicalException extends Exception {

    public String msg;

    public LexicalException(String _msg) {
        msg = _msg;
    }
}

class Lex {

    /**
     * This character denotes the end of file
     */
    public static final int YYEOF = -1;
    /**
     * initial size of the lookahead buffer
     */
    private static final int ZZ_BUFFERSIZE = 16384;
    /**
     * lexical states
     */
    public static final int YYINITIAL = 0;
    /**
     * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
     * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l at the
     * beginning of a line l is of the form l = 2*k, k a non negative integer
     */
    private static final int ZZ_LEXSTATE[] = {
        0, 0
    };
    /**
     * Translates characters to character classes
     */
    private static final String ZZ_CMAP_PACKED =
            "\11\0\1\6\1\4\1\0\1\6\1\5\22\0\1\6\7\0\1\16"
            + "\1\17\1\12\1\11\1\20\1\10\1\0\1\24\12\3\1\23\1\7"
            + "\1\14\1\13\1\15\2\0\32\2\4\0\1\1\1\0\1\43\1\45"
            + "\1\46\1\33\1\31\1\32\1\1\1\26\1\27\1\1\1\35\1\30"
            + "\1\1\1\40\1\41\1\36\1\1\1\42\1\34\1\37\1\44\1\1"
            + "\1\25\3\1\1\21\1\0\1\22\54\0\1\2\12\0\1\2\4\0"
            + "\1\2\5\0\27\2\1\0\37\2\1\0\u01ca\2\4\0\14\2\16\0"
            + "\5\2\7\0\1\2\1\0\1\2\21\0\165\2\1\0\2\2\2\0"
            + "\4\2\1\0\1\2\6\0\1\2\1\0\3\2\1\0\1\2\1\0"
            + "\24\2\1\0\123\2\1\0\213\2\1\0\255\2\1\0\46\2\2\0"
            + "\1\2\7\0\47\2\11\0\55\2\1\0\1\2\1\0\2\2\1\0"
            + "\2\2\1\0\1\2\10\0\33\2\5\0\3\2\35\0\13\2\5\0"
            + "\112\2\4\0\146\2\1\0\10\2\2\0\12\2\1\0\23\2\2\0"
            + "\1\2\20\0\73\2\2\0\145\2\16\0\66\2\4\0\1\2\5\0"
            + "\56\2\22\0\34\2\104\0\23\2\61\0\200\2\2\0\12\2\1\0"
            + "\23\2\1\0\10\2\2\0\2\2\2\0\26\2\1\0\7\2\1\0"
            + "\1\2\3\0\4\2\2\0\11\2\2\0\2\2\2\0\4\2\10\0"
            + "\1\2\4\0\2\2\1\0\5\2\2\0\14\2\17\0\3\2\1\0"
            + "\6\2\4\0\2\2\2\0\26\2\1\0\7\2\1\0\2\2\1\0"
            + "\2\2\1\0\2\2\2\0\1\2\1\0\5\2\4\0\2\2\2\0"
            + "\3\2\3\0\1\2\7\0\4\2\1\0\1\2\7\0\20\2\13\0"
            + "\3\2\1\0\11\2\1\0\3\2\1\0\26\2\1\0\7\2\1\0"
            + "\2\2\1\0\5\2\2\0\12\2\1\0\3\2\1\0\3\2\2\0"
            + "\1\2\17\0\4\2\2\0\12\2\21\0\3\2\1\0\10\2\2\0"
            + "\2\2\2\0\26\2\1\0\7\2\1\0\2\2\1\0\5\2\2\0"
            + "\11\2\2\0\2\2\2\0\3\2\10\0\2\2\4\0\2\2\1\0"
            + "\5\2\2\0\12\2\1\0\1\2\20\0\2\2\1\0\6\2\3\0"
            + "\3\2\1\0\4\2\3\0\2\2\1\0\1\2\1\0\2\2\3\0"
            + "\2\2\3\0\3\2\3\0\14\2\4\0\5\2\3\0\3\2\1\0"
            + "\4\2\2\0\1\2\6\0\1\2\16\0\12\2\20\0\4\2\1\0"
            + "\10\2\1\0\3\2\1\0\27\2\1\0\20\2\3\0\10\2\1\0"
            + "\3\2\1\0\4\2\7\0\2\2\1\0\2\2\6\0\4\2\2\0"
            + "\12\2\21\0\3\2\1\0\10\2\1\0\3\2\1\0\27\2\1\0"
            + "\12\2\1\0\5\2\2\0\11\2\1\0\3\2\1\0\4\2\7\0"
            + "\2\2\7\0\1\2\1\0\4\2\2\0\12\2\1\0\2\2\16\0"
            + "\3\2\1\0\10\2\1\0\3\2\1\0\51\2\2\0\10\2\1\0"
            + "\3\2\1\0\5\2\10\0\1\2\10\0\4\2\2\0\12\2\12\0"
            + "\6\2\2\0\2\2\1\0\22\2\3\0\30\2\1\0\11\2\1\0"
            + "\1\2\2\0\7\2\3\0\1\2\4\0\6\2\1\0\1\2\1\0"
            + "\10\2\6\0\12\2\2\0\2\2\15\0\72\2\5\0\17\2\1\0"
            + "\12\2\47\0\2\2\1\0\1\2\2\0\2\2\1\0\1\2\2\0"
            + "\1\2\6\0\4\2\1\0\7\2\1\0\3\2\1\0\1\2\1\0"
            + "\1\2\2\0\2\2\1\0\15\2\1\0\3\2\2\0\5\2\1\0"
            + "\1\2\1\0\6\2\2\0\12\2\2\0\4\2\40\0\1\2\27\0"
            + "\2\2\6\0\12\2\13\0\1\2\1\0\1\2\1\0\1\2\4\0"
            + "\12\2\1\0\44\2\4\0\24\2\1\0\22\2\1\0\44\2\11\0"
            + "\1\2\71\0\112\2\6\0\116\2\2\0\46\2\1\0\1\2\5\0"
            + "\1\2\2\0\53\2\1\0\u014d\2\1\0\4\2\2\0\7\2\1\0"
            + "\1\2\1\0\4\2\2\0\51\2\1\0\4\2\2\0\41\2\1\0"
            + "\4\2\2\0\7\2\1\0\1\2\1\0\4\2\2\0\17\2\1\0"
            + "\71\2\1\0\4\2\2\0\103\2\2\0\3\2\40\0\20\2\20\0"
            + "\125\2\14\0\u026c\2\2\0\21\2\1\0\32\2\5\0\113\2\3\0"
            + "\13\2\7\0\15\2\1\0\7\2\13\0\25\2\13\0\24\2\14\0"
            + "\15\2\1\0\3\2\1\0\2\2\14\0\124\2\3\0\1\2\4\0"
            + "\2\2\2\0\12\2\41\0\3\2\2\0\12\2\6\0\130\2\10\0"
            + "\53\2\5\0\106\2\12\0\37\2\1\0\14\2\4\0\14\2\12\0"
            + "\50\2\2\0\5\2\13\0\54\2\4\0\32\2\6\0\12\2\46\0"
            + "\34\2\4\0\77\2\1\0\35\2\2\0\13\2\6\0\12\2\15\0"
            + "\1\2\10\0\17\2\101\0\114\2\4\0\12\2\21\0\11\2\14\0"
            + "\164\2\14\0\70\2\10\0\12\2\3\0\61\2\122\0\3\2\1\0"
            + "\43\2\1\0\2\2\6\0\366\2\6\0\u011a\2\2\0\6\2\2\0"
            + "\46\2\2\0\6\2\2\0\10\2\1\0\1\2\1\0\1\2\1\0"
            + "\1\2\1\0\37\2\2\0\65\2\1\0\7\2\1\0\1\2\3\0"
            + "\3\2\1\0\7\2\3\0\4\2\2\0\6\2\4\0\15\2\5\0"
            + "\3\2\1\0\7\2\102\0\2\2\23\0\1\2\34\0\1\2\15\0"
            + "\1\2\20\0\15\2\63\0\41\2\21\0\1\2\4\0\1\2\2\0"
            + "\12\2\1\0\1\2\3\0\5\2\6\0\1\2\1\0\1\2\1\0"
            + "\1\2\1\0\4\2\1\0\13\2\2\0\4\2\5\0\5\2\4\0"
            + "\1\2\21\0\51\2\u032d\0\64\2\u0716\0\57\2\1\0\57\2\1\0"
            + "\205\2\6\0\11\2\14\0\46\2\1\0\1\2\5\0\1\2\2\0"
            + "\70\2\7\0\1\2\17\0\30\2\11\0\7\2\1\0\7\2\1\0"
            + "\7\2\1\0\7\2\1\0\7\2\1\0\7\2\1\0\7\2\1\0"
            + "\7\2\1\0\40\2\57\0\1\2\u01d5\0\3\2\31\0\17\2\1\0"
            + "\5\2\2\0\5\2\4\0\126\2\2\0\2\2\2\0\3\2\1\0"
            + "\132\2\1\0\4\2\5\0\51\2\3\0\136\2\21\0\33\2\65\0"
            + "\20\2\u0200\0\u19b6\2\112\0\u51cd\2\63\0\u048d\2\103\0\56\2\2\0"
            + "\u010d\2\3\0\34\2\24\0\63\2\1\0\12\2\1\0\37\2\1\0"
            + "\123\2\45\0\11\2\2\0\147\2\2\0\4\2\1\0\36\2\2\0"
            + "\2\2\105\0\61\2\30\0\64\2\14\0\105\2\13\0\12\2\6\0"
            + "\30\2\3\0\1\2\4\0\56\2\2\0\44\2\14\0\35\2\3\0"
            + "\101\2\16\0\13\2\6\0\37\2\1\0\67\2\11\0\16\2\2\0"
            + "\12\2\6\0\27\2\3\0\111\2\30\0\3\2\2\0\20\2\2\0"
            + "\5\2\12\0\6\2\2\0\6\2\2\0\6\2\11\0\7\2\1\0"
            + "\7\2\1\0\53\2\1\0\4\2\4\0\2\2\132\0\53\2\1\0"
            + "\2\2\2\0\12\2\6\0\u2ba4\2\14\0\27\2\4\0\61\2\u2104\0"
            + "\u016e\2\2\0\152\2\46\0\7\2\14\0\5\2\5\0\14\2\1\0"
            + "\15\2\1\0\5\2\1\0\1\2\1\0\2\2\1\0\2\2\1\0"
            + "\154\2\41\0\u016b\2\22\0\100\2\2\0\66\2\50\0\14\2\4\0"
            + "\20\2\20\0\16\2\5\0\2\2\30\0\3\2\40\0\5\2\1\0"
            + "\207\2\23\0\12\2\7\0\32\2\4\0\1\2\1\0\32\2\13\0"
            + "\131\2\3\0\6\2\2\0\6\2\2\0\6\2\2\0\3\2\43\0"
            + "\14\2\1\0\32\2\1\0\23\2\1\0\2\2\1\0\17\2\2\0"
            + "\16\2\42\0\173\2\105\0\65\2\210\0\1\2\202\0\35\2\3\0"
            + "\61\2\17\0\1\2\37\0\40\2\20\0\33\2\5\0\53\2\5\0"
            + "\36\2\2\0\44\2\4\0\10\2\1\0\5\2\52\0\236\2\2\0"
            + "\12\2\126\0\50\2\10\0\64\2\234\0\u0137\2\11\0\26\2\12\0"
            + "\10\2\230\0\6\2\2\0\1\2\1\0\54\2\1\0\2\2\3\0"
            + "\1\2\2\0\27\2\12\0\27\2\11\0\37\2\141\0\26\2\12\0"
            + "\32\2\106\0\70\2\6\0\2\2\100\0\4\2\1\0\2\2\5\0"
            + "\10\2\1\0\3\2\1\0\33\2\4\0\3\2\4\0\1\2\40\0"
            + "\35\2\3\0\35\2\43\0\10\2\1\0\36\2\31\0\66\2\12\0"
            + "\26\2\12\0\23\2\15\0\22\2\156\0\111\2\u03b7\0\107\2\37\0"
            + "\12\2\17\0\74\2\25\0\31\2\7\0\12\2\6\0\65\2\1\0"
            + "\12\2\20\0\44\2\2\0\1\2\11\0\105\2\13\0\13\2\45\0"
            + "\22\2\1\0\45\2\170\0\73\2\5\0\12\2\7\0\3\2\1\0"
            + "\10\2\2\0\2\2\2\0\26\2\1\0\7\2\1\0\2\2\1\0"
            + "\5\2\2\0\11\2\2\0\2\2\2\0\3\2\11\0\1\2\5\0"
            + "\7\2\2\0\7\2\3\0\5\2\u010b\0\106\2\1\0\1\2\10\0"
            + "\12\2\246\0\66\2\2\0\11\2\77\0\101\2\3\0\1\2\13\0"
            + "\12\2\46\0\70\2\10\0\12\2\u01d6\0\112\2\25\0\1\2\u01c0\0"
            + "\71\2\u0507\0\u0399\2\147\0\157\2\u0b91\0\u042f\2\u33d1\0\u0239\2\7\0"
            + "\37\2\1\0\12\2\146\0\36\2\2\0\5\2\13\0\67\2\11\0"
            + "\4\2\14\0\12\2\11\0\25\2\5\0\23\2\u0370\0\105\2\13\0"
            + "\57\2\20\0\21\2\u4060\0\2\2\u0bfe\0\153\2\5\0\15\2\3\0"
            + "\11\2\7\0\12\2\3\0\2\2\u14c6\0\5\2\3\0\6\2\10\0"
            + "\10\2\2\0\7\2\36\0\4\2\224\0\3\2\u01bb\0\125\2\1\0"
            + "\107\2\1\0\2\2\2\0\1\2\2\0\2\2\2\0\4\2\1\0"
            + "\14\2\1\0\1\2\1\0\7\2\1\0\101\2\1\0\4\2\2\0"
            + "\10\2\1\0\7\2\1\0\34\2\1\0\4\2\1\0\5\2\1\0"
            + "\1\2\3\0\7\2\1\0\u0154\2\2\0\31\2\1\0\31\2\1\0"
            + "\37\2\1\0\31\2\1\0\37\2\1\0\31\2\1\0\37\2\1\0"
            + "\31\2\1\0\37\2\1\0\31\2\1\0\10\2\2\0\62\2\u1000\0"
            + "\305\2\13\0\7\2\u0529\0\4\2\1\0\33\2\1\0\2\2\1\0"
            + "\1\2\2\0\1\2\1\0\12\2\1\0\4\2\1\0\1\2\1\0"
            + "\1\2\6\0\1\2\4\0\1\2\1\0\1\2\1\0\1\2\1\0"
            + "\3\2\1\0\2\2\1\0\1\2\2\0\1\2\1\0\1\2\1\0"
            + "\1\2\1\0\1\2\1\0\1\2\1\0\2\2\1\0\1\2\2\0"
            + "\4\2\1\0\7\2\1\0\4\2\1\0\4\2\1\0\1\2\1\0"
            + "\12\2\1\0\21\2\5\0\3\2\1\0\5\2\1\0\21\2\u0274\0"
            + "\32\2\6\0\32\2\6\0\32\2\u0e76\0\ua6d7\2\51\0\u1035\2\13\0"
            + "\336\2\u3fe2\0\u021e\2\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u06ed\0"
            + "\360\2\uffff\0\uffff\0\ufe12\0";
    /**
     * Translates characters to character classes
     */
    private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);
    /**
     * Translates DFA states to action switch labels.
     */
    private static final int[] ZZ_ACTION = zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 =
            "\1\0\1\1\1\2\1\3\2\4\1\5\1\6\1\7"
            + "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"
            + "\1\20\1\1\1\21\12\2\1\22\1\23\1\24\1\25"
            + "\1\2\1\26\2\2\1\27\10\2\1\30\7\2\1\31"
            + "\1\32\1\33\4\2\1\34\1\2\1\35\1\36\1\2"
            + "\1\37\2\2\1\40";

    private static int[] zzUnpackAction() {
        int[] result = new int[71];
        int offset = 0;
        offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAction(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }
    /**
     * Translates a state to a row index in the transition table
     */
    private static final int[] ZZ_ROWMAP = zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 =
            "\0\0\0\47\0\116\0\165\0\47\0\234\0\47\0\47"
            + "\0\47\0\47\0\303\0\352\0\u0111\0\47\0\47\0\47"
            + "\0\47\0\47\0\u0138\0\47\0\u015f\0\u0186\0\u01ad\0\u01d4"
            + "\0\u01fb\0\u0222\0\u0249\0\u0270\0\u0297\0\u02be\0\47\0\47"
            + "\0\47\0\47\0\u02e5\0\116\0\u030c\0\u0333\0\116\0\u035a"
            + "\0\u0381\0\u03a8\0\u03cf\0\u03f6\0\u041d\0\u0444\0\u046b\0\116"
            + "\0\u0492\0\u04b9\0\u04e0\0\u0507\0\u052e\0\u0555\0\u057c\0\116"
            + "\0\116\0\116\0\u05a3\0\u05ca\0\u05f1\0\u0618\0\116\0\u063f"
            + "\0\116\0\116\0\u0666\0\116\0\u068d\0\u06b4\0\116";

    private static int[] zzUnpackRowMap() {
        int[] result = new int[71];
        int offset = 0;
        offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackRowMap(String packed, int offset, int[] result) {
        int i = 0;  /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int high = packed.charAt(i++) << 16;
            result[j++] = high | packed.charAt(i++);
        }
        return j;
    }
    /**
     * The transition table of the DFA
     */
    private static final int[] ZZ_TRANS = zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 =
            "\1\2\1\3\1\2\1\4\1\5\1\6\1\5\1\7"
            + "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"
            + "\1\20\1\21\1\22\1\23\1\24\1\25\1\3\1\26"
            + "\1\3\1\27\1\3\1\30\1\31\2\3\1\32\2\3"
            + "\1\33\1\3\1\34\1\35\1\36\50\0\3\3\21\0"
            + "\22\3\3\0\1\4\47\0\1\5\55\0\1\37\46\0"
            + "\1\40\46\0\1\41\46\0\1\42\34\0\3\3\21\0"
            + "\1\3\1\43\20\3\1\0\3\3\21\0\5\3\1\44"
            + "\14\3\1\0\3\3\21\0\3\3\1\45\16\3\1\0"
            + "\3\3\21\0\4\3\1\46\7\3\1\47\5\3\1\0"
            + "\3\3\21\0\10\3\1\50\11\3\1\0\3\3\21\0"
            + "\1\3\1\51\20\3\1\0\3\3\21\0\4\3\1\52"
            + "\15\3\1\0\3\3\21\0\13\3\1\53\6\3\1\0"
            + "\3\3\21\0\15\3\1\54\4\3\1\0\3\3\21\0"
            + "\14\3\1\55\5\3\1\0\3\3\21\0\2\3\1\56"
            + "\17\3\1\0\3\3\21\0\7\3\1\57\12\3\1\0"
            + "\3\3\21\0\5\3\1\60\14\3\1\0\3\3\21\0"
            + "\2\3\1\61\17\3\1\0\3\3\21\0\4\3\1\62"
            + "\15\3\1\0\3\3\21\0\11\3\1\63\10\3\1\0"
            + "\3\3\21\0\12\3\1\64\7\3\1\0\3\3\21\0"
            + "\4\3\1\65\15\3\1\0\3\3\21\0\13\3\1\66"
            + "\6\3\1\0\3\3\21\0\3\3\1\67\16\3\1\0"
            + "\3\3\21\0\4\3\1\70\15\3\1\0\3\3\21\0"
            + "\11\3\1\71\10\3\1\0\3\3\21\0\13\3\1\72"
            + "\6\3\1\0\3\3\21\0\4\3\1\73\15\3\1\0"
            + "\3\3\21\0\2\3\1\74\17\3\1\0\3\3\21\0"
            + "\16\3\1\75\3\3\1\0\3\3\21\0\12\3\1\76"
            + "\7\3\1\0\3\3\21\0\4\3\1\77\15\3\1\0"
            + "\3\3\21\0\16\3\1\100\3\3\1\0\3\3\21\0"
            + "\3\3\1\101\16\3\1\0\3\3\21\0\10\3\1\102"
            + "\11\3\1\0\3\3\21\0\2\3\1\103\17\3\1\0"
            + "\3\3\21\0\12\3\1\104\7\3\1\0\3\3\21\0"
            + "\13\3\1\105\6\3\1\0\3\3\21\0\17\3\1\106"
            + "\2\3\1\0\3\3\21\0\4\3\1\107\15\3";

    private static int[] zzUnpackTrans() {
        int[] result = new int[1755];
        int offset = 0;
        offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackTrans(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            value--;
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }
    /* error codes */
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;

    /* error messages for the codes above */
    private static final String ZZ_ERROR_MSG[] = {
        "Unknown internal scanner error",
        "Error: could not match input",
        "Error: pushback value was too large"
    };
    /**
     * ZZ_ATTRIBUTE[aState] contains the attributes of state
     * <code>aState</code>
     */
    private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 =
            "\1\0\1\11\2\1\1\11\1\1\4\11\3\1\5\11"
            + "\1\1\1\11\12\1\4\11\45\1";

    private static int[] zzUnpackAttribute() {
        int[] result = new int[71];
        int offset = 0;
        offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAttribute(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }
    /**
     * the input device
     */
    private java.io.Reader zzReader;
    /**
     * the current state of the DFA
     */
    private int zzState;
    /**
     * the current lexical state
     */
    private int zzLexicalState = YYINITIAL;
    /**
     * this buffer contains the current text to be matched and is the source of
     * the yytext() string
     */
    private char zzBuffer[] = new char[ZZ_BUFFERSIZE];
    /**
     * the textposition at the last accepting state
     */
    private int zzMarkedPos;
    /**
     * the current text position in the buffer
     */
    private int zzCurrentPos;
    /**
     * startRead marks the beginning of the yytext() string in the buffer
     */
    private int zzStartRead;
    /**
     * endRead marks the last character in the buffer, that has been read from
     * input
     */
    private int zzEndRead;
    /**
     * number of newlines encountered up to the start of the matched text
     */
    private int yyline;
    /**
     * the number of characters up to the start of the matched text
     */
    private int yychar;
    /**
     * the number of characters from the last newline up to the start of the
     * matched text
     */
    private int yycolumn;
    /**
     * zzAtBOL == true <=> the scanner is currently at the beginning of a line
     */
    private boolean zzAtBOL = true;
    /**
     * zzAtEOF == true <=> the scanner is at the EOF
     */
    private boolean zzAtEOF;
    /**
     * denotes if the user-EOF-code has already been executed
     */
    private boolean zzEOFDone;
    /**
     * The number of occupied positions in zzBuffer beyond zzEndRead. When a
     * lead/high surrogate has been read from the input stream into the final
     * zzBuffer position, this will have a value of 1; otherwise, it will have a
     * value of 0.
     */
    private int zzFinalHighSurrogate = 0;

    /**
     * Creates a new scanner
     *
     * @param in the java.io.Reader to read input from.
     */
    public Lex(java.io.Reader in) {
        this.zzReader = in;
    }

    /**
     * Unpacks the compressed character translation table.
     *
     * @param packed the packed character translation table
     * @return the unpacked character translation table
     */
    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[0x110000];
        int i = 0;  /* index in packed string  */
        int j = 0;  /* index in unpacked array */
        while (i < 2638) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do {
                map[j++] = value;
            } while (--count > 0);
        }
        return map;
    }

    /**
     * Refills the input buffer.
     *
     * @return      <code>false</code>, iff there was new input.
     *
     * @exception java.io.IOException if any I/O-Error occurs
     */
    private boolean zzRefill() throws java.io.IOException {

        /* first: make room (if you can) */
        if (zzStartRead > 0) {
            zzEndRead += zzFinalHighSurrogate;
            zzFinalHighSurrogate = 0;
            System.arraycopy(zzBuffer, zzStartRead,
                    zzBuffer, 0,
                    zzEndRead - zzStartRead);

            /* translate stored positions */
            zzEndRead -= zzStartRead;
            zzCurrentPos -= zzStartRead;
            zzMarkedPos -= zzStartRead;
            zzStartRead = 0;
        }

        /* is the buffer big enough? */
        if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
            /* if not: blow it up */
            char newBuffer[] = new char[zzBuffer.length * 2];
            System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
            zzBuffer = newBuffer;
            zzEndRead += zzFinalHighSurrogate;
            zzFinalHighSurrogate = 0;
        }

        /* fill the buffer with new input */
        int requested = zzBuffer.length - zzEndRead;
        int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

        /* not supposed to occur according to specification of java.io.Reader */
        if (numRead == 0) {
            throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
        }
        if (numRead > 0) {
            zzEndRead += numRead;
            /* If numRead == requested, we might have requested to few chars to
             encode a full Unicode character. We assume that a Reader would
             otherwise never return half characters. */
            if (numRead == requested) {
                if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
                    --zzEndRead;
                    zzFinalHighSurrogate = 1;
                }
            }
            /* potentially more input available */
            return false;
        }

        /* numRead < 0 ==> end of stream */
        return true;
    }

    /**
     * Closes the input stream.
     */
    public final void yyclose() throws java.io.IOException {
        zzAtEOF = true;            /* indicate end of file */
        zzEndRead = zzStartRead;  /* invalidate buffer    */

        if (zzReader != null) {
            zzReader.close();
        }
    }

    /**
     * Resets the scanner to read from a new input stream. Does not close the
     * old reader.
     *
     * All internal variables are reset, the old input stream
     * <b>cannot</b> be reused (internal buffer is discarded and lost). Lexical
     * state is set to <tt>ZZ_INITIAL</tt>.
     *
     * Internal scan buffer is resized down to its initial length, if it has
     * grown.
     *
     * @param reader the new input stream
     */
    public final void yyreset(java.io.Reader reader) {
        zzReader = reader;
        zzAtBOL = true;
        zzAtEOF = false;
        zzEOFDone = false;
        zzEndRead = zzStartRead = 0;
        zzCurrentPos = zzMarkedPos = 0;
        zzFinalHighSurrogate = 0;
        yyline = yychar = yycolumn = 0;
        zzLexicalState = YYINITIAL;
        if (zzBuffer.length > ZZ_BUFFERSIZE) {
            zzBuffer = new char[ZZ_BUFFERSIZE];
        }
    }

    /**
     * Returns the current lexical state.
     */
    public final int yystate() {
        return zzLexicalState;
    }

    /**
     * Enters a new lexical state
     *
     * @param newState the new lexical state
     */
    public final void yybegin(int newState) {
        zzLexicalState = newState;
    }

    /**
     * Returns the text matched by the current regular expression.
     */
    public final String yytext() {
        return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
    }

    /**
     * Returns the character at position <tt>pos</tt> from the matched text.
     *
     * It is equivalent to yytext().charAt(pos), but faster
     *
     * @param pos the position of the character to fetch. A value from 0 to
     * yylength()-1.
     *
     * @return the character at position pos
     */
    public final char yycharat(int pos) {
        return zzBuffer[zzStartRead + pos];
    }

    /**
     * Returns the length of the matched text region.
     */
    public final int yylength() {
        return zzMarkedPos - zzStartRead;
    }

    /**
     * Reports an error that occured while scanning.
     *
     * In a wellformed scanner (no or only correct usage of yypushback(int) and
     * a match-all fallback rule) this method will only be called with things
     * that "Can't Possibly Happen". If this method is called, something is
     * seriously wrong (e.g. a JFlex bug producing a faulty scanner etc.).
     *
     * Usual syntax/scanner level error handling should be done in error
     * fallback rules.
     *
     * @param errorCode the code of the errormessage to display
     */
    private void zzScanError(int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        } catch (ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
        }

        throw new Error(message);
    }

    /**
     * Pushes the specified amount of characters back into the input stream.
     *
     * They will be read again by then next call of the scanning method
     *
     * @param number the number of characters to be read again. This number must
     * not be greater than yylength()!
     */
    public void yypushback(int number) {
        if (number > yylength()) {
            zzScanError(ZZ_PUSHBACK_2BIG);
        }

        zzMarkedPos -= number;
    }

    /**
     * Resumes scanning until the next regular expression is matched, the end of
     * input is encountered or an I/O-Error occurs.
     *
     * @return the next token
     * @exception java.io.IOException if any I/O-Error occurs
     */
    public Token yylex() throws java.io.IOException, LexicalException {
        int zzInput;
        int zzAction;

        // cached fields:
        int zzCurrentPosL;
        int zzMarkedPosL;
        int zzEndReadL = zzEndRead;
        char[] zzBufferL = zzBuffer;
        char[] zzCMapL = ZZ_CMAP;

        int[] zzTransL = ZZ_TRANS;
        int[] zzRowMapL = ZZ_ROWMAP;
        int[] zzAttrL = ZZ_ATTRIBUTE;

        while (true) {
            zzMarkedPosL = zzMarkedPos;

            zzAction = -1;

            zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

            zzState = ZZ_LEXSTATE[zzLexicalState];

            // set up zzAction for empty match case:
            int zzAttributes = zzAttrL[zzState];
            if ((zzAttributes & 1) == 1) {
                zzAction = zzState;
            }


            zzForAction:
            {
                while (true) {

                    if (zzCurrentPosL < zzEndReadL) {
                        zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
                        zzCurrentPosL += Character.charCount(zzInput);
                    } else if (zzAtEOF) {
                        zzInput = YYEOF;
                        break zzForAction;
                    } else {
                        // store back cached positions
                        zzCurrentPos = zzCurrentPosL;
                        zzMarkedPos = zzMarkedPosL;
                        boolean eof = zzRefill();
                        // get translated positions and possibly new buffer
                        zzCurrentPosL = zzCurrentPos;
                        zzMarkedPosL = zzMarkedPos;
                        zzBufferL = zzBuffer;
                        zzEndReadL = zzEndRead;
                        if (eof) {
                            zzInput = YYEOF;
                            break zzForAction;
                        } else {
                            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
                            zzCurrentPosL += Character.charCount(zzInput);
                        }
                    }
                    int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput]];
                    if (zzNext == -1) {
                        break zzForAction;
                    }
                    zzState = zzNext;

                    zzAttributes = zzAttrL[zzState];
                    if ((zzAttributes & 1) == 1) {
                        zzAction = zzState;
                        zzMarkedPosL = zzCurrentPosL;
                        if ((zzAttributes & 8) == 8) {
                            break zzForAction;
                        }
                    }

                }
            }

            // store back cached position
            zzMarkedPos = zzMarkedPosL;

            if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
                zzAtEOF = true;
                return null;
            } else {
                switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
                    case 1: {
                        throw new LexicalException("Illegal text <" + yytext() + ">");
                    }
                    case 33:
                        break;
                    case 2: {
                        return new T_Identifier(yytext());
                    }
                    case 34:
                        break;
                    case 3: {
                        return new T_Integer(Integer.parseInt(yytext()));
                    }
                    case 35:
                        break;
                    case 4: { /* Ignore white space */

                    }
                    case 36:
                        break;
                    case 5: {
                        return new T_Semicolon();
                    }
                    case 37:
                        break;
                    case 6: {
                        return new T_Minus();
                    }
                    case 38:
                        break;
                    case 7: {
                        return new T_Plus();
                    }
                    case 39:
                        break;
                    case 8: {
                        return new T_Times();
                    }
                    case 40:
                        break;
                    case 9: {
                        return new T_EqualDefines();
                    }
                    case 41:
                        break;
                    case 10: {
                        return new T_LessThan();
                    }
                    case 42:
                        break;
                    case 11: {
                        return new T_GreaterThan();
                    }
                    case 43:
                        break;
                    case 12: {
                        return new T_LeftBracket();
                    }
                    case 44:
                        break;
                    case 13: {
                        return new T_RightBracket();
                    }
                    case 45:
                        break;
                    case 14: {
                        return new T_Comma();
                    }
                    case 46:
                        break;
                    case 15: {
                        return new T_LeftCurlyBracket();
                    }
                    case 47:
                        break;
                    case 16: {
                        return new T_RightCurlyBracket();
                    }
                    case 48:
                        break;
                    case 17: {
                        return new T_Div();
                    }
                    case 49:
                        break;
                    case 18: {
                        return new T_Equal();
                    }
                    case 50:
                        break;
                    case 19: {
                        return new T_LessEq();
                    }
                    case 51:
                        break;
                    case 20: {
                        return new T_GreaterEq();
                    }
                    case 52:
                        break;
                    case 21: {
                        return new T_Assign();
                    }
                    case 53:
                        break;
                    case 22: {
                        return new T_If();
                    }
                    case 54:
                        break;
                    case 23: {
                        return new T_Do();
                    }
                    case 55:
                        break;
                    case 24: {
                        return new T_Def();
                    }
                    case 56:
                        break;
                    case 25: {
                        return new T_Else();
                    }
                    case 57:
                        break;
                    case 26: {
                        return new T_Skip();
                    }
                    case 58:
                        break;
                    case 27: {
                        return new T_Then();
                    }
                    case 59:
                        break;
                    case 28: {
                        return new T_While();
                    }
                    case 60:
                        break;
                    case 29: {
                        return new T_Until();
                    }
                    case 61:
                        break;
                    case 30: {
                        return new T_Break();
                    }
                    case 62:
                        break;
                    case 31: {
                        return new T_Repeat();
                    }
                    case 63:
                        break;
                    case 32: {
                        return new T_Continue();
                    }
                    case 64:
                        break;
                    default:
                        zzScanError(ZZ_NO_MATCH);
                }
            }
        }
    }
}

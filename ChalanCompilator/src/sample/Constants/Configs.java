package sample.Constants;

import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Configs {

    public static final String[] KEYWORDS = new String[] {
            "CREAR","TRAER","GENERAR","FIJO","PRIVADO","INT","STRING","DOUBLE",
            "PROCEDIMIENTO","ARREGLO", "NUEVO","PRINCIPAL","SI","SINO","HASTA"
    };
    public static final String[] FUNCIONES = new String[] {
            "POTENCIA","RAIZ","PI"
    };

    public static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    public static final String PAREN_PATTERN = "\\(|\\)";
    public static final String BRACE_PATTERN = "\\{|\\}|:|;";
    public static final String BRACKET_PATTERN = "\\[|\\]";
    public static final String SEMICOLON_PATTERN = "\\;";
    public static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    public static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
    public static final String NORMAL_PATTERN="(\\w+|\\.|[^"+String.join("|",FUNCIONES)+"])";
    public static final String SIMBOL_PATTERN="([+,\\-,=,/,*,!,<,>])";
    public static final String FUNCIONES_PATTERN="\\b(" + String.join("|", FUNCIONES) + ")\\b";

    public static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                    + "|(?<NORMAL>" + NORMAL_PATTERN + ")"
                    +"|(?<SIMBOL>" + SIMBOL_PATTERN + ")"
                    +"|(?<FUNCIONES>"+ FUNCIONES_PATTERN+")"
    );

    public static final String sampleCode = String.join("\n", new String[] {

            "",
            "",
            "CREAR PROCEDIMIENTO PRINCIPAL:HOLA;",
            "",
            "CREAR PROCEDIMIENTO Foo :",
            "",
            "    /*",
            "     * multi-line comment",
            "     */",
            "    public static void main(String[] args) {",
            "        // single-line comment",
            "        HASTA (INT LIM-LIM< OTROLIM-LIM+2): PROCESO; ",
            "            {  SI(arg.length() != 0)",
            "                System.out.println(arg);",
            "            SINO:",
            "                System.err.println(\"Warning: empty string as argument\");",
            "        }",
            "    }",
            "",
            "}"
    });
    public static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = Configs.PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("NORMAL")!=null ? "normal":
                                                                            matcher.group("SIMBOL")!=null ? "simbol":
                                                                                    matcher.group("FUNCIONES")!=null ? "funciones":
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
    public static String [] EXPRESIONES={
        "(\\s+)?CREAR(\\s+)?PROCEDIMIENTO(\\s+)?PRINCIPAL(\\s+)?:(\\s+)?(\\\n+)?\\w+(\\\n+)?(\\s+)?(;(\\s+)?)$", "(\\s+)?CREAR(\\s+)?PROCEDIMIENTO(\\s+)?[A-z]+(\\d+)?(\\s+)?:(\\s+)?(\\\n+)?\\w+(\\\n+)?(\\s+)?(;(\\s+)?)$",
            "(\\s+)?HASTA(\\s+)?\\(INT [A-z]+(\\d+)?(\\s+)?\\-(\\s+)?[A-z]+(\\d+)?(\\s+)?<(\\s+)?[A-z]+(\\d+)?(\\s+)?\\-(\\s+)?[A-z]+(\\d+)?(\\s+)?\\+\\d+(\\s+)?\\)(\\s+)?:(\\s+)?(\\\n+)?\\w+(\\\n+)?(\\s+)?(;(\\s+)?)$",
            "(\\s+)?STRING [A-z]+(\\d+)?","(\\s+)?DOUBLE [A-z]+(\\d+)?","INT [A-z]+(\\d+)?","(\\s+)?ARREGLO [A-z]+(\\d+)? x[1,2,3,4,5,6,7,8,9](\\s+)?",

    };
}

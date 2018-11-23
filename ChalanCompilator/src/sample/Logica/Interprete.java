package sample.Logica;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.control.TextArea;
import sample.Constants.TipoToken;

public class Interprete {
    TextArea txtConsola;

    public Interprete(String renglones, TextArea con) {
        this.txtConsola = con;
        compilar(renglones);

    }

    private void compilar(String renglones) {
        String[] r = renglones.split("\\n");
        for (int x = 0; x < r.length; x++) {
            if (r[x].contains("print")) {
                int x1 = r[x].indexOf("(");
                int x2 = r[x].indexOf(")");
                String texto = r[x].substring(x1+2, x2-1);
                this.txtConsola.setText(txtConsola.getText()+"\n"+texto);
            }

            if((r[x].contains("STRING"))||(r[x].contains("INT"))||(r[x].contains("DOUBLE"))||(r[x].contains("BOOLEAN"))){
                String[] td = r[x].split(" ");
            
                if (td[0].trim().equals(TipoToken.STRING)) {
                    Token token = new Token(TipoToken.STRING, "", td[1].trim());
                    if (ifExist(token)) {
                        txtConsola.appendText("\n" + " La variable " + td[1] + " ya existe");
                    } else {
                        TipoToken.Variables.add(token);
                    }
                }else if(td[0].trim().equals(TipoToken.INT)){
                    System.out.println("ENTRO");
                    Token token = new Token(TipoToken.INT, "", td[1].trim());
                    if (ifExist(token)) {
                        txtConsola.appendText("\n" + " La variable " + td[1] + " ya existe");
                    } else {
                        TipoToken.Variables.add(token);
                    }
                }else if(td[0].trim().equals(TipoToken.DOUBLE)){
                    System.out.println("ENTRO");
                    Token token = new Token(TipoToken.DOUBLE, "", td[1].trim());
                    if (ifExist(token)) {
                        txtConsola.appendText("\n" + " La variable " + td[1] + " ya existe");
                    } else {
                        TipoToken.Variables.add(token);
                    }
                }else if(td[0].trim().equals(TipoToken.BOOLEAN)){
                    System.out.println("ENTRO");
                    Token token = new Token(TipoToken.BOOLEAN, "", td[1].trim());
                    if (ifExist(token)) {
                        txtConsola.appendText("\n" + " La variable " + td[1] + " ya existe");
                    } else {
                        TipoToken.Variables.add(token);
                    }
                }
            }//lave for
        }
    }

    public boolean ifExist(Token t) {
        boolean exist=false;
        for (int x = 0; x < TipoToken.Variables.size() && exist==false; x++) {
            if (t.getName().equals(TipoToken.Variables.get(x).getName())) {
                exist=true;
            }
        }
        return  exist;
    }

}

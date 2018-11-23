package sample.Logica;

import javafx.scene.control.TextArea;
import sample.Constants.TipoToken;

public class Interprete {
    TextArea txtConsola;

    public Interprete(String renglones, TextArea con) {
        compilar(renglones);
        this.txtConsola = con;
    }

    private void compilar(String renglones) {
        String[] r = renglones.split("\\n");
        for (int x = 0; x < r.length; x++) {
            if (r[x].contains("print")) {
                int x1 = r[x].indexOf("(");
                int x2 = r[x].indexOf(")");
                String texto = r[x].substring(x1, x2);
                this.txtConsola.setText(texto);
            }

            if (r[x].contains("STRING")) {
                String[] td = r[x].split(" ");
                if (td[0].trim().equals(TipoToken.STRING)) {
                    Token token = new Token(TipoToken.STRING, "", td[1].trim());

                    if (ifExist(token)) {
                        txtConsola.appendText("\n" + " La variable " + td[1] + " ya existe");
                    } else {
                        TipoToken.Variables.add(token);
                    }
                }
            }
        }//lave for
    }

    public boolean ifExist(Token t) {
        boolean exist = false;
        for (int x = 0; x < TipoToken.Variables.size() && exist == false; x++) {
            if (t.getName().equals(TipoToken.Variables.get(x).getName())) {

            }
            exist=true;
        }
        return  exist;
    }

}

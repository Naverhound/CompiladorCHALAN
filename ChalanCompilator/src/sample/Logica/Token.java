package sample.Logica;

/**
 * Created by D10101 on 07/11/2018.
 */
public class Token {
    private String tipo;
    private String valor;
    private String name;

    public Token(String tipo, String valor, String name) {
        this.tipo = tipo;
        this.valor = valor;
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

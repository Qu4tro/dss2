package Classes;

import java.util.GregorianCalendar;

public class Pagamento {

    // Variaveis de instância
    private String modalidade;
    private GregorianCalendar data;
    private Float valor_pago;
    private String credor;
    private String devedor;

    //Construtores

    public Pagamento(String data, Float valor, String modo, String credor, String devedor) {

        this.data = Utils.parseData(data); // Mudar, tem de se meter a exception
        this.valor_pago = valor;
        this.modalidade = modo;
        this.credor = credor;
        this.devedor = devedor;
    }

    //Metodos


    public boolean eliminarPagamento() {
        // todo DAO
        return false;
    }

    public GregorianCalendar getData() {
        return this.data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    public Float getValor() {
        return this.valor_pago;
    }

    public void setValor(Float valor) {
        this.valor_pago = valor;
    }

    public String getModo() {
        return this.modalidade;
    }

    public void setModo(String modo) {
        this.modalidade = modo;
    }

    public String getCredor() {
        return this.credor;
    }

    public void setCredor(String user) {
        this.credor = user;
    }

    public String getDevedor() {
        return this.devedor;
    }

    public void setDevedor(String user) {
        this.devedor = user;
    }

}

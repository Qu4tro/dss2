package Classes;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.GregorianCalendar;

public class Pagamento {

    // Variaveis de instância
    private Integer id;
    private String modalidade;
    private GregorianCalendar data;
    private Integer valor_pago;
    private String credor;
    private String devedor;

    //Construtores
    public Pagamento(){
        
    }
    public Pagamento(String data, Integer valor, String modo, String credor, String devedor) {

        this.data = Utils.parseData(data); // Mudar, tem de se meter a exception
        this.valor_pago = valor;
        this.modalidade = modo;
        this.credor = credor;
        this.devedor = devedor;
    }
        public Pagamento (int v, String c, String d){
       
        this.valor_pago=v;
        this.modalidade = "Internet";
        this.credor = c;
        this.devedor = d;
    }
    //Metodos

    public Integer getID(){
        return id;
    }

    public void setID(Integer id){
        this.id = id;
    }

    public GregorianCalendar getData() {
        return this.data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    public Integer getValor() {
        return this.valor_pago;
    }

    public void setValor(Integer valor) {
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

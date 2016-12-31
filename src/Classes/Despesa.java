package Classes;

import java.util.GregorianCalendar;
import java.util.Map;

public class Despesa {

    // Variaveis de Instância
    private Integer id;
    private String descricao;
    private GregorianCalendar dataCriacao;
    private GregorianCalendar dataDespesa;
    private String responsavel;
    private Float valor;
    private Integer tempoRecurrencia;
    private GregorianCalendar dataLimite;
    private Map<String, Float> racioPagamentoFeito;
    private Map<String, Float> racioPagamentoDevido;
    private Integer grupo;

    // Construtores

    public Despesa(){

    }

    public Despesa(String descricao, Float valor, String responsavel,
                   GregorianCalendar dataCriacao, GregorianCalendar dataDespesa) {
        setDescricao(descricao);
        setValor(valor);
        setResponsavel(responsavel);
        setDataDespesa(dataDespesa);
        this.dataCriacao = dataCriacao;
        tempoRecurrencia = -1;
        dataLimite = null;
    }

    // Metodos
    public void eliminarDespesa() {

    }

    public int getID(){
        return id;
    }

    public void setID(Integer id){
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String user) {
        this.responsavel = user;
    }

    public GregorianCalendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String data) {
        this.dataCriacao = Utils.parseData(data);
    }

    public GregorianCalendar getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(GregorianCalendar data) {
        this.dataDespesa = data;
    }

    public void setRacioPagamentoFeito(Utilizador user, Float racio) {
        racioPagamentoFeito.put(user.getNickname(), racio);
    }

    public void setRacioPagamentoDevido(Utilizador user, Float racio) {
        racioPagamentoDevido.put(user.getNickname(), racio);
    }

    public Float getRacioPagamentoFeito(Utilizador user) {
        return racioPagamentoFeito.get(user.getNickname());
    }

    public Float getRacioPagamentoDevido(Utilizador user) {
        return racioPagamentoDevido.get(user.getNickname());
    }

    public boolean eRecurrente() {
        return tempoRecurrencia > 0;
    }

    public void setRecurrente(Integer tempoRecurrencia, String data) {
        setTempoDeRecurrencia(tempoRecurrencia);
        setDataLimite(data);
    }

    public Integer getTempoDeRecurrencia() {
        return tempoRecurrencia;
    }

    public void setTempoDeRecurrencia(Integer tempoRecurrencia) {
        this.tempoRecurrencia = tempoRecurrencia;
    }

    public GregorianCalendar getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(String data) {
        dataLimite = Utils.parseData(data);
    }

    public void setGrupo(Integer id){
        grupo = id;
    }

    public Integer getGrupo(){
        return grupo;
    }
}

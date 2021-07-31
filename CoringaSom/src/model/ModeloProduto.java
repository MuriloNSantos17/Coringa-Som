package model;

public class ModeloProduto {

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the id_responsavel
     */
    public int getId_responsavel() {
        return id_responsavel;
    }

    /**
     * @param id_responsavel the id_responsavel to set
     */
    public void setId_responsavel(int id_responsavel) {
        this.id_responsavel = id_responsavel;
    }

    /**
     * @return the status_estoque
     */
    public String getStatus_estoque() {
        return status_estoque;
    }

    /**
     * @param status_estoque the status_estoque to set
     */
    public void setStatus_estoque(String status_estoque) {
        this.status_estoque = status_estoque;
    }

    /**
     * @return the estoque_inicial
     */
    public int getEstoque_inicial() {
        return estoque_inicial;
    }

    /**
     * @param estoque_inicial the estoque_inicial to set
     */
    public void setEstoque_inicial(int estoque_inicial) {
        this.estoque_inicial = estoque_inicial;
    }

    /**
     * @return the estoque_final
     */
    public int getEstoque_final() {
        return estoque_final;
    }

    /**
     * @param estoque_final the estoque_final to set
     */
    public void setEstoque_final(int estoque_final) {
        this.estoque_final = estoque_final;
    }

    /**
     * @return the ds_movimentacao
     */
    public String getDs_movimentacao() {
        return ds_movimentacao;
    }

    /**
     * @param ds_movimentacao the ds_movimentacao to set
     */
    public void setDs_movimentacao(String ds_movimentacao) {
        this.ds_movimentacao = ds_movimentacao;
    }

    /**
     * @return the id_tipo
     */
    public int getId_tipo() {
        return id_tipo;
    }

    /**
     * @param id_tipo the id_tipo to set
     */
    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cd_barras
     */
    public String getCd_barras() {
        return cd_barras;
    }

    /**
     * @param cd_barras the cd_barras to set
     */
    public void setCd_barras(String cd_barras) {
        this.cd_barras = cd_barras;
    }

    /**
     * @return the nm_produto
     */
    public String getNm_produto() {
        return nm_produto;
    }

    /**
     * @param nm_produto the nm_produto to set
     */
    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }

    /**
     * @return the ds_quantidade
     */
    public String getDs_quantidade() {
        return ds_quantidade;
    }

    /**
     * @param ds_quantidade the ds_quantidade to set
     */
    public void setDs_quantidade(String ds_quantidade) {
        this.ds_quantidade = ds_quantidade;
    }

    /**
     * @return the vl_compra
     */
    public float getVl_compra() {
        return vl_compra;
    }

    /**
     * @param vl_compra the vl_compra to set
     */
    public void setVl_compra(float vl_compra) {
        this.vl_compra = vl_compra;
    }

    /**
     * @return the vl_venda
     */
    public float getVl_venda() {
        return vl_venda;
    }

    /**
     * @param vl_venda the vl_venda to set
     */
    public void setVl_venda(float vl_venda) {
        this.vl_venda = vl_venda;
    }

    /**
     * @return the id_fornecedor
     */
    public int getId_fornecedor() {
        return id_fornecedor;
    }

    /**
     * @param id_fornecedor the id_fornecedor to set
     */
    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }
    
    private int id;
    private String cd_barras;
    private String nm_produto;
    private String ds_quantidade;
    private float vl_compra;
    private float vl_venda;
    private int id_fornecedor;
    private int id_usuario;
    private String status;
    private int id_tipo;
    private String ds_movimentacao;
    private int estoque_inicial;
    private int estoque_final;
    private String status_estoque;
    private int id_responsavel;
    private String observacao;
}

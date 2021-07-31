package model;

public class ModeloVenda {

    /**
     * @return the desconto
     */
    public float getDesconto() {
        return desconto;
    }

    /**
     * @param desconto the desconto to set
     */
    public void setDesconto(float desconto) {
        this.desconto = desconto;
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
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the nr_venda
     */
    public int getNr_venda() {
        return nr_venda;
    }

    /**
     * @param nr_venda the nr_venda to set
     */
    public void setNr_venda(int nr_venda) {
        this.nr_venda = nr_venda;
    }

    /**
     * @return the id_produto
     */
    public int getId_produto() {
        return id_produto;
    }

    /**
     * @param id_produto the id_produto to set
     */
    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    /**
     * @return the ds_quantidade
     */
    public int getDs_quantidade() {
        return ds_quantidade;
    }

    /**
     * @param ds_quantidade the ds_quantidade to set
     */
    public void setDs_quantidade(int ds_quantidade) {
        this.ds_quantidade = ds_quantidade;
    }

    /**
     * @return the id_usuario_venda
     */
    public int getId_usuario_venda() {
        return id_usuario_venda;
    }

    /**
     * @param id_usuario_venda the id_usuario_venda to set
     */
    public void setId_usuario_venda(int id_usuario_venda) {
        this.id_usuario_venda = id_usuario_venda;
    }

    /**
     * @return the id_usuario_aprovador
     */
    public int getId_usuario_aprovador() {
        return id_usuario_aprovador;
    }

    /**
     * @param id_usuario_aprovador the id_usuario_aprovador to set
     */
    public void setId_usuario_aprovador(int id_usuario_aprovador) {
        this.id_usuario_aprovador = id_usuario_aprovador;
    }

    /**
     * @return the status_venda
     */
    public String getStatus_venda() {
        return status_venda;
    }

    /**
     * @param status_venda the status_venda to set
     */
    public void setStatus_venda(String status_venda) {
        this.status_venda = status_venda;
    }
    
    private int nr_venda;
    private int id_produto;
    private int ds_quantidade;
    private int id_usuario_venda;
    private int id_usuario_aprovador;
    private String status_venda;
    private float total;
    private int id;
    private float desconto;
            
}

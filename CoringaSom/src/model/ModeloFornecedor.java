
package model;

public class ModeloFornecedor {

    /**
     * @return the nr_cnpj
     */
    public String getNr_cnpj() {
        return nr_cnpj;
    }

    /**
     * @param nr_cnpj the nr_cnpj to set
     */
    public void setNr_cnpj(String nr_cnpj) {
        this.nr_cnpj = nr_cnpj;
    }

    /**
     * @return the nr_telefone
     */
    public String getNr_telefone() {
        return nr_telefone;
    }

    /**
     * @param nr_telefone the nr_telefone to set
     */
    public void setNr_telefone(String nr_telefone) {
        this.nr_telefone = nr_telefone;
    }

    /**
     * @return the id_email
     */
    public String getId_email() {
        return id_email;
    }

    /**
     * @param id_email the id_email to set
     */
    public void setId_email(String id_email) {
        this.id_email = id_email;
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
     * @return the nm_fornecedor
     */
    public String getNm_fornecedor() {
        return nm_fornecedor;
    }

    /**
     * @param nm_fornecedor the nm_fornecedor to set
     */
    public void setNm_fornecedor(String nm_fornecedor) {
        this.nm_fornecedor = nm_fornecedor;
    }

   
    

    /**
     * @return the id_usuario
     */
    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the ds_status
     */
    public String getDs_status() {
        return ds_status;
    }

    /**
     * @param ds_status the ds_status to set
     */
    public void setDs_status(String ds_status) {
        this.ds_status = ds_status;
    }
    
    private int id;
    private String nm_fornecedor;
    private String nr_cnpj;
    private String nr_telefone;
    private String id_usuario;
    private String ds_status;
    private String id_email;
}

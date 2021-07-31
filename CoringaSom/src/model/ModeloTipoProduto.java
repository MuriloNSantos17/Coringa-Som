
package model;


public class ModeloTipoProduto {

    
    /**
     * @return the nm_tipo
     */
    public String getNm_tipo() {
        return nm_tipo;
    }

    /**
     * @param nm_tipo the nm_tipo to set
     */
    public void setNm_tipo(String nm_tipo) {
        this.nm_tipo = nm_tipo;
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
    
    private String nm_tipo;
    private String ds_status;
    private int id;

    
}

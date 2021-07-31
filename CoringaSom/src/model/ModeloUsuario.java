package model;

public class ModeloUsuario {

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
     * @return the in_alterar_senha
     */
    public String getIn_alterar_senha() {
        return in_alterar_senha;
    }

    /**
     * @param in_alterar_senha the in_alterar_senha to set
     */
    public void setIn_alterar_senha(String in_alterar_senha) {
        this.in_alterar_senha = in_alterar_senha;
    }

    /**
     * @return the login
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(boolean login) {
        this.login = login;
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
     * @return the nm_usuario
     */
    public String getNm_usuario() {
        return nm_usuario;
    }

    /**
     * @param nm_usuario the nm_usuario to set
     */
    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
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
     * @return the ds_matricula
     */
    public String getDs_matricula() {
        return ds_matricula;
    }

    /**
     * @param ds_matricula the ds_matricula to set
     */
    public void setDs_matricula(String ds_matricula) {
        this.ds_matricula = ds_matricula;
    }

    /**
     * @return the ds_senha
     */
    public String getDs_senha() {
        return ds_senha;
    }

    /**
     * @param ds_senha the ds_senha to set
     */
    public void setDs_senha(String ds_senha) {
        this.ds_senha = ds_senha;
    }
    private int id;
    private String nm_usuario;
    private String id_usuario;
    private String ds_matricula;
    private String ds_senha;
    private String in_alterar_senha;
    private boolean login;
    private String ds_status;
}

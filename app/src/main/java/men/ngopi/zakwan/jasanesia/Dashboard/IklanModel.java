package men.ngopi.zakwan.jasanesia.Dashboard;

public class IklanModel {
    private String id , foto_url;

    public IklanModel(String id, String foto_url) {
        this.id = id;
        this.foto_url = foto_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }
}

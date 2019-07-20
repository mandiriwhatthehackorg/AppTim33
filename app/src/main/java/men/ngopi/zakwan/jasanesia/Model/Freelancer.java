package men.ngopi.zakwan.jasanesia.Model;

public class Freelancer {
    private String  id,  name, deskripsi , pendidikan, keahlian , photo, no_akun , no_cif ;

    public Freelancer(){

    }

    public Freelancer(String id, String name, String deskripsi, String pendidikan, String keahlian, String photo, String no_akun, String no_cif) {
        this.id = id;
        this.name = name;
        this.deskripsi = deskripsi;
        this.pendidikan = pendidikan;
        this.keahlian = keahlian;
        this.photo = photo;
        this.no_akun = no_akun;
        this.no_cif = no_cif;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNo_akun() {
        return no_akun;
    }

    public void setNo_akun(String no_akun) {
        this.no_akun = no_akun;
    }

    public String getNo_cif() {
        return no_cif;
    }

    public void setNo_cif(String no_cif) {
        this.no_cif = no_cif;
    }
}

package men.ngopi.zakwan.jasanesia.Model;

public class MessageModel {
//    tipe_transaksi 1 chat
//    tipe_transaksi 0 transak
//    tipe_user 1 sendiri
//    tipe_user 0 freelancer
    String id , nama_pengirim , text , waktu , tipe_transaksi , tipe_user , transaksi_penerima , status_pembayaran;

    public MessageModel() {

    }


    public MessageModel(String id, String nama_pengirim, String text, String waktu, String tipe_transaksi, String tipe_user, String transaksi_penerima, String status_pembayaran) {
        this.id = id;
        this.nama_pengirim = nama_pengirim;
        this.text = text;
        this.waktu = waktu;
        this.tipe_transaksi = tipe_transaksi;
        this.tipe_user = tipe_user;
        this.transaksi_penerima = transaksi_penerima;
        this.status_pembayaran = status_pembayaran;
    }

    public String getTransaksi_penerima() {
        return transaksi_penerima;
    }

    public void setTransaksi_penerima(String transaksi_penerima) {
        this.transaksi_penerima = transaksi_penerima;
    }

    public String getStatus_pembayaran() {
        return status_pembayaran;
    }

    public void setStatus_pembayaran(String status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_pengirim() {
        return nama_pengirim;
    }

    public void setNama_pengirim(String nama_pengirim) {
        this.nama_pengirim = nama_pengirim;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTipe_transaksi() {
        return tipe_transaksi;
    }

    public void setTipe_transaksi(String tipe_transaksi) {
        this.tipe_transaksi = tipe_transaksi;
    }

    public String getTipe_user() {
        return tipe_user;
    }

    public void setTipe_user(String tipe_user) {
        this.tipe_user = tipe_user;
    }
}

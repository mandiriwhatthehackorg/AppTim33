package men.ngopi.zakwan.jasanesia.Utils;

import com.pixplicity.easyprefs.library.Prefs;

public class PublicString {

    public static final String ROLE_LAKON = "lakon";
    public static final String ROLE_MEMBER = "member";

    public static final String IS_INTRO_OPENED = "IS_INTRO_OPENED";

    public static final String TRANSACTION_LAKON_ID = "TRANSACTION_LAKON_ID";
    public static final String TRANSACTION_LAKON_EMAIL = "TRANSACTION_LAKON_EMAIL";
    public static final String TRANSACTION_LAKON_NAMA = "TRANSACTION_LAKON_NAMA";
    public static final String TRANSACTION_LAKON_FOTO_URL = "TRANSACTION_LAKON_FOTO_URL";
    public static final String TRANSACTION_LAKON_DESKRIPSI = "TRANSACTION_LAKON_DESKRIPSI";
    public static final String TRANSACTION_LAKON_STATUS_VERIFIKASI = "TRANSACTION_LAKON_STATUS_VERIFIKASI";


    public static final String CUREENT_USER_NAMA = "CUREENT_USER_NAMA";
    public static final String CUREENT_USER_ID = "CUREENT_USER_ID";
    public static final String CUREENT_USER_EMAIL = "CUREENT_USER_EMAIL";
    public static final String CUREENT_USER_ROLE = "CUREENT_USER_ROLE";
    public static final String CUREENT_USER_FOTO_URL = "CUREENT_USER_FOTO_URL";
    public static final String CUREENT_USER_DESKRIPSI = "CUREENT_USER_DESKRIPSI";
    public static final String CUREENT_USER_ID_KATEGORI = "CUREENT_USER_ID_KATEGORI";
    public static final String CUREENT_USER_NAMA_KATEGORI = "CUREENT_USER_NAMA_KATEGORI";

    public static final String CUREENT_USER_PREF = "CUREENT_USER_PREF";

    public static String PHONE_FOR_CONFIRM = "PHONE_FOR_CONFIRM";

    public static void setPhoneNumber(String phone){
        Prefs.putString(PHONE_FOR_CONFIRM , phone);
    }

    public static String getPhoneNumber(){
        return Prefs.getString(PHONE_FOR_CONFIRM , null);
    }
}
//
//class Role {
//    public static final String LAKON = "Lakon";
//    public static final String MEMBER = "Member";
//}

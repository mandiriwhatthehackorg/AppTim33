package men.ngopi.zakwan.jasanesia.Intro;

public class IntroModel {

    String Title,Description , secondary;
    int ScreenImg;

    public IntroModel(String title, String description,String secondary, int screenImg) {
        Title = title;
        Description = description;
        this.secondary = secondary;
        ScreenImg = screenImg;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}

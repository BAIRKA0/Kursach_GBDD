public class Fine {
    private String num;
    private String data;
    private String opisanie;
    private String razmer;
    public Fine(String num,String data,String opisanie,String razmer){
        this.num = num;
        this.data = data;
        this.opisanie = opisanie;
        this.razmer = razmer;
    }


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOpisanie() {
        return opisanie;
    }

    public void setOpisanie(String opisanie) {
        this.opisanie = opisanie;
    }

    public String getRazmer() {
        return razmer;
    }

    public void setRazmer(String razmer) {
        this.razmer = razmer;
    }

}


public class Car {

    private String model;
    private String plate;
    private String color;
    private String insurance;

    public Car(String model, String plate, String color, String insurance){
        this.model = model;
        this.plate = plate;
        this.color = color;
        this.insurance = insurance;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public String getColor() {
        return color;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}

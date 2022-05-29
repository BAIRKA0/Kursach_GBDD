/*import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    private static Connection connection = null;

    private static final String url = "jdbc:sqlserver://localhost:1433;";
    private static final String dbName = "databaseName=PoliceDB;";
    private static final String secureConnection = "encrypt=true;trustServerCertificate=true;";
    private static final String user = "user=sa;";
    private static final String userPassword = "password=123";

    public static void makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = url + dbName + secureConnection + user + userPassword;
            connection = DriverManager.getConnection(connectionUrl);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    // Извлечение списка машин по номеру водительских прав
    public static ArrayList<Car> selectCarsByLicense(String license) {
        ArrayList<Car> carsList = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Cars WHERE С_LICENSE = ?");

            statement.setString(1, license);

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                String plate = rs.getString("C_PLATE");
                String color = rs.getString("C_COlOR");
                String insurance = rs.getString("C_INSURANCE");
                String model = rs.getString("C_MODEL");
                int fines = rs.getInt("C_FINES");
                String license1 = rs.getString("С_LICENSE");

                Car car = new Car(
                        model,
                        plate,
                        color,
                        insurance,
                        fines,
                        license);

                carsList.add(car);

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return carsList;
    }
    // Извлечение списка водителей
    public static ArrayList<Driver> selectDrivers(){
        ArrayList<Driver> driverList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                        "SELECT TOP (1000) [D_LICENSE]\n" +
                                "      ,[D_FIRSTNAME]\n" +
                                "      ,[D_LASTNAME]\n" +
                                "  FROM [Drivers]");

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                String license = rs.getString("D_LICENSE");
                String first_name = rs.getString("D_FIRSTNAME");
                String last_name = rs.getString("D_LASTNAME");

                Driver driver = new Driver(
                        license,
                        first_name,
                        last_name);
                driverList.add(driver);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return driverList;
    }
    public static void addDriver(Driver driver){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Drivers (D_LICENSE,D_FIRSTNAME,D_LASTNAME) VALUES\n" +
                        "    (\'"+driver.getLicense()+"\',\'"+driver.getFirst_name()+"\',\'"+driver.getLast_name()+"\')");

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void delDriver(String s){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE Drivers\n" +
                        "WHERE D_LICENSE = "+s);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void changeCar(Car car){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Cars\n"+
                        "   SET C_PLATE = \'"+car.getPlate()+"\',\n"+
                        "   C_COLOR = \'"+car.getColor()+"\',\n" +
                        "   C_INSURANCE = \'"+car.getInsurance()+"\',\n"+
                        "   C_MODEL = \'"+car.getModel()+"\',\n"+
                        "C_FINES = \'"+car.getFinesCount()+"\'\n"+
                        "WHERE С_LICENSE = \'"+car.getLicence()+"\'");
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}*/

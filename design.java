/*

* Design patterns are a collection of common solutions to solve common software development design problems
* There are currently twenty-three patterns and they are grouped into three categories: creational, behavioral and structural. 
* 
* Creational Patterns:-
a) Builder
b) Factory Method
c) Abstract Factory
d) Singleton
e) Prototype

Builder Pattern : The builder design pattern’s main objective is to separate the construction of a complex object from its representation.

*/

//CODE DEMO:

public class Car {

    //required data
    private final int year;
    private final String make;
    private final String model;

    //optional data
    private final String trim;
    private final String color;
    private final int mileage;
    private final String vin;

    //getters
    public int getYear(){return year;}
    public String getMake(){ return make;}
    public String getModel(){ return model;}
    public String getTrim(){ return trim;}
    public String getColor(){ return color;}
    public int getMileage(){ return mileage;}
    public String getVin(){ return vin;}

    public Car(CarBuilder builder){
        this.year = builder.year;
        this.make = builder.make;
        this.model = builder.model;
        this.trim = builder.trim;
        this.color = builder.color;
        this.mileage = builder.mileage;
        this.vin = builder.vin;
    }

    @Override
    public String toString() {
        String requiredData = String.format("Year: %s\nMake: %s\nModel: %s\n",this.year, this.make, this.model);
        StringBuilder builder = new StringBuilder("");
        builder.append(requiredData);

        String trimValue = this.trim == null || this.trim.length() == 0 ? "UNKNOWN" : this.trim;
        String colorValue = this.color == null || this.color.length() == 0 ? "UNKNOWN" : this.color;
        String vinValue = this.vin == null || this.vin.length() == 0 ? "UNKNOWN" : this.vin;

        builder.append(String.format("Trim: %s\n", trimValue));
        builder.append(String.format("Color: %s\n", colorValue));
        builder.append(String.format("VIN: %s\n", vinValue));
        builder.append(String.format("Mileage: %s\n", mileage));

        return builder.toString();
    }

    //builder
    public static class CarBuilder {
        private final int year;
        private final String make;
        private final String model;

        private String trim;
        private String color;
        private int mileage;
        private String vin;

        public CarBuilder(int year, String make, String model) {
            this.year = year;
            this.make = make;
            this.model = model;
        }

        public CarBuilder withTrim(String trim){
            this.trim = trim;
            return this;
        }

        public CarBuilder withColor(String color){
            this.color = color;
            return this;
        }

        public CarBuilder withMileage(int mileage){
            this.mileage = mileage;
            return this;
        }

        public CarBuilder withVIN(String vin){
            this.vin = vin;
            return this;
        }

        public Car build(){
            Car car = new Car(this);

            if(isValid(car)){
                return car;
            }

            throw new IllegalArgumentException("Invalid data provided");
        }

        private boolean isValid(Car car){
            //perform validation base on requirements
            return true;
        }

    }
}

/*
 *You could then use your CarBuilder to create instances like this.
 */
public static void main(String[] args) {
        
        Car myCar = new Car.CarBuilder(2020,"BMW","X5")
                .withColor("gray")
                .build();


        Car myCar1 = new Car.CarBuilder(2012,"Audi","Q5")
                .withColor("gray")
                .withMileage(78000)
                .withVIN("vin")
                .build();

        Car myCar2 = new Car.CarBuilder(2017,"Acura","TLX")
                .withColor("gray")
                .withMileage(27000)
                .withVIN("vin")
                .withTrim("trim")
                .build();

        System.out.println(myCar);
        System.out.println(myCar1);
        System.out.println(myCar2);

    }

public class Circle extends Shape2D {
    private double radius;
    private String name = "circle";
    private double area;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public double getArea(){
        area = Math.PI * Math.pow(radius, 2.0);
        return area;
    }
}

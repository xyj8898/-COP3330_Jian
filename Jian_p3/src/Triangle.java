public class Triangle extends Shape2D {
    private double base, height;
    private String name = "triangle";
    private double area;

    public Triangle(double base, double height){
        this.base = base;
        this.height = height;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public double getArea(){
        area = 0.5 * base * height;
        return area;
    }
}

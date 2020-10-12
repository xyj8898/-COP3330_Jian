public class Square extends Shape2D {
    private double length;
    private String name = "square";
    private double area;

    public Square(double length){
        this.length = length;
    }

    @Override
    public String getName(){
        return name;
    }
    @Override
    public double getArea(){
        area = length * length;
        return area;
    }
}

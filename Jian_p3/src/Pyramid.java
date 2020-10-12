public class Pyramid extends Shape3D{
    private double length, width, height;
    private String name = "pyramid";
    private double surfaceArea, volume;

    public Pyramid(double length, double width, double height){
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public double getArea(){
        double radicand1, radicand2;
        // A = lw + l*sqrt((w/2)^2 + h^2) + w
        radicand1 = Math.pow((width/2.0), 2.0) + Math.pow(height, 2.0);
        radicand2 = Math.pow((length/2.0), 2.0) + Math.pow(height, 2.0);
        surfaceArea = (length * width) + (length * Math.sqrt(radicand1)) + (width * Math.sqrt(radicand2));
        return surfaceArea;
    }

    @Override
    public double getVolume(){
        volume = (1.0/3.0) * length * width * height;
        return volume;
    }
}

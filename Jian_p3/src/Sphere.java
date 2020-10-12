public class Sphere extends Shape3D {
    private double radius;
    private String name = "sphere";
    private double surfaceArea, volume;

    public Sphere(double radius){
        this.radius = radius;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public double getArea(){
        surfaceArea = 4.0 * Math.PI * Math.pow(radius, 2.0);
        return surfaceArea;
    }

    @Override
    public double getVolume(){
        volume = 4.0 * Math.PI * (1.0/3.0) * Math.pow(radius, 3.0);
        return volume;
    }
}

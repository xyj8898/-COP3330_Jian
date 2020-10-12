public class Cube extends Shape3D{
    private double edge;
    private String name = "cube";
    private double surfaceArea, volume;

    public Cube(double edge){
        this.edge = edge;
    }

    @Override
    public String getName(){
        return name;
    }
    @Override
    public double getArea(){
        surfaceArea = 6.0 * Math.pow(edge, 2.0);
        return surfaceArea;
    }

    @Override
    public double getVolume() {
        volume = Math.pow(edge, 3.0);
        return volume;
    }
}

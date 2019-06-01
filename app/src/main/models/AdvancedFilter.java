public class AdvancedFilter extends Filter {
    private float radius;
    private  int hoursDistance;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float newRadius) {
        this.radius = newRadius;
    }

    public int getHoursDistance() {
        return hoursDistance;
    }

    public void setHoursDistance(int newHoursDistance) {
        this.hoursDistance = newHoursDistance;
    }
}

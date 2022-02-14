package math;

public class interp {

    public static float lerp(float a, float b, float t)
    {
        return a + (b - a) * t;
    }

    public static float unlerp(float x, float x0, float x1)
    {
        return (x - x0) / (x1 - x0);
    }

    public static float map(float x, float x0, float x1, float a, float b)
    {
        float t = unlerp(x, x0, x1);
        return lerp(a, b, t);
    }
}

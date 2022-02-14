package math;

public class arr {

    public static float min(float[] a, int n)
    {
        float x = a[0];
        for (int i = 0; i < n; i++)
        {
            if (a[i] < x) x = a[i];
        }
        return x;
    }

    public static float max(float[] a, int n)
    {
        float x = a[0];
        for (int i = 0; i < n; i++)
        {
            if (a[i] > x) x = a[i];
        }
        return x;
    }
}

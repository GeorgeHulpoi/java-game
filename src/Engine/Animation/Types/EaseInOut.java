package Engine.Animation.Types;

import Engine.Animation.Type;

import java.lang.Math;

public class EaseInOut extends Type
{
    public double formula(double t)
    {
        return t<.5 ? 4*t*t*t : (t-1)*(2*t-2)*(2*t-2)+1;
    }
}

package Engine.Animation;

public abstract class Animation
{
    protected int delay;
    protected Type type;

    public Animation(int delay, Type type)
    {
        this.delay = delay;
        this.type = type;
    }

    abstract void start();
}

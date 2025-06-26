public final class Singleton {
    private static volatile Singleton instance;

    public String value;

    private Singleton(String value) {
        this.value = value;
    }

    public static Singleton getInstace(String value) {
        Singleton result = instance;
        if (null != result) {
            return result;
        }

        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton(value);
            }
        }
        return instance;
    }

}
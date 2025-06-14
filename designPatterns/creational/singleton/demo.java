public class demo {
    public static void main(String[] args) {

        for (int i = 0; i < 15; i++) {
            Thread thredFoo = new Thread(new ThreadFoo());
            Thread thredBar = new Thread(new ThreadBar());
            thredBar.start();
            thredFoo.start();
            System.out.println(" \n");
        }

    }

    static class ThreadFoo implements Runnable {

        @Override
        public void run() {
            Singleton singleton = Singleton.getInstace("FOO");
            System.out.println(singleton.value);
        }

    }

    static class ThreadBar implements Runnable {

        @Override
        public void run() {
            Singleton singleton = Singleton.getInstace("BARR");
            System.out.println(singleton.value);
        }

    }
}
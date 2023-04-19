package observer;

import data.Result;

public class PrintObserver implements Observer {
    @Override
    public void update(Result result) {
        System.out.println("-----------------------");
        System.out.println(result);
        System.out.println("-----------------------");
    }
}

package fr.formation.observer;

public class LogSubscriber implements ISubscriber {
    @Override
    public void update(IPublisher publisher, Context context) {
        System.out.println("JOURNALISATION ...");
        System.out.println(publisher);
        System.out.println(context);
    }
}

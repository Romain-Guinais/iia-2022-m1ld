package fr.formation.observer;

public interface ISubscriber {
    public void update(IPublisher publisher, Context context);
}

package fr.formation.service;

import java.util.ArrayList;
import java.util.List;

import fr.formation.observer.Context;
import fr.formation.observer.IPublisher;
import fr.formation.observer.ISubscriber;

public class ProduitService implements IProduitService, IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();

    @Override
    public void findAll() {
        System.out.println("FINDALL V1");

        this.notify(new Context("findAll", null));
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }
    
    @Override
    public void unsubscribe(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notify(Context context) {
        this.subscribers.forEach(s -> s.update(this, context));
    }
}

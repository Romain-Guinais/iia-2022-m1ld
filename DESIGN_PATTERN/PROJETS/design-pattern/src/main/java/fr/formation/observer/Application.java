package fr.formation.observer;

import fr.formation.factory.ServiceFactory;
import fr.formation.service.IProduitService;

public class Application {
    public static void main(String[] args) {
        IProduitService srvProduit = ServiceFactory.createProduitService(1);

        ((IPublisher)srvProduit).subscribe(new MailSubscriber()); 
        ((IPublisher)srvProduit).subscribe(new LogSubscriber());
        
        srvProduit.findAll();
    }
}

package fr.formation.proxy1;

import fr.formation.decorator.LogDecorator;
import fr.formation.decorator.MailDecorator;
import fr.formation.factory.ServiceFactory;
import fr.formation.service.IProduitService;

public class Application {
    public static final boolean USER_CONNECTED = true;

    public static void main(String[] args) {
        IProduitService srvProduit = new ProduitServiceProxy();

        srvProduit.findAll();


        IProduitService stackedServiceProduit = ServiceFactory.createProduitService(2);

        stackedServiceProduit = new LogDecorator(stackedServiceProduit);
        stackedServiceProduit = new MailDecorator(stackedServiceProduit);

        stackedServiceProduit = new ProduitServiceProxy(stackedServiceProduit);

        stackedServiceProduit.findAll();
    }
}

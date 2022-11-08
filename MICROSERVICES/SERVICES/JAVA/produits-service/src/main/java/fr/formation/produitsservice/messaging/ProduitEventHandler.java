package fr.formation.produitsservice.messaging;

import java.util.function.Consumer;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.formation.produitsservice.service.ProduitService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ProduitEventHandler {
    private final ProduitService srvProduit;
    private final StreamBridge streamBridge;

    @Bean // Suppression possible, donc on supprime et on prévient ceux que ça intéressent
    public Consumer<ProduitDeletionOkEvent> produitDeletionOk() {
        return evt -> {
            this.srvProduit.deleteById(evt.getProduitId());
            this.streamBridge.send("produit-deleted-out-0", ProduitDeletedCommand.builder().produitId(evt.getProduitId()).build());
        };
    }
}

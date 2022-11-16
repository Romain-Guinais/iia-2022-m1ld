package fr.formation.produitsservice.api;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.produitsservice.api.request.ProduitRequest;
import fr.formation.produitsservice.messaging.ProduitDeletionCommand;
import fr.formation.produitsservice.service.ProduitService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitService srvProduit;
    private final StreamBridge streamBridge;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int add(@RequestBody ProduitRequest produitRequest) {
        return this.srvProduit.add(produitRequest);
    }

    @PutMapping("/{id}")
    public int edit(@PathVariable int id, @RequestBody ProduitRequest produitRequest) {
        return this.srvProduit.edit(id, produitRequest);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id) {
        this.streamBridge.send("produit-deletion-out-0", ProduitDeletionCommand.builder().produitId(id).build());
        
        return true;
    }
}

package Venda.VendaAPI.controllers;

import Venda.VendaAPI.models.Venda;
import Venda.VendaAPI.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> listarVendas() {
        return vendaService.listarVendas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaPorId(@PathVariable Long id) {
        Venda venda = vendaService.buscarVendaPorId(id);
        return venda != null ? ResponseEntity.ok(venda) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
        try {
            Venda novaVenda = vendaService.criarVenda(venda);
            return ResponseEntity.ok(novaVenda);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarVenda(@PathVariable Long id) {
        try {
            vendaService.deletarVenda(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package br.com.praiaregiaodoslagos.controllers;

import br.com.praiaregiaodoslagos.entities.Praia;
import br.com.praiaregiaodoslagos.requesties.PraiaRequest;
import br.com.praiaregiaodoslagos.responses.PraiaResponse;
import br.com.praiaregiaodoslagos.services.PraiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/praias")
public class PraiasController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private PraiaService service;

    @PostMapping
    public ResponseEntity<PraiaRequest> insert(@Valid @RequestBody PraiaRequest request) {
        request = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(request);
    }


    @GetMapping
    public ResponseEntity<Page<PraiaResponse>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy );
        Page<PraiaResponse> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PraiaResponse> findById(@PathVariable Long id) {
        PraiaResponse response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "nome/{nome}")
    @Transactional(readOnly = true)
    public List<Praia> buscarPorNome(@PathVariable("nome") String nome) {
        String jpql = "SELECT c FROM Praia c WHERE c.nome = :nome";
        return manager.createQuery(jpql, Praia.class).setParameter("nome", nome).getResultList();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PraiaRequest> update(@PathVariable Long id, @Valid @RequestBody PraiaRequest request){
        request = service.update(id, request);
        return ResponseEntity.ok().body(request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PraiaRequest> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.praiaregiaodoslagos.controllers;

import br.com.praiaregiaodoslagos.entities.Cidade;
import br.com.praiaregiaodoslagos.requesties.CidadeRequest;
import br.com.praiaregiaodoslagos.responses.CidadeResponse;
import br.com.praiaregiaodoslagos.services.CidadeService;
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
@RequestMapping(value = "/cidades")
public class CidadeController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private CidadeService service;

    @PostMapping
    public ResponseEntity<CidadeRequest> insert(@Valid @RequestBody CidadeRequest request) {
        request = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<Page<CidadeResponse>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy );
        Page<CidadeResponse> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CidadeResponse> findById(@PathVariable Long id) {
        CidadeResponse response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "nome/{nome}")
    @Transactional(readOnly = true)
    public List<Cidade> buscarPorNome(@PathVariable("nome") String nome) {
        String jpql = "SELECT c FROM Cidade c WHERE c.nome = :nome";
        return manager.createQuery(jpql, Cidade.class).setParameter("nome", nome).getResultList();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CidadeRequest> update(@PathVariable Long id, @Valid @RequestBody CidadeRequest request){
        request = service.update(id, request);
        return ResponseEntity.ok().body(request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CidadeRequest> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.praiaregiaodoslagos.services;

import br.com.praiaregiaodoslagos.entities.Cidade;
import br.com.praiaregiaodoslagos.repositories.CidadeRepository;
import br.com.praiaregiaodoslagos.requesties.CidadeRequest;
import br.com.praiaregiaodoslagos.responses.CidadeResponse;
import br.com.praiaregiaodoslagos.services.exceptions.DataBaseException;
import br.com.praiaregiaodoslagos.services.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;


    @Transactional(readOnly = true)
    public Page<CidadeResponse> findAllPaged(PageRequest pageRequest) {
        Page<Cidade> list = repository.findAll(pageRequest);
        return list.map(x -> new CidadeResponse(x));
    }

    @Transactional(readOnly = true)
    public CidadeResponse findById(Long id) {
        Optional<Cidade> obj = repository.findById(id);
        Cidade entity = obj.orElseThrow(() -> new ResourcesNotFoundException("Cidade não Encontrada"));
        return new CidadeResponse(entity, entity.getPraias());
    }

    @Transactional
    public CidadeRequest insert(CidadeRequest resquest) {
        Cidade entity = new Cidade();
        montarObjeto(resquest, entity);
        entity = repository.save(entity);
        System.out.println(entity);
        return new CidadeRequest(entity);
    }
    @Transactional
    public CidadeRequest update(Long id, CidadeRequest request) {
        try {
            Cidade entity = repository.getOne(id);
            montarObjeto(request, entity);
            entity = repository.save(entity);
            return new CidadeRequest(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourcesNotFoundException("Id Não encontrado" + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
                throw new ResourcesNotFoundException("ID not found " + id);
            }
        catch (DataIntegrityViolationException e) {
                throw new DataBaseException("Integrity Violation");
            }
    }

    private void montarObjeto(CidadeRequest resquest, Cidade entity) {
        entity.setNome (resquest.getNome());
        entity.setArea(resquest.getArea());
        entity.setCodigoIbge(resquest.getCodigoIbge());
        entity.setPopulacao(resquest.getPopulacao());
        entity.setDensidadeDemografica(resquest.getDensidadeDemografica());
        entity.setLinkImage(resquest.getLinkImage());
    }


}


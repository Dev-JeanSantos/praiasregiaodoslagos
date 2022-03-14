package br.com.praiaregiaodoslagos.services;

import br.com.praiaregiaodoslagos.entities.Cidade;
import br.com.praiaregiaodoslagos.entities.Praia;
import br.com.praiaregiaodoslagos.repositories.CidadeRepository;
import br.com.praiaregiaodoslagos.repositories.PraiaRepository;
import br.com.praiaregiaodoslagos.requesties.PraiaRequest;
import br.com.praiaregiaodoslagos.responses.PraiaResponse;
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
public class PraiaService {

    @Autowired
    private PraiaRepository repository;
    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional(readOnly = true)
    public Page<PraiaResponse> findAllPaged(PageRequest pageRequest) {
        Page<Praia> list = repository.findAll(pageRequest);
        return list.map(x -> new PraiaResponse(x));
    }

    @Transactional(readOnly = true)
    public PraiaResponse findById(Long id) {
        Optional<Praia> obj = repository.findById(id);
        Praia entity = obj.orElseThrow(() -> new ResourcesNotFoundException("Praia não Encontrada"));
        return new PraiaResponse(entity);
    }

    @Transactional
    public PraiaRequest insert(PraiaRequest resquest) {
        Praia entity = new Praia();
        montarObjeto(resquest, entity);

        entity = repository.save(entity);
        System.out.println(entity);
        return new PraiaRequest(entity);
    }

    @Transactional
    public PraiaRequest update(Long id, PraiaRequest request) {
        try {
            Praia entity = repository.getOne(id);
            montarObjeto(request, entity);
            entity = repository.save(entity);
            return new PraiaRequest(entity);
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

    private void montarObjeto(PraiaRequest request, Praia entity) {
        entity.setNome (request.getNome());
        entity.setAguaEOnda(request.getAguaEOnda());
        entity.setCoordenadas(request.getCoordenadas());
        entity.setTipoDePraia(request.getTipoDePraia());
        entity.setDescricao(request.getDescricao());
        entity.setFaixaDeAreia(request.getFaixaDeAreia());
        Cidade cidade = cidadeRepository.getOne(request.getCidadeId());
        entity.setCidade(cidade);
    }

}


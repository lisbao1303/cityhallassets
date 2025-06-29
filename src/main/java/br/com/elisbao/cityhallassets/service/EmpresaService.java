package br.com.elisbao.cityhallassets.service;

import br.com.elisbao.cityhallassets.model.Empresa;
import br.com.elisbao.cityhallassets.repository.EmpresaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EmpresaService {

    @Inject
    EmpresaRepository empresaRepository;

    @Transactional
    public Empresa cadastrar(Empresa empresa) {
        empresa.dataCadastro = LocalDateTime.now();
        empresaRepository.persist(empresa);
        return empresa;
    }

    @Transactional
    public List<Empresa> listarTodas() {
        return empresaRepository.listAll();
    }

}

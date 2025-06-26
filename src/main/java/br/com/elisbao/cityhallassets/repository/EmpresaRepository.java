package br.com.elisbao.cityhallassets.repository;

import br.com.elisbao.cityhallassets.model.Empresa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmpresaRepository implements PanacheRepository<Empresa> {
    // Métodos adicionais se necessário
}

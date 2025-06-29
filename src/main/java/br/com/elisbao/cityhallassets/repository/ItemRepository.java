package br.com.elisbao.cityhallassets.repository;

import br.com.elisbao.cityhallassets.model.Item;

import io.quarkus.hibernate.orm.PersistenceUnit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {

    public Item findByCodigo(String codigo) {
        return find("numero_patrimonio", codigo).firstResult();
    }

    public List<Item> findAtivosByEmpresaCnpj(String empresa_id) {
        return list("empresa.id = ?1", empresa_id);
    }

    public List<Item> findEmValidacaoPorUsuarioEData(String usuario, LocalDate data) {
        return list("manutUser = ?1 and confirmacao = false and dataValidacao = ?2", usuario, data);
    }

}

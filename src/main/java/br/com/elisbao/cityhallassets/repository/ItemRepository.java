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
        return find("codigo", codigo).firstResult();
    }

    public List<Item> findAtivosByEmpresaCnpj(String cnpj) {
        return list("empresa = ?1 and confirmacao = true", cnpj);
    }

    public List<Item> findEmValidacaoPorUsuarioEData(String usuario, LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(23, 59, 59);
        return list("manutUser = ?1 and confirmacao = false and dataValidacao between ?2 and ?3",
                usuario, inicio, fim);
    }
}

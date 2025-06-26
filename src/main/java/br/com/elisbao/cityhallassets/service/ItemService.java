package br.com.elisbao.cityhallassets.service;

import br.com.elisbao.cityhallassets.model.Item;
import br.com.elisbao.cityhallassets.repository.ItemRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ItemService {

    @Inject
    ItemRepository itemRepository;

    // 1. Cadastrar novo item
    @Transactional
    public void salvar(Item item) {
        itemRepository.persist(item);
    }

    // 2. Validar item (atualiza data de validação)
    @Transactional
    public boolean validarItem(String codigo) {
        Item item = itemRepository.findByCodigo(codigo);
        if (item == null) {
            return false;
        }
        item.setDataValidacao(LocalDate.now());
        itemRepository.persist(item);
        return true;
    }

    // 3. Listar itens ativos por empresa (CNPJ)
    public List<Item> listarAtivosPorEmpresa(String cnpj) {
        return itemRepository.findAtivosByEmpresaCnpj(cnpj);
    }

    // 4. Listar itens em validação (confirmacao = false, por usuário e data)
    public List<Item> listarItensEmValidacao(String usuario, LocalDate data) {
        return itemRepository.findEmValidacaoPorUsuarioEData(usuario, data);
    }

    // 5. Confirmar item
    @Transactional
    public boolean confirmarItem(String codigo, boolean confirmacao, String observacao) {
        Item item = itemRepository.findByCodigo(codigo);
        if (item == null) {
            return false;
        }
        item.setConfirmacao(confirmacao);
        item.setObservacao(observacao);
        itemRepository.persist(item);
        return true;
    }
}

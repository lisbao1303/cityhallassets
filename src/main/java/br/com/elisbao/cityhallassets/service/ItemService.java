package br.com.elisbao.cityhallassets.service;

import br.com.elisbao.cityhallassets.dto.ItemCadastroDTO;
import br.com.elisbao.cityhallassets.model.Empresa;
import br.com.elisbao.cityhallassets.model.Item;
import br.com.elisbao.cityhallassets.repository.EmpresaRepository;
import br.com.elisbao.cityhallassets.repository.ItemRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ItemService {

    @Inject
    ItemRepository itemRepository;

    @Inject
    EmpresaRepository empresaRepository;

    // 1. Cadastrar novo item
    @Transactional
    public Item salvar(ItemCadastroDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.empresaId);
        if (empresa == null) {
            throw new RuntimeException();
        }

        Item item = new Item();
        item.setEmpresa(empresa);
        item.setNumeroPatrimonio(dto.numeroPatrimonio);
        item.setDescricao(dto.descricao);
        item.setMarca(dto.marca);
        item.setModelo(dto.modelo);
        item.setEstadoConservacao(dto.estadoConservacao);
        item.setLocalizacao(dto.localizacao);
        item.setResponsavel(dto.responsavel);
        item.setFotoUrl(dto.fotoUrl);
        item.setValor(dto.valor);
        item.setDataAquisicao(dto.dataAquisicao);
        item.setManutUser(dto.manutUser);

        itemRepository.persist(item);
        return item;
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
    public List<Item> listarAtivosPorEmpresa(String id) {
        return itemRepository.findAtivosByEmpresaCnpj(id);
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

package br.com.elisbao.cityhallassets.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "empresa")
public class Empresa extends PanacheEntity {

    @Column(length = 255, nullable = false)
    public String nome;

    @Column(length = 20, nullable = false, unique = true)
    public String cnpj;

    public String endereco;

    @Column(name = "data_cadastro")
    public LocalDateTime dataCadastro;
}

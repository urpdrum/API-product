package com.example.springboot.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ItemOrdered")
    public class ItemOrderedModel extends RepresentationModel<ItemOrderedModel> implements Serializable {

    private static final long serialVersionUID = 1L;


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private UUID idItemOrdered;

        @ManyToOne
        private OrderModel OrderModel;

        @ManyToOne
        private ProductModel ProductModel;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ItemOrderedModel> itens = new ArrayList<>();

        private LocalDateTime dataCriacao;

        private BigDecimal valorTotal;



//        @ElementCollection
//        @CollectionTable(name = "TB_ItemOrdered", joinColumns = @JoinColumn(name = "idItemOrdered"))
//        private List<com.example.springboot.models.Valor> valores;

        // Getters and setters omitted for brevity
    }

//    class Valor {
//        private BigDecimal valor;
//
//        // Getters and setters omitted for brevity



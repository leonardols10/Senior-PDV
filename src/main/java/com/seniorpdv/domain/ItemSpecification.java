package com.seniorpdv.domain;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {

    public static Specification<Item> hasNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); 
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }
}

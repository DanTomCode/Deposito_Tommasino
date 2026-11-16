package it.corso.products.service;

import it.corso.products.model.Product;
import it.corso.products.persistence.ProductEntity;
import it.corso.products.persistence.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(Product product) {
        ProductEntity entityToSave = ProductEntity.fromModel(product);
        ProductEntity savedEntity = productRepository.save(entityToSave);
        return savedEntity.toModel();
    }
}

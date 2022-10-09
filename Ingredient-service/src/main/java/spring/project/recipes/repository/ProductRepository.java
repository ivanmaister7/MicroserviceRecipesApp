package src.main.java.spring.project.recipes.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.main.java.spring.project.recipes.domain.Product;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductId(Long productId);

    @Query(value = " SELECT p " +
            "FROM Product p ")
    List<Product> findAllProducts();
    @Query(value = " SELECT p.productId " +
            "FROM Product p "+
            "WHERE p.productName IN :names" )
    List<Long> findProductByNames(@Param("names") Collection<String> names);
}

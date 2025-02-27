package com.friendzoo.api.domain.product.repository;

import com.friendzoo.api.domain.product.dto.ProductDTO;
import com.friendzoo.api.domain.product.entity.Category;
import com.friendzoo.api.domain.product.entity.Product;
import com.friendzoo.api.domain.product.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>
, ProductRepositoryCustom {
//    @EntityGraph(attributePaths = "imageList")
//    @Query("select p from Product p where p.id = :pno")
//    Optional<Product> selectOne(@Param("pno") Long pno);

    @Query("select p from Product p where p.name = :name and p.delFlag = false")
    Optional<Product> findByProduct(@Param("name") String name);

    //Best 상품 조회
    @Query("select p,lists from Product p join p.imageList lists where p.best= 'Y' and lists.ord = 0 and p.delFlag = false")
    List<Product> findBestProducts();

    //MdPick 상품 조회
    @Query("select p from Product p join p.imageList lists where p.mdPick= 'Y' and lists.ord = 0 and p.delFlag = false")
    List<Product> findMdPickProducts();

    //New 상품 순서 조회
    @Query("select p from Product p where p.delFlag = false")
    List<Product> findNewProducts(Sort createdAt);

    @Query("select p,lists from Product p join p.imageList lists where lists.ord = 0 and p.delFlag = false")
    List<Product> findAllProducts();

    //Search 시 이름으로 검색 조회
    @Query("select p from Product p where p.name LIKE CONCAT('%',:name,'%') and p.delFlag = false")
    List<Product> findSelectedCategory(@Param("name") String name);

    //상품 상세 조회
    @Query("select p,lists from Product p join p.imageList lists where p.id = :id and lists.ord = 0 and p.delFlag = false")
    List<Product> findSelectedItem(@Param("id") Long id);

    //상품 상세 관련 상품 조회
    @Query("select p from Product p where p.category.id = :id and p.delFlag = false")
    List<Product> findrelatedItem(@Param("id") Long id);

    //상품 삭제
    @Modifying
    @Query("update Product p set p.delFlag = true where p.id = :id")
    void modifyDeleteFlag(@Param("id") Long id);

    // product 내 카테고리 존재 여부 확인
    @Query("select case when count(p) > 0 then true else false end from Product p where p.category.id = :categoryId")
    boolean existsByCategoryId(@Param("categoryId") Long categoryId);
}

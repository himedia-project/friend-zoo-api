package com.friendzoo.api.domain.product.service;

import com.friendzoo.api.domain.member.dto.JoinRequestDTO;
import com.friendzoo.api.domain.member.dto.MemberTestDTO;
import com.friendzoo.api.domain.member.entity.Member;
import com.friendzoo.api.domain.product.dto.CategoryDTO;
import com.friendzoo.api.domain.product.dto.ProductDTO;
import com.friendzoo.api.domain.product.entity.Category;
import com.friendzoo.api.domain.product.entity.Product;
import com.friendzoo.api.domain.product.entity.ProductImage;
import com.friendzoo.api.domain.test.dto.TestResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ProductService {


    List<ProductDTO> getBestProducts(ProductDTO productDTO);

    List<ProductDTO> getMdPickProducts(ProductDTO productDTO);

    List<ProductDTO> getNewProduct(ProductDTO productDTO);

    List<ProductDTO> getSelectedCategory(String name);

    ProductDTO getSelectedItem(String email,Long id);

    List<ProductDTO> getSelectedCategoryItem(String email, Long id);

    Product getEntity(Long productId);

    ProductDTO getDTO(Product product);

    // 상품 리스트 조회
    List<ProductDTO> getAllList(String email, ProductDTO productDTO);


    default ProductDTO entityToDTO(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory() != null ? product.getCategory().getId() : null)
                .price(product.getPrice())
                .best(product.getBest())
                .mdPick(product.getMdPick())
                .uploadFileNames(product.getImageList().stream().map(ProductImage::getImageName).toList())
                .description(product.getDescription())
                .stockNumber(product.getStockNumber())
                .isHeart(product.getIsHeart())
                .categoryId(product.getCategory().getId())
                .createdAt(product.getCreatedAt())
                .modifiedAt(product.getModifiedAt())
                .discountPrice(product.getDiscountPrice())
                .build();
    }


}

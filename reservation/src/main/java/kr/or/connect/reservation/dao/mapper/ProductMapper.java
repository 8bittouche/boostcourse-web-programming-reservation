package kr.or.connect.reservation.dao.mapper;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectProductAll(int start);
    List<Product> selectProductByCategory(@Param("categoryId") int categoryId, @Param("start") int start);
    int selectCountAll();
    int selectCountCategory(int categoryId);
    List<ProductImage> selectProductImage(int productId);
    List<ProductPrice> selectProductPrice(int productId);
}

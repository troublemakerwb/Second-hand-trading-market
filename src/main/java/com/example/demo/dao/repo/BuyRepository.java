package com.example.demo.dao.repo;

import com.example.demo.dao.entity.BuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BuyRepository extends JpaRepository<BuyEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from c_buy where productid = :productid", nativeQuery = true)
    public void deleteBuyEntityById(@Param("productid") Long productId);

    @Query(value = "select * from c_buy t where t.productid = :id", nativeQuery = true)
    public BuyEntity findByIdLikeRawSQL(@Param("id") Long productId);

    @Query(value = "select * from c_buy t where t.userid = :id", nativeQuery = true)
    public List<BuyEntity> findByUserIdLikeRawSQL(@Param("id") Long userId);

    @Transactional
    @Modifying
    @Query(value = "update c_buy t set t.product_num = t.product_num + 1 where t.productid = :id ", nativeQuery = true)
    public void updateProductNum(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update c_buy t set t.product_num = :num where t.productid = :id ", nativeQuery = true)
    public void updateProductNumLikeRawSQL(@Param("id") Long id, @Param("num") Integer productNum);

    @Transactional
    @Modifying
    @Query(value = "update c_buy t set t.checked = :checked where t.productid = :id ", nativeQuery = true)
    public void updateProductCheckedLikeRawSQL(@Param("id") Long id, @Param("checked") Integer chekced);

    @Transactional
    void deleteAllByIdIn(List<Long> ids);

    List<BuyEntity> findAllByIdIn(List<Long> ids);

}

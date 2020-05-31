package com.example.demo.dao.repo;

import com.example.demo.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public List<UserEntity> findByUserNameLike(String keyword);
   // public List<UserEntity> findByAgeBetween(Integer startAge,Integer endAge);

    UserEntity findAllByUserName(String s);

    //JPQL
    @Query("SELECT entity from UserEntity entity where entity.userName like :keyword")
    public List<UserEntity> findByNameLike2(@Param("keyword") String keyword);

    @Query(value="select * from c_user t where t.username like :keyword",nativeQuery=true)
    public List<UserEntity> findByNameLikeRawSQL(@Param("keyword")String keyword);

    //userName代表UserEntity里的属性，第二个username为参数值
    @Modifying
    @Query("update UserEntity set userName = :username where id = :id ")
    public void updateUserName(@Param("username")String username, @Param("id")Long id);

    public UserEntity findByUserName(String keyword);

    @Transactional
    @Modifying
    @Query(value="delete from c_user where id = :id",nativeQuery=true)
    public void deleteAddress(@Param("id")Long userId);


}

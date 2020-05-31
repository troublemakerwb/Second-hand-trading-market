package com.example.demo.dao.repo;

import com.example.demo.dao.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    @Transactional
    @Modifying
    @Query(value="update c_address t set t.is_default = 1 where t.address_id = :id ",nativeQuery=true)
    public void updateIsDefault(@Param("id")Long addressId);

    @Transactional
    @Modifying
    @Query(value="delete from c_address where address_id = :id",nativeQuery=true)
    public void deleteAddress(@Param("id")Long addressId);

    @Transactional
    @Modifying
    @Query(value="insert into c_address(user_name,street_name,tel) values(username,streetname,tel)",nativeQuery=true)
    public void addAddress(@Param("username")String userName,@Param("streetname")String streetName,@Param("tel")String tel);
}

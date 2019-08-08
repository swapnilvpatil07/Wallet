/**
 * 
 */
package com.fidel.webwallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fidel.webwallet.model.UserInfo;

/**
 * @author Swapnil
 *
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM user_info u WHERE u.email_id = :email")
	Optional<UserInfo> getUserByEmail(@Param("email") String email);

	@Query(nativeQuery = true, value = "SELECT * FROM user_info u WHERE u.contact_no = :contactNo")
	List<UserInfo> getUserByMobileNo(@Param("contactNo") String contactNo);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE user_info SET cnt_verified=1 WHERE cust_id = :custId")
	void updateMobVerified(@Param("custId") Integer custId);
}

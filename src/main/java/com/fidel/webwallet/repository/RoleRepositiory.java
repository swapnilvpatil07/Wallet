/**
 * 
 */
package com.fidel.webwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fidel.webwallet.model.Role;

/**
 * @author Swapnil
 *
 */
public interface RoleRepositiory extends JpaRepository<Role, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM role r WHERE r.name = :role")
	Role findByName(@Param("role") String role);

}

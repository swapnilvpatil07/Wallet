/**
 * 
 */
package com.fidel.webwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fidel.webwallet.model.OrderDetails;

/**
 * @author Swapnil
 *
 */
public interface OrderInfoRepository extends JpaRepository<OrderDetails, Integer> {

}

/**
 * 
 */
package com.fidel.webwallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidel.webwallet.model.OrderDetails;
import com.fidel.webwallet.repository.OrderInfoRepository;
import com.fidel.webwallet.service.OrderDetailsService;

/**
 * @author Swapnil
 *
 */
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	/**
	 * 
	 */
	public OrderDetailsServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private OrderInfoRepository orderInfoRepository;

	@Override
	public Integer getNextOrdNo() {
		OrderDetails ord = orderInfoRepository.save(new OrderDetails());
		return ord.getOrderId();
	}

	@Override
	public OrderDetails getOrder(String orderId) {
		Optional<OrderDetails> record = orderInfoRepository.findById(Integer.parseInt(orderId));

		if (record.isPresent()) {
			return record.get();
		}
		return new OrderDetails();
	}

	@Override
	public boolean updateTxn(OrderDetails order) {
		try {
			orderInfoRepository.save(order);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}

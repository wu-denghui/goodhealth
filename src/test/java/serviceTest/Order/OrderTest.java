/**
 * 
 */
package serviceTest.Order;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.goodhealth.App;
import com.goodhealth.dao.OrderRepository;
import com.goodhealth.pojo.Orders;
import com.goodhealth.service.imp.OrderServiceImp;

/**
 * @author 24663
 * @date 2019年1月4日
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class OrderTest {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private  OrderServiceImp  orderService;
	
	@Test
	public void testSave() {
		Orders order=new Orders();
		order.setMemberId(4);
		order.setOrderCount(new  BigDecimal("100"));
		order.setOrderDetail("ojbk");
		order.setOrderDate("now");
		this.orderRepository.save(order);
	}
	
	@Test
	public void testSaveBy() throws Exception {
		Orders order=new Orders();
		order.setMemberId(4);
		order.setOrderCount(new  BigDecimal("100"));
		order.setOrderDetail("ojbka");
		order.setOrderDate("now");
		this.orderService.addOrder(order);
	}
	
	@Test
	public  void   testFind(){
		this.orderRepository.findByOrderId(1);
	}
	

}

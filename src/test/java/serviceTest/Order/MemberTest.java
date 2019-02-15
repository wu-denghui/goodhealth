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
import com.goodhealth.dao.MemberRepository;

/**
 * @author 24663
 * @date 2019年1月4日
 * @Description
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class MemberTest {
	
	@Autowired
	private  MemberRepository memberRepository;
	
	@Test
	public void testFind() {
		this.memberRepository.findOne(5);
	}

}

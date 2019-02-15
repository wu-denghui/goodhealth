/**
 * 
 */
package serviceTest.Drug;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.goodhealth.App;
import com.goodhealth.dao.DrugRepository;
import com.goodhealth.dao.MemberRepository;
import com.goodhealth.pojo.Drug;
import com.goodhealth.service.imp.DrugServiceImp;

/**
 * @author 24663
 * @date 2019年1月4日
 * @Description
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class DrugTest {
	@Autowired
	private  DrugRepository   drugRepository;
	
	@Autowired
	private DrugServiceImp  drugService;
	
	
	@Test
	public void testFind() {
		try {
			Page<Drug> page= this.drugService.findDrugListByLikeName("大大颗粒", 0);
			List<Drug> list=page.getContent();
			if ( list.isEmpty()) {
				System.out.println(1001);
			}else{
				System.out.println(101);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void  testFindByName(){
		try {
			this.drugService.findDrugByName("四季感冒片");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public  void testSave(){
		Drug drug=new Drug();
		drug.setDrugPrice(new BigDecimal("15"));
		drug.setDrugName("demo");
		drug.setDrugIntegral(15);
		this.drugRepository.save(drug);
	}
}

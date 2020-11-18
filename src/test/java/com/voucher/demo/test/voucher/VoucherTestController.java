package com.voucher.demo.test.voucher;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.voucher.demo.controller.voucher.VoucherRestController;
import com.voucher.demo.dto.offer.VoucherCodeOfferDto;
import com.voucher.demo.entity.offer.SpecialOfferBean;
import com.voucher.demo.entity.offer.VoucherCodeBean;
import com.voucher.demo.entity.recipient.RecipientBean;
import com.voucher.demo.service.recipient.RecipientService;
import com.voucher.demo.service.voucher.SpecialOfferService;
import com.voucher.demo.service.voucher.VoucherCodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = VoucherRestController.class)
public class VoucherTestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SpecialOfferService specialOfferService;
	
	@MockBean
	private VoucherCodeService voucherCodeService;
	
	@MockBean
	private RecipientService recipientService;
	
	VoucherCodeBean testVoucherCodeBean1 = new VoucherCodeBean("cdd8b0f8-553d-4d1f-bae9-1337af198ada", 1, 1, getDate("11-Oct-2021"), 0, null);
	VoucherCodeBean testVoucherCodeBean2 = new VoucherCodeBean("bdd8b0f8-554b-5d1f-bae9-2337af198ada", 1, 2, getDate("11-Sep-2020"), 0, null);
	VoucherCodeBean testVoucherCodeBean3 = new VoucherCodeBean("3344b0f8-554b-5d1f-bae9-2337af198ada", 2, 1, getDate("11-Oct-2021"), 1, getDate("11-Oct-2020"));
	SpecialOfferBean testSpecialOfferBean1 = new SpecialOfferBean(1, "KFC 2021 Offer", new BigDecimal("4.5"),"Y");
	SpecialOfferBean testSpecialOfferBean2 = new SpecialOfferBean(2, "GSC Oct Offer", new BigDecimal("10"),"N");
	RecipientBean testRecipientBean1 = new RecipientBean(1,"Tester 1", "tester1@gmail.com");
	RecipientBean testRecipientBean2 = new RecipientBean(2,"Tester 2", "tester2@gmail.com");
	RecipientBean testRecipientBean3 = new RecipientBean(3,"Tester 3", "tester3@gmail.com");
	VoucherCodeOfferDto testVoucherCodeOfferDto1 = new VoucherCodeOfferDto("cdd8b0f8-553d-4d1f-bae9-1337af198ada", "KFC 2021 Offer", new BigDecimal("4.5"), getDate("11-Oct-2021"), 0, null);
	List<VoucherCodeBean> voucherCodeTestList = new ArrayList<>();
	List<SpecialOfferBean> specialOfferTestList = new ArrayList<>();
	List<RecipientBean> recipientTestList = new ArrayList<>();
	List<VoucherCodeOfferDto> voucherCodeOfferDtoList = new ArrayList<>();
	
	@Test
	public void createVoucherCodeOnEachRecipientTest() {
		try {
			String expectedResult = "Voucher code created for 3 recipients.";
			recipientTestList.add(testRecipientBean1);
			recipientTestList.add(testRecipientBean2);
			recipientTestList.add(testRecipientBean3);

			Mockito.when(specialOfferService.getSpecialOfferById(Mockito.anyInt())).thenReturn(testSpecialOfferBean1);
			Mockito.when(specialOfferService.validateSpecialOffer(Mockito.any())).thenReturn("");
			Mockito.when(recipientService.findAllReceipentList()).thenReturn(recipientTestList);
			for(RecipientBean recipientTestBean: recipientTestList) {
				Mockito.when(voucherCodeService.createVoucherCode(Mockito.any())).thenReturn(testVoucherCodeBean2);
			}
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/voucher/rest-api/create-voucher-code-on-each-recipient").accept(MediaType.APPLICATION_JSON)
					.param("specialOfferId", "1").param("expirationDate", "2021-11-17");

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.out.println("Test response createVoucherCodeOnEachRecipientTest=" + response.getContentAsString());
			assertEquals(expectedResult, result.getResponse().getContentAsString(), true);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void redeemVoucherTest() throws Exception {
		String expectedResult = "4.5";
		String jsonString ="{\"voucherCodeId\": \"cdd8b0f8-553d-4d1f-bae9-1337af198ada\",\"email\": \"khalil@gmail.com\"}\r\n";
		
		Mockito.when(recipientService.getRecipientByEmail(Mockito.anyString())).thenReturn(testRecipientBean1);
		Mockito.when(voucherCodeService.getVoucherCodeById(Mockito.anyString())).thenReturn(testVoucherCodeBean1);
		Mockito.when(voucherCodeService.validateRedeemVoucherCode(Mockito.any(), Mockito.any())).thenReturn("");
		Mockito.when(specialOfferService.getSpecialOfferById(Mockito.anyInt())).thenReturn(testSpecialOfferBean1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/voucher/rest-api/redeem-voucher").accept(MediaType.APPLICATION_JSON)
				.content(jsonString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("Test response redeemVoucherTest=" + response.getContentAsString());
		assertEquals(expectedResult, result.getResponse().getContentAsString(), true);
	}
	
	@Test
	public void getVoucherByEmailTest() throws Exception {
		String expectedResult = "[{\"voucherCodeId\":\"cdd8b0f8-553d-4d1f-bae9-1337af198ada\",\"specialOfferName\":\"KFC 2021 Offer\",\"fixedPercentageDiscount\":4.5,\"expirationDate\":\"2021-10-10T16:00:00.000+00:00\",\"usedFrequencies\":0,\"usageDate\":null}]";
		voucherCodeOfferDtoList.add(testVoucherCodeOfferDto1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/voucher/rest-api/get-voucher-by-email").accept(MediaType.APPLICATION_JSON)
				.param("email", "tester1@gmail.com");
		Mockito.when(voucherCodeService.findVoucherCodeOfferByEmail(Mockito.anyString())).thenReturn(voucherCodeOfferDtoList);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("Test response getVoucherByEmailTest=" + response.getContentAsString());
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), true);
	}
	
	private static Date getDate(String dateString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}

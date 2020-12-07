package com.ibm.application.spb.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.application.spb.domain.Adress;
import com.ibm.application.spb.domain.Category;
import com.ibm.application.spb.domain.City;
import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.domain.Order;
import com.ibm.application.spb.domain.OrderItem;
import com.ibm.application.spb.domain.Payment;
import com.ibm.application.spb.domain.PaymentWithCard;
import com.ibm.application.spb.domain.PaymentWithInvoice;
import com.ibm.application.spb.domain.Product;
import com.ibm.application.spb.domain.State;
import com.ibm.application.spb.domain.enums.CustomerType;
import com.ibm.application.spb.domain.enums.Profiles;
import com.ibm.application.spb.domain.enums.StatusPayment;
import com.ibm.application.spb.repositories.AdressRepository;
import com.ibm.application.spb.repositories.CategoryRepository;
import com.ibm.application.spb.repositories.CityRepository;
import com.ibm.application.spb.repositories.ClientRepository;
import com.ibm.application.spb.repositories.OrderItemRepository;
import com.ibm.application.spb.repositories.OrderRepository;
import com.ibm.application.spb.repositories.PaymentRepository;
import com.ibm.application.spb.repositories.ProductRepository;
import com.ibm.application.spb.repositories.StateRepository;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AdressRepository adressRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private OrderItemRepository orderItem;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");

	public void instantiateDataBase() throws ParseException {

		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Computing");
		Category cat3 = new Category(null, "Bed, Table and Bath");
		Category cat4 = new Category(null, "Computing");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decoration");
		Category cat7 = new Category(null, "Perfumery");

		Product prod1 = new Product(null, "Computer", 2000.00);
		Product prod2 = new Product(null, "Printer", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);
		Product prod4 = new Product(null, "Office Desk", 300.00);
		Product prod5 = new Product(null, "Towel", 50.00);
		Product prod6 = new Product(null, "Quilt", 200.00);
		Product prod7 = new Product(null, "TV True Color", 1200.00);
		Product prod8 = new Product(null, "BrushCutter", 800.00);
		Product prod9 = new Product(null, "BedSide Lamp", 100.00);
		Product prod10 = new Product(null, "Pending", 180.00);
		Product prod11 = new Product(null, "Shampoo", 90.00);

		cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProducts().addAll(Arrays.asList(prod2, prod4));
		cat3.getProducts().addAll(Arrays.asList(prod5, prod6));
		cat4.getProducts().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProducts().addAll(Arrays.asList(prod8));
		cat6.getProducts().addAll(Arrays.asList(prod9, prod10));
		cat7.getProducts().addAll(Arrays.asList(prod11));

		prod1.getCategories().addAll(Arrays.asList(cat1, cat4));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		prod3.getCategories().addAll(Arrays.asList(cat1, cat4));
		prod4.getCategories().addAll(Arrays.asList(cat2));
		prod5.getCategories().addAll(Arrays.asList(cat3));
		prod6.getCategories().addAll(Arrays.asList(cat3));
		prod7.getCategories().addAll(Arrays.asList(cat4));
		prod8.getCategories().addAll(Arrays.asList(cat5));
		prod9.getCategories().addAll(Arrays.asList(cat6));
		prod10.getCategories().addAll(Arrays.asList(cat6));
		prod11.getCategories().addAll(Arrays.asList(cat7));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));

		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");

		City c1 = new City(null, "Urbelandia", s1);
		City c2 = new City(null, "São Paulo", s2);
		City c3 = new City(null, "Campinas", s2);

		s1.getCitys().addAll(Arrays.asList(c1));
		s2.getCitys().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(s1, s2));

		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria silva", "jeff98alves@gmail.com", bCryptPasswordEncoder.encode("1234567"), "36378912377", CustomerType.PHYSICAL_PERSON);
		cli1.getPhones().addAll(Arrays.asList("27363323", "938383935"));

		Client cli2 = new Client(null, "João Silva", "jeffersonalmeida16@gmail.com", bCryptPasswordEncoder.encode("H3243254d#"), "35496494322", CustomerType.LEGAL_PERSON);
		cli2.getPhones().addAll(Arrays.asList("43843995", "954654332"));
		cli2.addProfile(Profiles.ADM);
		

		Adress a1 = new Adress(null, "Rua Flores", 300, "Apto 203", "Jardim", 38220834, cli1, c1);
		Adress a2 = new Adress(null, "Avenida Matos", 105, "Sala 800", "Centro", 38777012, cli1, c2);
		Adress a3 = new Adress(null, "Avenida Floriano", 105, null, "Centro", 9435403, cli2, c2);

		cli1.getAdresses().addAll(Arrays.asList(a1, a2));
		cli2.getAdresses().addAll(Arrays.asList(a3));

		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		adressRepository.saveAll(Arrays.asList(a1, a2, a3));

		Order o1 = new Order(null, sdf.parse("2017/09/30 10:32:00"), cli1, a1);
		Order o2 = new Order(null, sdf.parse("2017/10/10 19:35:00"), cli1, a2);

		Payment p1 = new PaymentWithCard(null, StatusPayment.PAID, o1, 6);
		Payment p2 = new PaymentWithInvoice(null, StatusPayment.PEDING, o2, sdf.parse("2017/10/20 22:00:00"), null);

		o1.setPayment(p1);
		o2.setPayment(p2);

		cli1.getOrders().addAll(Arrays.asList(o1, o2));

		orderRepository.saveAll(Arrays.asList(o1, o2));
		paymentRepository.saveAll(Arrays.asList(p1, p2));

		OrderItem oi1 = new OrderItem(o1, prod1, 0, 1, 2000.0);
		OrderItem oi2 = new OrderItem(o1, prod3, 0, 2, 800.0);
		OrderItem oi3 = new OrderItem(o2, prod2, 100, 1, 800.0);

		o1.getOrderItems().addAll(Arrays.asList(oi1, oi2));
		o2.getOrderItems().addAll(Arrays.asList(oi3));

		orderItem.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}

package sda.pl;

import sda.pl.domain.*;
import sda.pl.repository.OrderRepository;
import sda.pl.repository.ProductRatingRepository;
import sda.pl.repository.ProductRepository;
import sda.pl.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class App {

    public static void main(String[] args) {
        String[] names = new String[]{"Edward", "Brian", "Harold", "Theo", "Matt"};
        String[] email = new String[]{"Edward@mail.com", "Brian@mail.com", "Harold@mail.com", "Theo@mail.com", "Matt@mail.com"};
        String[] pwd = new String[]{"imEdward", "imBrian", "imHarold", "imTheo", "imMatt"};
//        for (int i = 0; i < names.length; i++) {
//            User user = User.builder().firstName(names[i]).email(email[i]).password(pwd[i]).build();
//            UserRepository.saveOrUpdate(user);
//        }
//        User user = User.builder().firstName("Harold").email("brian@mail.com").password("imharold").build();
//        UserRepository.saveOrUpdate(user);
        for (int i = 0; i < 20; i++){


    Product maslo = Product.builder()
            .name("Masło")
            .color(Color.RED)
            .price(Price.builder()
                    .priceGross(new BigDecimal ("4.72"))
                    .priceNet(new BigDecimal("4.20"))
                    .priceSymbol("PLN")
                    .build())
            .productType(ProductType.MASLO)
            .build();
        ProductRepository.saveProduct(maslo);
}


        ProductRepository.findAll().forEach(e -> System.out.println(e.getId() + " " + e.getName()));
        ProductRepository.findAllWithPriceNetLessThan(new BigDecimal(4));
        System.out.println("Records : " + ProductRepository.countAll());

        Optional<Product> product = ProductRepository.findProduct(3L);
        if (product.isPresent()) {
            Product product2 = product.get();
            product2.setName("Kefir");
            product2.setProductType(ProductType.KEFIR);
            product2.setPrice(Price.builder()
                    .priceNet(new BigDecimal("1.2"))
                    .priceGross(new BigDecimal("1.8"))
                    .priceSymbol("USD")
                    .build());

            ProductRepository.saveOrUpdateProduct(product2);
        }

//
//            Order kowalskiOrder = Order.builder()
//                    .date(LocalDateTime.now())
//                    .cityname("Posen")
//                    .RODO(true)
//                    .totalPrice(new Price())
//                    .build();
//            OrderDetails detail1 = OrderDetails.builder()
//                    .amount(7L)
//                    .product(product2)
//                    .price(product2.getPrice())
//                    .build();
//            kowalskiOrder.addOrderDetail(detail1);

//            double sum = kowalskiOrder.getOrderDetailsSet().stream()
//                    .mapToDouble(
//                            cd -> cd.getPrice()
//                                    .priceGross
//                                    .multiply(new BigDecimal(cd.getAmount()))
//                                    .doubleValue())
//                    .sum();
//            BigDecimal sumPriceGross = new BigDecimal(0);
//            kowalskiOrder.calculateTotalPrice();
//            OrderRepository.saveOrder(kowalskiOrder);
//            OrderRepository.collectAllOrders().forEach(e->
//                    e.getOrderDetailsSet()
//                            .forEach(x->System.out.println(x.getProduct().getName())));
//        }
        OrderRepository.collectAllWithId("Kefir").forEach(e->System.out.println(e.getId()));
        UserRepository.findAllWithTotalOrderPrice().forEach(e->System.out.println(e.getEmail() + " " + e.getTotalOrderPrice()));
       ProductRepository.findByNameCriteriaQuery("Maslo") ;
    ProductRatingRepository.findAllActiveorWithRatingabove(3L, 3).forEach(System.out::println);
    }
}

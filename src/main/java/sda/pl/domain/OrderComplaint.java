//package sda.pl.domain;
//
//import lombok.AllArgsConstructor;
//import lombok.EqualsAndHashCode;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@AllArgsConstructor
//@EqualsAndHashCode(exclude = "orderSet")
//@Table(name = "Complaint")
//public class OrderComplaint {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//    String message;
//    @Enumerated(value = EnumType.STRING)
//    ComplaintStatus complaintStatus;
//    @ManyToMany(mappedBy = "orderComplaintSet", cascade = CascadeType.ALL)
//    Set<Order> orderSet;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    Set<OrderDetails> orderDetailsSet;
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            //joinColumns nazwa kolumny w tabeli dodatkowej z kluczem w do tabeli łączonej + nazwa pola w ensji z kluczem
//            // po którym łączymy
//            name = "order_complaint",
//            //nazwa kolumny z kluczem glownym z encji Order
//            joinColumns = @JoinColumn(name = "order_complaint_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id")
//    )
//    Set<OrderComplaint> orderComplaintSet;
//
//}
//

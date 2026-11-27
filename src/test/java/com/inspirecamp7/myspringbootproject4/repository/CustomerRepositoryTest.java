package com.inspirecamp7.myspringbootproject4.repository;

import com.inspirecamp7.myspringbootproject4.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    //1.Customer 등록
    @Test
    void testInsert() {
        //Given-When-Then
        //Given (준비단계)
        Customer customer = new Customer();
        customer.setCustomerId("B001");
        customer.setCustomerName("스프링B");
        //When (실행단계)
        Customer addedCustomer = customerRepository.save(customer);
        //Then (검증단계)
        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getCustomerId()).isEqualTo("B001");
        assertThat(addedCustomer.getCustomerName()).isEqualTo("스프링B");
    }

    //2.Customer 조회 ( 값이 일치하는 Customer 존재하는 경우 )
    @Test
    void testRead() {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId("B001");
        //Optional이 담고 있는 Customer가 값을 가지고 있으면?
        if(optionalCustomer.isPresent()){
            Customer existCustomer = optionalCustomer.get();
            assertThat(existCustomer.getCustomerId()).isEqualTo("B001");
        }
        //함수형 인터페이스 Customer 의 추상메서드 void accept(T t)
        optionalCustomer.ifPresent(customer -> System.out.println(customer.getCustomerName()));
    }

    //3.Customer 조회 ( 값이 일치하는 Customer 존재하지 않는 경우 )
    @Test
    void testReadNotFound() {
        //Optional orElseGet(Supplier supplier)
        //함수형인터페이스 Supplier 추상메서드 T get()
        Customer customer = customerRepository.findByCustomerId("F001")  //Optional<Customer>
                .orElseGet(() -> new Customer());
        //Customer가 값이 있는 경우는 성공함
        //assertThat(customer.getCustomerId()).isNotNull();
        
        //Customer가 값이 없는 경우는 성공함
        assertThat(customer.getCustomerId()).isNull();
    }

}
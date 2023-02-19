package org.cap.demo.dao;

import java.util.Optional;

import org.cap.demo.json.CustomerRequest;
import org.cap.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerRequest,Long> {

	Customer findByEmailIdAndPassword(String emailId, String password);

	Optional<CustomerRequest> findByEmailId(String emailId);
   Optional<CustomerRequest> findByResetToken(String resetToken);
   Optional<CustomerRequest> findByFirstName(String uname);
   

}

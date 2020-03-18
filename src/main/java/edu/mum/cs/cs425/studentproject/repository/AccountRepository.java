package edu.mum.cs.cs425.studentproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
public final static String accountUserDetail ="Select new edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail(a.accountType, a, u) "
												+ "from Account a Join a.accountUser u Where u.userId = :id";
	

	@Query(accountUserDetail)
	List<UserAccountDetail> findUserAccontDetails(Long id);
}

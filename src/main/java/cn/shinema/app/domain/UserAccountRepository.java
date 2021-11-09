package cn.shinema.app.domain;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository
		extends JpaRepository<UserAccount, Serializable>, JpaSpecificationExecutor<UserAccount> {

	UserAccount findByCellphone(String cellphone);

	UserAccount findByCellphoneOrEmail(String cellphone, String email);

	@Query("from UserAccount where cellphone = ?1 or email = ?1")
	public UserAccount findByUserName(String username);

	public UserAccount findByAccountId(String accountId);

}

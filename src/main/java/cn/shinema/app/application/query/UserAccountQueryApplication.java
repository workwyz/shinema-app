package cn.shinema.app.application.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.shinema.app.domain.UserAccount;
import cn.shinema.app.domain.UserAccountRepository;
import cn.shinema.app.domain.UserStatisticsRepository;

@Service
public class UserAccountQueryApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountQueryApplication.class);

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserStatisticsRepository userStatisticsRepository;

	public Page<UserAccount> list(UserAccount queryAccount) {
//		Specification<UserAccount> spec = SpecificationFactory.gt("user.age", 10).
//		userAccountRepository.findAll(spec, Pageable.ofSize(10));

		Specification<UserAccount> userAccountSpecification = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			// 模糊查询
			if (queryAccount.email() != null && queryAccount.email().length() > 0) {
				predicates.add(cb.like(root.get("email"), queryAccount.email() + "%"));
			}

			// 日期比较
			if (queryAccount.createdDate() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("createDate").as(String.class), "2020-11-21 14:54:42"));
			}

			return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
		};

		return userAccountRepository.findAll(userAccountSpecification, Pageable.ofSize(10));

	}
}

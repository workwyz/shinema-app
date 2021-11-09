package cn.shinema.app.domain;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticsRepository
		extends JpaRepository<UserStatistics, String>, JpaSpecificationExecutor<UserStatistics> {
	UserStatistics findByAccountIdAndStatDate(String accountId, Date statDate);
}
